package t2406e_group1.bookshopspringboot.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProduct extends JpaRepository<EntityProduct, Integer> {
    // Tìm sản phẩm theo tên
    Optional<EntityProduct> findByName(String name);
    
    // Tìm sản phẩm theo danh mục
    // List<EntityProduct> findByCategory(String category);
    
}
