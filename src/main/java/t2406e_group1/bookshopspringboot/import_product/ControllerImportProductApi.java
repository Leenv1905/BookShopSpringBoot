package t2406e_group1.bookshopspringboot.import_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/import-products")
public class ControllerImportProductApi {

    private static final Logger logger = LoggerFactory.getLogger(ControllerImportProductApi.class);

    @Autowired
    private ServiceImportProduct serviceImportProduct;

    @GetMapping
    public ResponseEntity<List<EntityImportProduct>> getAllImportProducts() {
        try {
            List<EntityImportProduct> importProducts = serviceImportProduct.getAllImportProducts();
            return ResponseEntity.ok(importProducts);
        } catch (Exception e) {
            logger.error("Lỗi khi lấy danh sách phiếu nhập hàng", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityImportProduct> getImportProductById(@PathVariable int id) {
        try {
            EntityImportProduct importProduct = serviceImportProduct.getImportProductById(id);
            return ResponseEntity.ok(importProduct);
        } catch (Exception e) {
            logger.error("Không tìm thấy phiếu nhập hàng với ID: " + id, e);
            return ResponseEntity.notFound().build();
        }
    }
//     @GetMapping("/{id}")
// public ResponseEntity<EntityImportProduct> getImportProductById(
//         @PathVariable int id,
//         @RequestParam(defaultValue = "true") boolean includeImages) {
//     try {
//         EntityImportProduct importProduct = serviceImportProduct.getImportProductById(id);
//         if (!includeImages) {
//             importProduct.getDetails().forEach(detail -> detail.getProduct().setImages(null));
//         }
//         return ResponseEntity.ok(importProduct);
//     } catch (Exception e) {
//         logger.error("Không tìm thấy phiếu nhập hàng với ID: " + id, e);
//         return ResponseEntity.notFound().build();
//     }
// }
// Trong ControllerImportProductApi, thêm tham số query (ví dụ: includeImages=false) để cho phép client quyết định có lấy images hay không.
// Client có thể gọi /api/import-products/1?includeImages=false để bỏ images, giảm kích thước phản hồi khi không cần.

    @PostMapping
    public ResponseEntity<?> createImportProduct(@Valid @RequestBody ImportProductDTO importProductDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            fieldError -> fieldError.getField(),
                            fieldError -> fieldError.getDefaultMessage(),
                            (existing, replacement) -> existing, HashMap::new
                    ));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            EntityImportProduct createdImportProduct = serviceImportProduct.createImportProduct(importProductDTO);
            return ResponseEntity.ok(createdImportProduct);
        } catch (Exception e) {
            logger.error("Lỗi khi tạo phiếu nhập hàng", e);
            return ResponseEntity.status(500).body("Lỗi server: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateImportProduct(@PathVariable int id, @Valid @RequestBody ImportProductDTO importProductDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            fieldError -> fieldError.getField(),
                            fieldError -> fieldError.getDefaultMessage(),
                            (existing, replacement) -> existing, HashMap::new
                    ));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            EntityImportProduct updatedImportProduct = serviceImportProduct.updateImportProduct(id, importProductDTO);
            return ResponseEntity.ok(updatedImportProduct);
        } catch (Exception e) {
            logger.error("Lỗi khi cập nhật phiếu nhập hàng với ID: " + id, e);
            return ResponseEntity.status(500).body("Lỗi server: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImportProduct(@PathVariable int id) {
        try {
            serviceImportProduct.deleteImportProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Lỗi khi xóa phiếu nhập hàng với ID: " + id, e);
            return ResponseEntity.status(500).build();
        }
    }
}