package t2406e_group1.bookshopspringboot.maketing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository // Đánh dấu đây là một Repository (DAO - Data Access Object)
public interface JpaDiscount extends JpaRepository<EntityDiscount, Integer> {

  
    
    // Nếu cần các phương thức truy vấn tùy chỉnh, có thể khai báo thêm tại đây
}
