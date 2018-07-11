/**
 * Created by HP on 07/07/2018.
 */
package com.example.sit.myapplication;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Place {
    private int no_of_cols;
    private int no_of_rows;
    private int no_of_slots;
    public Map<String, List<Slot>> Slots = new
            HashMap<String, List<Slot>>();

    public Place(int no_of_cols, int no_of_rows, int no_of_slots) {
        this.no_of_cols = no_of_cols;
        this.no_of_rows = no_of_rows;
        this.no_of_slots = no_of_slots;

    }

         public Place() {
        }

    public int getNo_of_cols() {
        return no_of_cols;
    }

    public void setNo_of_cols(int no_of_cols) {
        this.no_of_cols = no_of_cols;
    }

    public int getNo_of_rows() {
        return no_of_rows;
    }

    public void setNo_of_rows(int no_of_rows) {
        this.no_of_rows = no_of_rows;
    }

    public int getNo_of_slots() {
        return no_of_slots;
    }

    public void setNo_of_slots(int no_of_slots) {
        this.no_of_slots = no_of_slots;
    }
}


