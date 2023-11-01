package com.example.kinoteatr.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_status;
    @NotBlank(message = "Введите название")
    @Size(max = 50, message = "Длина названия должна быть не больше 50 символов")
    @Column(name = "name_status")
    private String name_status;

    @OneToMany (mappedBy = "status", fetch = FetchType.LAZY)
    private Collection<Indent> order;


    public Status(){}

    public Status(long id_status, String name_status, Collection<Indent> order) {
        this.id_status = id_status;
        this.name_status = name_status;
        this.order = order;
    }

    public long getId_status() {
        return id_status;
    }

    public void setId_status(long id_status) {
        this.id_status = id_status;
    }

    public String getName_status() {
        return name_status;
    }

    public void setName_status(String name_status) {
        this.name_status = name_status;
    }

    public Collection<Indent> getOrder() {
        return order;
    }

    public void setOrder(Collection<Indent> order) {
        this.order = order;
    }
}
