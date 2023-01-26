package com.fmartinez.disney.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "GENRE")
public class Genre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String name;
    private String image;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "gender")
    @JsonIgnore
    private Set<MovieSerie> movies;

    public void addMovieSerie(MovieSerie movie) {
        this.movies.add(movie);
        movie.setGender(this);
    }

    public void removeMovie(MovieSerie movie) {
        this.movies.remove(movie);
        movie.setGender(null);
    }

    public void removesMovies() {
        for (MovieSerie m : new HashSet<>(movies)) {
            removeMovie(m);
        }
    }

    @Serial
    private static final long serialVersionUID = -2202074855826539188L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Genre genre = (Genre) o;
        return id != null && Objects.equals(id, genre.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
