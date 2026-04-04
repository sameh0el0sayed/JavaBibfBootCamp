package com.ga.TicketSystemProject3.Model.request;

public class ChangePasswordRequest {
    private String newPass;
    private String oldPass;

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }
}
