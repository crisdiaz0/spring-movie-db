package io.qdivision.qtp.movies.Names;

import io.qdivision.qtp.movies.LikedStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Name {
    private Long id;
    private String nconst;
    private String primaryName;
    private Integer birthYear;
    private Integer deathYear;
    private String primaryProfession;
    private String knownForTitles;
    private LikedStatus likedStatus;
}
