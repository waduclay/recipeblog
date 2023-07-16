package com.system.recipeblog.models;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "INGREDIENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    @Column(name = "INGREDIENT TITLE")
    public String ingredientTitle;

    @ManyToMany
    private List<Recipe> recipes = new ArrayList<>();

}
