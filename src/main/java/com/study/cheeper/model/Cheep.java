package com.study.cheeper.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Cheep {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "text")
    private String message;
    private LocalDateTime creation = LocalDateTime.now();

    @ManyToOne
    private User autor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreation() { return creation; }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }
}
