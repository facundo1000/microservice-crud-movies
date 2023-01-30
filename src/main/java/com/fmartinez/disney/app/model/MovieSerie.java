package com.fmartinez.disney.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fmartinez.disney.app.dto.GenreDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MOVIE_SERIE")
@SQLDelete(sql = "UPDATE MOVIE_SERIE SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class MovieSerie implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String image;
    private String title;
    @Column(name = "create_at")
    @Temporal(value = TemporalType.DATE)
    private Date createAt;
    private Integer rate; //Calificacion de 1 a 5
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @JoinTable(name = "TBL_MOVIE_CHARACTER",
            joinColumns = @JoinColumn(name = "MOVIE_SERIE_id"),
            inverseJoinColumns = @JoinColumn(name = "CHARACTER_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"MOVIE_SERIE_id", "CHARACTER_id"}))

    @JsonIgnore
    private Set<Character> characters = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "gender_id")
    @JsonIgnore
    private Genre gender;

    private Boolean deleted = Boolean.FALSE;

    public void addCharacter(Character character) {
        this.characters.add(character);
        character.getMovies().add(this);
    }

    public void removeCharacter(Character character) {
        this.characters.remove(character);
        character.getMovies().remove(this);
    }

    public void removeCharacters() {
        for (Character c : new HashSet<>(characters)) {
            removeCharacter(c);
        }
    }

    @Serial
    private static final long serialVersionUID = 977663249957830579L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MovieSerie that = (MovieSerie) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
