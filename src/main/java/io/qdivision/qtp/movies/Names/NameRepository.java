package io.qdivision.qtp.movies.Names;

import io.qdivision.qtp.movies.LikedStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NameRepository extends JpaRepository<NameEntity, Long> {
    List<NameEntity> findByLikedStatus(LikedStatus status);
}
