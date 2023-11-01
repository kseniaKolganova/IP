package com.example.kinoteatr.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_manufacturer;
    @NotBlank(message = "Введите название")
    @Size(max = 50, message = "Длина названия должна быть не больше 50 символов")
    @Column(name = "name_genre")
    private String name_manufacturer;

    @Min(value = 1900, message = "Значение должно быть больше 1900")
    @Max(value = 2023, message = "Значение должно быть меньше 2023")
    private int year_manufacturer;

    @ManyToOne
    @JoinColumn(name = "id_country") // Указывает на столбец, который связывает сущности
    private Country country;

    @OneToMany (mappedBy = "manufacturer", fetch = FetchType.LAZY)
    private Collection<Film> film;

    public Manufacturer(long id_manufacturer, String name_manufacturer, int year_manufacturer, Country country, Collection<Film> film) {
        this.id_manufacturer = id_manufacturer;
        this.name_manufacturer = name_manufacturer;
        this.year_manufacturer = year_manufacturer;
        this.country = country;
        this.film = film;
    }

    public Manufacturer() {

    }

    public long getId_manufacturer() {
        return id_manufacturer;
    }

    public void setId_manufacturer(long id_manufacturer) {
        this.id_manufacturer = id_manufacturer;
    }

    public String getName_manufacturer() {
        return name_manufacturer;
    }

    public void setName_manufacturer(String name_manufacturer) {
        this.name_manufacturer = name_manufacturer;
    }

    public int getYear_manufacturer() {
        return year_manufacturer;
    }

    public void setYear_manufacturer(int year_manufacturer) {
        this.year_manufacturer = year_manufacturer;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Collection<Film> getFilm() {
        return film;
    }

    public void setFilm(Collection<Film> film) {
        this.film = film;
    }
}
