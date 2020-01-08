package com.defmacros.goodrecipes.websvc.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recipe {
    Long id;
    String uid;
    String title;
    List<String> ingredients = new ArrayList<>();
    String instructions;
}
