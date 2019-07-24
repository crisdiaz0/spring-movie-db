package io.qdivision.qtp.movies;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="movie")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String tconst;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private boolean isAdult;
    private Integer startYear;
    private Integer runtimeMinutes;
    private String genres;
    private boolean favorite;

    @OneToMany
    @JoinTable(
            name = "title_name",
            joinColumns = {@JoinColumn(name = "title_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "name_id", referencedColumnName = "id", unique = true)}
    )
    private List<NameEntity> names;
}
