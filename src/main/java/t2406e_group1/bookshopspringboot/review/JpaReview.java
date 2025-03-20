package t2406e_group1.bookshopspringboot.review;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReview extends JpaRepository<EntityReview, Integer>
// Cùng cấp thì ko cần import file
{
// Bạn có thể định nghĩa các phương thức truy vấn tùy chỉnh tại đây
// Optional<EntityReview> findByEmail(String email); // Thêm phương thức tìm kiếm theo email
// List<EntityReview> findByRolesContaining(String roles);  // Tìm User theo vai trò
Optional<EntityReview> findByTenKhach(String tenKhach);
}
