import pyrebase, random
import datetime, time
config = {
  "apiKey": "AIzaSyBPBr-MCdMvRpSDwMggk79nxGQvnWAmW5U",
  "authDomain": "fir-app-37941.firebaseapp.com",
  "databaseURL": "https://fir-app-37941.firebaseio.com/",
  "storageBucket": "fir-app-37941.appspot.com"
}

def set_db(firebase, place, tot, r, c):
	db = firebase.database()
	data={"no_of_slots":tot, "no_of_rows":r, "no_of_cols":c}
	db.child(place).set(data)
	for i in range(0,tot):
		db.child("/").child(place).child("slot"+str(i)).set({"Status":0, "vehicle":"No","timestamp":"00:00:00"})
		print("Done ", i)

def update_db(firebase, parent, slot_no, vehicle_no, status, timestamp):
	db = firebase.database()
	db.child("/").child(parent).child("Slots").child("Slots").child(str(slot_no)).update({"Status":status, "vehicle":vehicle_no,"timestamp":timestamp})
def retrieve_db(firebase, parent, slot_no, value):
    	db = firebase.database()
    	return db.child("/").child(parent).child("Slots").child("Slots").child(str(slot_no)).child(value).get().val() 

def history_db(firebase, vehicle, lot, arrival=0, departure=0):
    db = firebase.database()
    if arrival!=0:
        db.child("/").child("History_of_Vehicles").child(vehicle).set({"Lot":lot,"Arrival":arrival,"Departure":departure})

def initialize():
    firebase = pyrebase.initialize_app(config)
    '''set_db(firebase, "SSIT", 18, 3, 6)
    basic="KA-06-EE-"
    vehicles=[]
    for i in range(0,15):
        vehicles.append(basic+str(random.randint(1000,10000)))
        t=datetime.datetime.fromtimestamp(time.time()).strftime('%Y-%m-%d %H:%M:%S')
        update_db(firebase, "SSIT", i, vehicles[i] , 1,t)'''
    return firebase