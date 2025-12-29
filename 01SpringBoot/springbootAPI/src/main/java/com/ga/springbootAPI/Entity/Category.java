package com.ga.springbootAPI.Entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
//import lombok.Getter;
//import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column
    @Getter @Setter private String name;

    @Column
    @Getter @Setter private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "category", orphanRemoval = true)
    @Getter @Setter private List<Recipe> recipeList;

    @Column
    @CreationTimestamp
    @Getter @Setter private LocalDateTime createdAt;

    @Column
    @UpdateTimestamp
    @Getter
    @Setter
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    @Getter
    @Setter
    private User user;
}