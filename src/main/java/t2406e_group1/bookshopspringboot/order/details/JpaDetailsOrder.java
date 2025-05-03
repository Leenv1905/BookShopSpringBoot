package t2406e_group1.bookshopspringboot.order.details;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaDetailsOrder extends JpaRepository<EntityDetailsOrder, Integer> {
}