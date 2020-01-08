package com.defmacros.goodrecipes.websvc.model;

import javax.persistence.OneToMany;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String entry;

    @ManyToOne
    @JoinColumn(name = "recipeId", nullable = false)
    @JsonIgnore
    private Recipe recipe;
}
