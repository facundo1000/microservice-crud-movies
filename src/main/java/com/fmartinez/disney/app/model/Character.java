package com.fmartinez.disney.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "CHARACTER")
public class Character implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotEmpty(message = "Image cannot be empty")
    private String image;
    @NotEmpty(message = "name cannot be empty")
    private String name;
    @NotNull(message = "field age cannot be null")
    @Min(value = 0)
    private Integer age;
    @NotNull(message = "weight cannot be null")
    @Min(value = 0)
    private Double weight;
    @NotEmpty(message = "story cannot be empty")
    private String story;
    @ManyToMany(mappedBy = "characters",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<MovieSerie> movies;

    public void addMovieSerie(MovieSerie movie) {
        this.movies.add(movie);
        movie.getCharacters().add(this);
    }

    public void removeMovie(MovieSerie movie) {
        this.movies.remove(movie);
        movie.getCharacters().remove(this);
    }

    public void removeMovies() {
        for (MovieSerie m : new HashSet<>(movies)) {
            removeMovie(m);
        }
    }

    @Serial
    private static final long serialVersionUID = -3110566712430087466L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Character character = (Character) o;
        return id != null && Objects.equals(id, character.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
