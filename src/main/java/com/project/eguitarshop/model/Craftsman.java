package com.project.eguitarshop.model;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "craftsmen")
public class Craftsman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Nullable
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "created_by_craftsman",
            joinColumns = @JoinColumn(name="craftsman_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "guitar_id",referencedColumnName = "id")
    )
    private List<Guitar> guitars;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Guitar> getGuitars() {
        return guitars;
    }

    public void setGuitars(List<Guitar> guitars) {
        this.guitars = guitars;
    }

}
