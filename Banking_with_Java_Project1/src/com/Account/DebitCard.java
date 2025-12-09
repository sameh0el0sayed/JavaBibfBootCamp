package com.Account;


import com.fasterxml.jackson.annotation.JsonProperty;

public class DebitCard {

    private String type;
    private double withdrawLimitPerDay;
    private double transferLimitPerDay;
    private double transferLimitPerDayOwnAccount;
    private double depositLimitPerDay;
    private double depositLimitPerDayOwnAccount;

    public DebitCard(
            @JsonProperty("type") String type,
            @JsonProperty("withdrawLimitPerDay") double withdrawLimitPerDay,
            @JsonProperty("transferLimitPerDay") double transferLimitPerDay,
            @JsonProperty("transferLimitPerDayOwnAccount") double transferLimitPerDayOwnAccount,
            @JsonProperty("depositLimitPerDay") double depositLimitPerDay,
            @JsonProperty("depositLimitPerDayOwnAccount") double depositLimitPerDayOwnAccount) {
        this.type = type;
        this.withdrawLimitPerDay = withdrawLimitPerDay;
        this.transferLimitPerDay = transferLimitPerDay;
        this.transferLimitPerDayOwnAccount = transferLimitPerDayOwnAccount;
        this.depositLimitPerDay = depositLimitPerDay;
        this.depositLimitPerDayOwnAccount = depositLimitPerDayOwnAccount;
    }

    // Getters and setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getWithdrawLimitPerDay() { return withdrawLimitPerDay; }
    public void setWithdrawLimitPerDay(double withdrawLimitPerDay) { this.withdrawLimitPerDay = withdrawLimitPerDay; }

    public double getTransferLimitPerDay() { return transferLimitPerDay; }
    public void setTransferLimitPerDay(double transferLimitPerDay) { this.transferLimitPerDay = transferLimitPerDay; }

    public double getTransferLimitPerDayOwnAccount() { return transferLimitPerDayOwnAccount; }
    public void setTransferLimitPerDayOwnAccount(double transferLimitPerDayOwnAccount) { this.transferLimitPerDayOwnAccount = transferLimitPerDayOwnAccount; }

    public double getDepositLimitPerDay() { return depositLimitPerDay; }
    public void setDepositLimitPerDay(double depositLimitPerDay) { this.depositLimitPerDay = depositLimitPerDay; }

    public double getDepositLimitPerDayOwnAccount() { return depositLimitPerDayOwnAccount; }
    public void setDepositLimitPerDayOwnAccount(double depositLimitPerDayOwnAccount) { this.depositLimitPerDayOwnAccount = depositLimitPerDayOwnAccount; }
}
