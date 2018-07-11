import RPi.GPIO as GPIO
import time, datetime
import database
import random
def send_trig_pulse(trig_pin):
        GPIO.output(trig_pin,True)
        time.sleep(0.0001)
        GPIO.output(trig_pin,False)
def wait_for_echo( echo_pin, value,timeout):
        count=timeout
        while GPIO.input(echo_pin)!=value and count>0:
            count=count-1
class parkingLot:
    
    def __init__(self, trig, echoList,ledList, lotName):
        self.trig=trig
        self.echoList=echoList
        self.lotName=lotName
        self.dis=[0]*len(echoList)
        self.ledList=ledList
        GPIO.setwarnings(False)
        GPIO.setmode(GPIO.BOARD)
        GPIO.setup(self.trig,GPIO.OUT)
        for i in self.echoList:
            GPIO.setup(i,GPIO.IN)
        for i in self.ledList:
            GPIO.setup(i, GPIO.OUT)
    
    def get_distance(self):
        for i in range(0, len(self.echoList)):
            send_trig_pulse(self.trig)
            wait_for_echo(self.echoList[i], True,10000)
            start=time.time()
            wait_for_echo(self.echoList[i], False,10000)
            finish=time.time()
            pulse_len=finish-start
            distance_cm=pulse_len/0.000058
            self.dis[i]=distance_cm         
        return(self.dis)
cs_block=parkingLot(36, [40, 38],[31,32], "SIT_CS_Block")
p=[0,0]
firebase=database.initialize()
vehicle_CS=["No", "No"]
prev_t=[0,0]
vehicle_back=['No', 'No']
while True:
    disList=cs_block.get_distance()
    print(disList)
    time.sleep(2)
    for i in range(0, len(disList)):
        dis=disList[i]
        if( dis>4):
            print("Parking slot"+str(i)+" is empty ", dis)
            vehicle_CS[i]=database.retrieve_db(firebase, cs_block.lotName, i, "vehicle")
            if vehicle_CS[i]!="No":
                vehicle_back[i]=vehicle_CS[i]
                GPIO.output(cs_block.ledList[i], True)
            time.sleep(2)
            p[i]=0
        elif (dis <=4):
            print("Parking slot"+str(i)+" is occupied ", dis)
            if database.retrieve_db(firebase, cs_block.lotName, i, "vehicle")!="No":
                GPIO.output(cs_block.ledList[i], False)
            time.sleep(2)
            p[i]=1
        now_t=datetime.datetime.fromtimestamp(time.time()).strftime('%Y-%m-%d %H:%M:%S')
        db_status=database.retrieve_db(firebase, cs_block.lotName, i, "Status")
        if p[i]==1 and db_status==0:
            GPIO.output(cs_block.ledList[i], False)
            database.update_db(firebase, cs_block.lotName, i, vehicle_CS[i],p[i], now_t )
            database.history_db(firebase, vehicle_CS[i], cs_block.lotName,arrival=now_t)
            prev_t[i]=now_t
        elif p[i]==0 and db_status==1:
            vehicle_CS[i]="No"
            database.update_db(firebase, cs_block.lotName, i, "No",p[i], now_t )
            GPIO.output(cs_block.ledList[i], False)
            database.history_db(firebase, vehicle_back[i], cs_block.lotName, arrival=prev_t[i], departure=now_t)
            vehicle_back[i]='No'

        
        
        
    
    

    

