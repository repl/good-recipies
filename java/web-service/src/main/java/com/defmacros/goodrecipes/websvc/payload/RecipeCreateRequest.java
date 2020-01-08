package com.defmacros.goodrecipes.websvc.payload;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeCreateRequest {
    private String title;
    private List<String> ingredients;
    private String instructions;
}
