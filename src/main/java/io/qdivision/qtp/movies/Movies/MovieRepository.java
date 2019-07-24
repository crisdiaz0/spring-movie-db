package io.qdivision.qtp.movies.Movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {
    List<MovieEntity> findByFavoriteIsTrue();

    @Query("from MovieEntity where start_year >= :beginRange and start_year <= :endRange")
    List<MovieEntity> findMoviesYearRange(@Param("beginRange") Integer beginRange, @Param("endRange") Integer endRange);

    List<MovieEntity> findByPrimaryTitleContainingIgnoreCase(String searchValue);

    List<MovieEntity> findByRating(Integer rating);
}
