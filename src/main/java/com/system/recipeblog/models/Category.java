package com.system.recipeblog.models;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "CATEGORIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "CATEGORY TITLE")
    public String categoryTitle;

    @Column(name = "CATEGORY DESCRIPTION")
    public String categoryDescription;

    @ManyToMany
    private List<Recipe> recipes = new ArrayList<>();

}
