package io.qdivision.qtp.movies.Names;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameRepository extends JpaRepository<NameEntity, Long> {
}
