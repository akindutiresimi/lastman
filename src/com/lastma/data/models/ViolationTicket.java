package com.lastma.data.models;

import java.util.Date;

public class ViolationTicket {
    private String plateNumber;
    private Date dateIssue;
    private String offence;
    private int fineAmount;

    public Date getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Date dateIssue) {
        this.dateIssue = dateIssue;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(int fineAmount) {
        this.fineAmount = fineAmount;
    }

    public String getOffence() {
        return offence;
    }

    public void setOffence(String offence) {
        this.offence = offence;
    }

}
