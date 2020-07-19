package com.study.cheeper.model.form;

import com.study.cheeper.model.Cheep;
import com.study.cheeper.model.User;
import com.study.cheeper.repository.UserRepository;

import java.util.Optional;

public class NewCheepForm {

    private String message;
    private Integer idAutor;

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public Integer getIdAutor() { return idAutor; }

    public void setIdAutor(Integer idAutor) { this.idAutor = idAutor; }

    public Cheep toCheep(UserRepository userRepository) {

        // Validar autor
        Optional<User> autor = userRepository.findById(this.idAutor);

        Cheep cheep = new Cheep();
        cheep.setMessage(this.message);
        cheep.setAutor(autor.get());

        return cheep;
    }
}
