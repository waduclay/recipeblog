package com.system.recipeblog.models;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "RECIPES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "RECIPE TITLE")
    public String recipeTitle;

    @Column(name = "RECIPE DESCRIPTION")
    public String recipeDescription;

    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToMany
    private List<Category> categories = new ArrayList<>();
}
