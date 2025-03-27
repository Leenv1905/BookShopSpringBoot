package t2406e_group1.bookshopspringboot.import_product;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface JpaImportProduct đóng vai trò là tầng truy vấn dữ liệu (DAO) 
 * cho bảng "import_product" trong cơ sở dữ liệu.
 * 
 * JpaRepository<EntityImportProduct, int> giúp tự động cung cấp các phương thức 
 * như tìm kiếm, lưu, cập nhật và xóa dữ liệu trong database.
 */
@Repository
public interface JpaImportProduct extends JpaRepository<EntityImportProduct, Integer> {
    // Nếu cần các phương thức truy vấn tùy chỉnh, có thể khai báo tại đây
}
