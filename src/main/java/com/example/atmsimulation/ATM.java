package com.example.atmsimulation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ATM {

    private List<Integer> currency = new ArrayList<Integer>(Arrays.asList(1000,500,100,50,20));

    private List<Integer> noteOfCurrency = new ArrayList<Integer>(Arrays.asList(3,12,20,30,10));

    private List<Integer> noteOfWithdraw = new ArrayList<Integer>();

    private BigDecimal totalAmount;

    public List<Integer> getCurrency() {
        return currency;
    }

    public List<Integer> getNoteOfCurrency() {
        return noteOfCurrency;
    }

    public void setNoteOfCurrency(List<Integer> numberOfCurrency) {
        this.noteOfCurrency = numberOfCurrency;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Integer> getNoteOfWithdraw() {
        return noteOfWithdraw;
    }

    public void setNoteOfWithdraw(List<Integer> noteOfWithdraw) {
        this.noteOfWithdraw = noteOfWithdraw;
    }
}
