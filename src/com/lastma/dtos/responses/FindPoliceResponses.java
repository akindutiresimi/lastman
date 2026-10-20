package com.lastma.dtos.responses;

public class FindPoliceResponses {
    private String fullNamw;
    private String email;
    private int id;
    private boolean loggedIn;

    public String getFullName() {
        return fullNamw;
    }

    public void setFullName(String fullName) {
        this.fullNamw = fullNamw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
