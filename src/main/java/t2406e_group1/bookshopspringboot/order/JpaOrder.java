package t2406e_group1.bookshopspringboot.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrder extends JpaRepository<EntityOrder, Integer> {
}