package com.defmacros.goodrecipes.websvc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recipe {
    String id;
    String name;

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
