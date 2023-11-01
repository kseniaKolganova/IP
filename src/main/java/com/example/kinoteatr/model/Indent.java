package com.example.kinoteatr.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.util.List;

@Entity
@Table(name = "indent")
public class Indent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_indent;
    @DecimalMin(value = "0.0", message = "Значение должно быть больше 0")
    @DecimalMax(value = "100000.0", message = "Значение должно быть меньше 100000")
    private double price_indent;

    @ManyToOne
    @JoinColumn(name = "id_status") // Указывает на столбец, который связывает сущности
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name="indent_film",
            joinColumns=@JoinColumn (name="indent_id"),
            inverseJoinColumns=@JoinColumn(name="film_id"))
    private List<Film> film;

    @ManyToOne
    @JoinColumn(name = "id_user") // Указывает на столбец, который связывает сущности
    private ModelUser user;


    public Indent(){}

    public Indent(long id_indent, double price_indent, Status status, List<Film> film, ModelUser user) {
        this.id_indent = id_indent;
        this.price_indent = price_indent;
        this.status = status;
        this.film = film;
        this.user = user;
    }

    public ModelUser getUser() {
        return user;
    }

    public void setUser(ModelUser user) {
        this.user = user;
    }

    public long getId_indent() {
        return id_indent;
    }

    public void setId_indent(long id_indent) {
        this.id_indent = id_indent;
    }

    public double getPrice_indent() {
        return price_indent;
    }

    public void setPrice_indent(double price_indent) {
        this.price_indent = price_indent;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public List<Film> getFilm() {
        return film;
    }

    public void setFilm(List<Film> film) {
        this.film = film;
    }
}
