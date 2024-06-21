package com.system.recipeblog.models;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "title")
    public String title;

    @Column(name = "description")
    public String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
