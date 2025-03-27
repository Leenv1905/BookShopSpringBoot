package t2406e_group1.bookshopspringboot.import_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service // Đánh dấu lớp này là một Service trong Spring
public class ServiceImportProduct {

    @Autowired
    private JpaImportProduct jpaImportProduct; // Repository để thao tác với cơ sở dữ liệu

    /**
     * Lấy danh sách tất cả sản phẩm nhập kho từ cơ sở dữ liệu.
     *
     * @return Danh sách các sản phẩm nhập kho
     */
    public List<EntityImportProduct> getAllImportProducts() {
        try {
            return jpaImportProduct.findAll(); //  Đã sửa lỗi
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách sản phẩm nhập kho", e);
        }
    }

    /**
     * Tìm kiếm một sản phẩm nhập kho theo ID.
     *
     * @param id ID của sản phẩm nhập kho cần tìm
     * @return Đối tượng sản phẩm nhập kho nếu tìm thấy
     * @throws RuntimeException nếu không tìm thấy sản phẩm với ID tương ứng
     */
    public EntityImportProduct getImportProductById(int id) {
        Optional<EntityImportProduct> product = jpaImportProduct.findById(id); //  Đã sửa lỗi
        return product.orElseThrow(() -> new EntityNotFoundException ("Không tìm thấy sản phẩm với ID: " + id));
    }

    /**
     * Thêm một sản phẩm nhập kho mới vào cơ sở dữ liệu.
     *
     * @param importProduct Đối tượng sản phẩm nhập kho cần thêm
     * @return Đối tượng sản phẩm nhập kho sau khi lưu vào cơ sở dữ liệu
     */
    public EntityImportProduct createImportProduct(EntityImportProduct importProduct) {
        return jpaImportProduct.save(importProduct); // Lưu sản phẩm nhập kho vào DB
    }

    /**
     * Cập nhật thông tin sản phẩm nhập kho dựa trên ID.
     *
     * @param id ID của sản phẩm nhập kho cần cập nhật
     * @param importProduct Dữ liệu sản phẩm nhập kho mới
     * @return Đối tượng sản phẩm nhập kho sau khi cập nhật
     * @throws RuntimeException nếu không tìm thấy sản phẩm với ID tương ứng
     */
    public EntityImportProduct updateImportProduct(int id, EntityImportProduct importProduct) {
        EntityImportProduct existingProduct = getImportProductById(id); // Lấy sản phẩm hiện có theo ID
        
        existingProduct.setQuantity(importProduct.getQuantity()); // Cập nhật số lượng nhập kho
         existingProduct.setPrice(importProduct.getPrice()); // Cập nhật giá nhập kho
        // existingProduct.setDateImport(importProduct.getDateImport()); // Cập nhật ngày nhập kho
        return jpaImportProduct.save(existingProduct); // Lưu thông tin cập nhật vào DB
    }

    /**
     * Xóa một sản phẩm nhập kho khỏi cơ sở dữ liệu dựa trên ID.
     *
     * @param id ID của sản phẩm nhập kho cần xóa
     */
    public void deleteImportProduct(int id) {
        jpaImportProduct.deleteById(id); // Xóa sản phẩm nhập kho khỏi DB theo ID
    }
}
