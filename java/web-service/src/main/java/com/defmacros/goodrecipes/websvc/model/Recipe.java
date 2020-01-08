package com.defmacros.goodrecipes.websvc.model;

import javax.persistence.OneToMany;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "recipes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "uid"
            })
    })
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;

    @NotBlank
    @Size(max = 80)
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.PERSIST)
    private List<Ingredient> ingredients;

    private String instructions;
}
