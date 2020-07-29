package com.study.cheeper.model.form;

import com.study.cheeper.model.Cheep;
import com.study.cheeper.model.User;

public class NewCheepForm {

    private String message;

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Cheep toCheep(User author) {
        Cheep cheep = new Cheep();
        cheep.setMessage(this.message);
        cheep.setAutor(author);

        return cheep;
    }
}
