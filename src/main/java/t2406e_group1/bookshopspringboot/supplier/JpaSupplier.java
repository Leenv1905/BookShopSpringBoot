package t2406e_group1.bookshopspringboot.supplier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaSupplier extends JpaRepository<EntitySupplier, Integer> {
    // Có thể thêm các phương thức tìm kiếm tùy chỉnh nếu cần
}
