package io.qdivision.qtp.movies.Names;

import io.qdivision.qtp.movies.LikedStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "name")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class NameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String nconst;
    private String primaryName;
    private Integer birthYear;
    private Integer deathYear;
    private String primaryProfession;
    private String knownForTitles;

    @Enumerated(EnumType.STRING)
    private LikedStatus likedStatus;
}
