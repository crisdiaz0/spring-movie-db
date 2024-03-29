package io.qdivision.qtp.movies.Movies;

import io.qdivision.qtp.movies.Names.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
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
    private Integer rating;

    private List<Name> names;
}
