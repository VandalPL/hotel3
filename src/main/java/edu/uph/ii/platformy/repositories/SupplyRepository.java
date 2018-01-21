package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.Supply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
    @Query("SELECT v FROM Supply v WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(v.name) LIKE upper(:phrase)  ) ")
    Page<Supply> findAllSupplyUsingFilter(@Param("phrase") String p, Pageable pageable);

}
