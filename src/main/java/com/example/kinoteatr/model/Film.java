package com.example.kinoteatr.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_film;
    @NotBlank(message = "Введите название")
    @Size(max = 50, message = "Длина названия должна быть не больше 50 символов")
    @Column(name = "name")
    private String name_film;

    @Min(value = 1, message = "Значение должно быть больше 0")
    @Max(value = 200, message = "Значение должно быть меньше 200")
    private int numbers_actors_film;

    @Min(value = 1, message = "Значение должно быть больше 0")
    @Max(value = 10, message = "Значение должно быть меньше 10")
    private int rating_film;

    @DecimalMin(value = "1.0", message = "Значение должно быть больше 0")
    @DecimalMax(value = "100000.0", message = "Значение должно быть меньше 100000")
    private double price_film;

    @Min(value = 1800, message = "Значение должно быть больше 2000")
    @Max(value = 2030, message = "Значение должно быть меньше 2023")
    private int year_film;

    @Min(value = 1, message = "Значение должно быть больше 5")
    @Max(value = 100, message = "Значение должно быть меньше 1000")
    private int time_film;

    @ManyToOne
    @JoinColumn(name = "id_genre") // Указывает на столбец, который связывает сущности
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "id_manufacturer") // Указывает на столбец, который связывает сущности
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "id_author") // Указывает на столбец, который связывает сущности
    private Author author;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name="indent_film",
            joinColumns=@JoinColumn (name="film_id"),
            inverseJoinColumns=@JoinColumn(name="indent_id"))
    private List<Indent> indent;

    public Film(){}

    public Film(long id_film, String name_film, int numbers_actors_film, int rating_film, double price_film, int year_film, int time_film, Genre genre, Manufacturer manufacturer, Author author, List<Indent> indent) {
        this.id_film = id_film;
        this.name_film = name_film;
        this.numbers_actors_film = numbers_actors_film;
        this.rating_film = rating_film;
        this.price_film = price_film;
        this.year_film = year_film;
        this.time_film = time_film;
        this.genre = genre;
        this.manufacturer = manufacturer;
        this.author = author;
        this.indent = indent;
    }

    public List<Indent> getIndent() {
        return indent;
    }

    public void setIndent(List<Indent> indent) {
        this.indent = indent;
    }

    public long getId_film() {
        return id_film;
    }

    public void setId_film(long id_film) {
        this.id_film = id_film;
    }

    public String getName_film() {
        return name_film;
    }

    public void setName_film(String name_film) {
        this.name_film = name_film;
    }

    public int getNumbers_actors_film() {
        return numbers_actors_film;
    }

    public void setNumbers_actors_film(int numbers_actors_film) {
        this.numbers_actors_film = numbers_actors_film;
    }

    public int getRating_film() {
        return rating_film;
    }

    public void setRating_film(int rating_film) {
        this.rating_film = rating_film;
    }

    public double getPrice_film() {
        return price_film;
    }

    public void setPrice_film(double price_film) {
        this.price_film = price_film;
    }

    public int getYear_film() {
        return year_film;
    }

    public void setYear_film(int year_film) {
        this.year_film = year_film;
    }

    public int getTime_film() {
        return time_film;
    }

    public void setTime_film(int time_film) {
        this.time_film = time_film;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
