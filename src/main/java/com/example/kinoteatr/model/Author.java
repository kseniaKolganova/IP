package com.example.kinoteatr.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_author;
    @NotBlank(message = "Введите имя")
    @Size(max = 50, message = "Длина имени должна быть не больше 200 символов")
    @Column(name = "name_author")
    private String name_author;

    @OneToMany (mappedBy = "author", fetch = FetchType.LAZY)
    private Collection<Film> film;

    public Author(){}

    public Author(long id_author, String name_author, Collection<Film> film) {
        this.id_author = id_author;
        this.name_author = name_author;
        this.film = film;
    }

    public long getId_author() {
        return id_author;
    }

    public void setId_author(long id_author) {
        this.id_author = id_author;
    }

    public String getName_author() {
        return name_author;
    }

    public void setName_author(String name_author) {
        this.name_author = name_author;
    }

    public Collection<Film> getFilm() {
        return film;
    }

    public void setFilm(Collection<Film> film) {
        this.film = film;
    }
}
