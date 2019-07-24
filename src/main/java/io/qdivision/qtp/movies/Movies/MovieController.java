package io.qdivision.qtp.movies.Movies;

import io.qdivision.qtp.movies.LikedStatus;
import io.qdivision.qtp.movies.Names.Name;
import io.qdivision.qtp.movies.Names.NameEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("movies")
@Slf4j
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

    @GetMapping("/{id}")
    public Movie findById(@PathVariable("id") Long id) {
        return toMovie(movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found")));
    }

    @GetMapping("/favorites")
    public List<Movie> findIsFavorite() {
        return movieRepository.findByFavoriteIsTrue()
                .stream()
                .map(this::toMovie)
                .collect(Collectors.toList());
    }

    @GetMapping("/range/{beginRange}/{endRange}")
    public List<Movie> findMoviesDateRange(@PathVariable("beginRange") Integer beginRange, @PathVariable("endRange") Integer endRange) {
        return movieRepository.findMoviesYearRange(beginRange, endRange)
                .stream()
                .map(this::toMovie)
                .collect(Collectors.toList());
    }

    @GetMapping("/titles/{searchValue}")
    public List<Movie> findMoviesTitleSearch(@PathVariable("searchValue") String searchValue) {
        return movieRepository.findByPrimaryTitleContainingIgnoreCase(searchValue)
                .stream()
                .map(this::toMovie)
                .collect(Collectors.toList());
    }

    @GetMapping("/ratings/{rating}")
    public List<Movie> findMoviesByRating(@PathVariable("rating") Integer rating) {
        return movieRepository.findByRating(rating)
                .stream()
                .map(this::toMovie)
                .collect(Collectors.toList());
    }

    @GetMapping("/favoriteNames")
    public List<Name> findLikedNamesInFavoriteMovies() {
        List<Movie> unfilteredList = this.findIsFavorite();

        return unfilteredList.get(0).getNames()
                .stream()
                .filter(name -> name.getLikedStatus().equals(LikedStatus.LIKED))
                .collect(Collectors.toList());

//        return unfilteredList.stream()
//                .forEach();

//        movie.getNames().filter(name -> name.getLikedStatus.equals(LikeStatus.LIKED)

    }

    @PutMapping("/{id}/rating/{rating}")
    public Movie updateRating(@PathVariable("id") Long id, @PathVariable("rating") Integer rating) {
        MovieEntity movieToBeUpdated = toMovieEntity(findById(id));
        movieToBeUpdated.setRating(rating);
        return toMovie(movieRepository.saveAndFlush(movieToBeUpdated));
    }

    @PutMapping("/{id}/favorite")
    public Movie toggleFavorite(@PathVariable("id") Long id) {
        MovieEntity movieToBeUpdated = toMovieEntity(findById(id));
        movieToBeUpdated.setFavorite(!movieToBeUpdated.isFavorite());
        return toMovie(movieRepository.saveAndFlush(movieToBeUpdated));
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
                .favorite(movieEntity.isFavorite())
                .rating(movieEntity.getRating())
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
                .favorite(movie.isFavorite())
                .rating(movie.getRating())
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
                .likedStatus(name.getLikedStatus())
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
                .likedStatus(nameEntity.getLikedStatus())
                .build();
    }
}
