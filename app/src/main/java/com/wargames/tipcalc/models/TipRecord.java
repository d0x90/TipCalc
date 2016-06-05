package com.wargames.tipcalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Diego Campos on 4/06/2016.
 */
public class TipRecord {

    private double bill;
    private int tipPercentage;
    private Date timestamp;

    public double getTip()
    {
        return bill * (tipPercentage/100d);
    }

    public String getDateFormatted()
    {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("MM.dd.yyyy HH:mm");
        return simpledateformat.format(timestamp);
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public int getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
