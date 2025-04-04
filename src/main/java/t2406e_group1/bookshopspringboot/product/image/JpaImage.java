package t2406e_group1.bookshopspringboot.product.image;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaImage extends JpaRepository<EntityImage, Integer> {
    //Tìm ảnh theo ID ảnh
    List<EntityImage> findByProductId(int productId);
    
}
