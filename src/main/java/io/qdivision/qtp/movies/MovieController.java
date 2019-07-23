package io.qdivision.qtp.movies;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("movies")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public List<Movie> getMovies() {
        return movieRepository.findAll()
                .stream()
                .map(movieEntity -> toMovie(movieEntity))
                .collect(Collectors.toList());
    }

    private Movie toMovie(MovieEntity movieEntity) {
        return Movie.builder()
                .id(movieEntity.getId())
                .tconst(movieEntity.getTconst())
                .titleType(movieEntity.getTitleType())
                .primaryTitle(movieEntity.getPrimaryTitle())
                .originalTitle(movieEntity.getOriginalTitle())
                .isAdult(movieEntity.isAdult())
                .startYear(movieEntity.getStartYear())
                .runtimeMinutes(movieEntity.getRuntimeMinutes())
                .genres(movieEntity.getGenres())
                .names(movieEntity.getNames()
                        .stream()
                        .map(this::toName)
                        .collect(Collectors.toList()))
                .build();
    }

    private MovieEntity toMovieEntity(Movie movie) {
        return MovieEntity.builder()
                .id(movie.getId())
                .tconst(movie.getTconst())
                .titleType(movie.getTitleType())
                .primaryTitle(movie.getPrimaryTitle())
                .originalTitle(movie.getOriginalTitle())
                .isAdult(movie.isAdult())
                .startYear(movie.getStartYear())
                .runtimeMinutes(movie.getRuntimeMinutes())
                .genres(movie.getGenres())
                .names(movie.getNames()
                        .stream()
                        .map(this::toNameEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    private NameEntity toNameEntity(Name name) {
        return NameEntity.builder()
                .id(name.getId())
                .nconst(name.getNconst())
                .primaryName(name.getPrimaryName())
                .birthYear(name.getBirthYear())
                .deathYear(name.getDeathYear())
                .primaryProfession(name.getPrimaryProfession())
                .knownForTitles(name.getKnownForTitles())
                .build();
    }

    private Name toName(NameEntity nameEntity) {
        return Name.builder()
                .id(nameEntity.getId())
                .nconst(nameEntity.getNconst())
                .primaryName(nameEntity.getPrimaryName())
                .birthYear(nameEntity.getBirthYear())
                .deathYear(nameEntity.getDeathYear())
                .primaryProfession(nameEntity.getPrimaryProfession())
                .knownForTitles(nameEntity.getKnownForTitles())
                .build();
    }
}
