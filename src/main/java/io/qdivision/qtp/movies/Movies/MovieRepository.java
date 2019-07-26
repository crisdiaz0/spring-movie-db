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

//    @Query("from movie m inner join title_name tn on m.id = tn.title_id INNER JOIN name n on n.id = tn.name_id and n.liked_status='LIKED' and m.favorite = true")
//    List<MovieEntity> findFavoriteMoviesWithLikedNames();
}
