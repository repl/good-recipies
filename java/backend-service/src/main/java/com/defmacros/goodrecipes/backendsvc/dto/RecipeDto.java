package com.defmacros.goodrecipes.backendsvc.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeDto {
    Long id;
    String uid;
    String title;
    List<String> ingredients;
    String instructions;
}
