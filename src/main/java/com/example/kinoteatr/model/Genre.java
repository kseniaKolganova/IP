package com.example.kinoteatr.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_genre;
    @NotBlank(message = "Введите название")
    @Size(max = 50, message = "Длина названия должна быть не больше 50 символов")
    @Column(name = "name_genre")
    private String name_genre;

    @OneToMany (mappedBy = "genre", fetch = FetchType.LAZY)
    private Collection<Film> film;

    public Genre(){}

    public Genre(long id_genre, String name_genre, Collection<Film> film) {
        this.id_genre = id_genre;
        this.name_genre = name_genre;
        this.film = film;
    }

    public long getId_genre() {
        return id_genre;
    }

    public void setId_genre(long id_genre) {
        this.id_genre = id_genre;
    }

    public String getName_genre() {
        return name_genre;
    }

    public void setName_genre(String name_genre) {
        this.name_genre = name_genre;
    }

    public Collection<Film> getFilm() {
        return film;
    }

    public void setFilm(Collection<Film> film) {
        this.film = film;
    }
}
