package t2406e_group1.bookshopspringboot.import_product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaImportProductDetail extends JpaRepository<EntityImportProductDetail, Integer> {
}