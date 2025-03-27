package t2406e_group1.bookshopspringboot.import_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/import-products") // Định nghĩa đường dẫn API gốc
public class ControllerImportProductApi {

    private static final Logger logger = LoggerFactory.getLogger(ControllerImportProductApi.class);

    @Autowired
    private ServiceImportProduct serviceImportProduct;

    /**
     * API lấy danh sách tất cả sản phẩm nhập kho.
     * @return ResponseEntity chứa danh sách sản phẩm hoặc lỗi 500 nếu có lỗi.
     */
    @GetMapping
    public ResponseEntity<List<EntityImportProduct>> getAllImportProducts() {
        try {
            List<EntityImportProduct> products = serviceImportProduct.getAllImportProducts();
            return ResponseEntity.ok(products); // Trả về danh sách sản phẩm với mã 200 OK
        } catch (Exception e) {
            logger.error("Lỗi khi lấy danh sách sản phẩm nhập kho", e);
            return ResponseEntity.status(500).body(null); // Trả về lỗi 500 nếu có lỗi
        }
    }

    /**
     * API lấy thông tin chi tiết của một sản phẩm nhập kho theo ID.
     * @param id ID của sản phẩm cần lấy thông tin.
     * @return ResponseEntity chứa sản phẩm hoặc lỗi nếu không tìm thấy.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityImportProduct> getImportProductById(@PathVariable int id) {
        try {
            EntityImportProduct product = serviceImportProduct.getImportProductById(id);
            return ResponseEntity.ok(product); // Trả về sản phẩm với mã 200 OK
        } catch (Exception e) {
            logger.error("Không tìm thấy sản phẩm nhập kho với ID: " + id, e);
            return ResponseEntity.notFound().build(); // Trả về lỗi 404 nếu không tìm thấy
        }
    }

    /**
     * API tạo mới một sản phẩm nhập kho.
     * @param importProduct Đối tượng sản phẩm nhập kho cần thêm.
     * @return ResponseEntity chứa sản phẩm vừa tạo.
     */
    @PostMapping
    public ResponseEntity<EntityImportProduct> createImportProduct(@RequestBody EntityImportProduct importProduct) {
        try {
            EntityImportProduct createdProduct = serviceImportProduct.createImportProduct(importProduct);
            return ResponseEntity.ok(createdProduct); // Trả về sản phẩm mới với mã 200 OK
        } catch (Exception e) {
            logger.error("Lỗi khi tạo sản phẩm nhập kho", e);
            return ResponseEntity.status(500).body(null); // Trả về lỗi 500 nếu có lỗi
        }
        //
        //
        //
        //CHỖ NÀY NÊN THAY TÊN BIẾN VÀ CHÚ THÍCH LÀ ĐƠN HÀNG
        // VÌ NGHIỆP VỤ Ở ĐÂY LÀ NHẬP HÀNG
        // TRÁNH NHẦM LẪN VỀ SAU NÀY
    }

    /**
     * API cập nhật thông tin của một sản phẩm nhập kho.
     * @param id ID của sản phẩm cần cập nhật.
     * @param importProduct Dữ liệu sản phẩm mới.
     * @return ResponseEntity chứa sản phẩm đã cập nhật.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityImportProduct> updateImportProduct(
            @PathVariable int id, @RequestBody EntityImportProduct importProduct) {
        try {
            EntityImportProduct updatedProduct = serviceImportProduct.updateImportProduct(id, importProduct);
            return ResponseEntity.ok(updatedProduct); // Trả về sản phẩm đã cập nhật với mã 200 OK
        } catch (Exception e) {
            logger.error("Lỗi khi cập nhật sản phẩm nhập kho với ID: " + id, e);
            return ResponseEntity.status(500).body(null); // Trả về lỗi 500 nếu có lỗi
        }
    }

    /**
     * API xóa một sản phẩm nhập kho theo ID.
     * @param id ID của sản phẩm cần xóa.
     * @return ResponseEntity với mã 204 nếu xóa thành công.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImportProduct(@PathVariable int id) {
        try {
            serviceImportProduct.deleteImportProduct(id);
            return ResponseEntity.noContent().build(); // Trả về mã 204 No Content nếu xóa thành công
        } catch (Exception e) {
            logger.error("Lỗi khi xóa sản phẩm nhập kho với ID: " + id, e);
            return ResponseEntity.status(500).build(); // Trả về lỗi 500 nếu có lỗi
        }
    }
}
