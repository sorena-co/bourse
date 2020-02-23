package ir.bourse.repository;
import ir.bourse.domain.OrderRequest;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrderRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderRequestRepository extends JpaRepository<OrderRequest, Long> {

}
