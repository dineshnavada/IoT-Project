package com.example.sit.myapplication;

/**
 * Created by HP on 07/07/2018.
 */

public class Slot {


    private String timestamp;
    private String vehicle;
    private int Status;

    public Slot(String timestamp, String vehicle, int status) {
        this.timestamp = timestamp;
        this.vehicle = vehicle;
        Status = status;
    }

    public Slot() {
    }

    public String getTimestamp() {
        return timestamp;
    }



    public String getVehicle() {
        return vehicle;
    }



    public int getStatus() {
        return Status;
    }

}





