package ir.bourse.repository;
import ir.bourse.domain.Sign;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Sign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SignRepository extends JpaRepository<Sign, Long> {

}
