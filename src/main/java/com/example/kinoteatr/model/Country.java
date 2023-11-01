package com.example.kinoteatr.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_country;
    @NotBlank(message = "Введите название")
    @Size(max = 50, message = "Длина названия должна быть не больше 50 символов")
    @Column(name = "name_country")
    private String name_country;

    @OneToMany (mappedBy = "country", fetch = FetchType.LAZY)
    private Collection<Manufacturer> manufacturer;

    public Country(){}

    public Country(long id_country, String name_country, Collection<Manufacturer> manufacturer) {
        this.id_country = id_country;
        this.name_country = name_country;
        this.manufacturer = manufacturer;
    }

    public long getId_country() {
        return id_country;
    }

    public void setId_country(long id_country) {
        this.id_country = id_country;
    }

    public String getName_country() {
        return name_country;
    }

    public void setName_country(String name_country) {
        this.name_country = name_country;
    }

    public Collection<Manufacturer> getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Collection<Manufacturer> manufacturer) {
        this.manufacturer = manufacturer;
    }
}
