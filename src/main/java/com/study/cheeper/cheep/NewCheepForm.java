package com.study.cheeper.cheep;

import com.study.cheeper.model.User;

public class NewCheepForm {

    private String message;

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Cheep toCheep(User profile) {
        Cheep cheep = new Cheep();
        cheep.setMessage(this.message);
        cheep.setProfile(profile);

        return cheep;
    }
}
