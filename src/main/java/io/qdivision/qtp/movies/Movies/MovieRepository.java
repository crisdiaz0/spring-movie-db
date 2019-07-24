package io.qdivision.qtp.movies.Movies;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    List<MovieEntity> findByFavoriteIsTrue();
}
