package t2406e_group1.bookshopspringboot.product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProduct extends JpaRepository<EntityProduct, Integer> {
    // Tìm sản phẩm theo tên
    Optional<EntityProduct> findByName(String name);
    
    // Tìm sản phẩm theo giá
    List<EntityProduct> findByPrice(Float price);
    
    //Tìm sản phẩm theo thể loại
    List<EntityProduct> findByCategory(String category);

    //Tìm sản phẩm theo ngôn ngữ
    List<EntityProduct> findByLanguage(String language);
}
