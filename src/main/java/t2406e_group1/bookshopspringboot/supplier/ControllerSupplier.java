package t2406e_group1.bookshopspringboot.supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/supplier")
public class ControllerSupplier {

    @Autowired
    private ServiceSupplier supplierService;

    // Lấy danh sách tất cả nhà cung cấp
    @GetMapping
    public ResponseEntity<List<EntitySupplier>> getAllSuppliers() {
        List<EntitySupplier> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    // Lấy nhà cung cấp theo ID
    @GetMapping("/{id}")
    public ResponseEntity<EntitySupplier> getSupplierById(@PathVariable int id) {
        Optional<EntitySupplier> supplier = supplierService.getSupplierById(id);
        return supplier.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Thêm nhà cung cấp mới
    @PostMapping
    public ResponseEntity<EntitySupplier> createSupplier(@RequestBody EntitySupplier supplier) {
        EntitySupplier newSupplier = supplierService.saveSupplier(supplier);
        return ResponseEntity.ok(newSupplier);
    }

    // Cập nhật thông tin nhà cung cấp
    @PutMapping("/{id}")
    public ResponseEntity<EntitySupplier> updateSupplier(@PathVariable int id, @RequestBody EntitySupplier supplier) {
        Optional<EntitySupplier> existingSupplier = supplierService.getSupplierById(id);
        if (existingSupplier.isPresent()) {
            supplier.setId(id);
            EntitySupplier updatedSupplier = supplierService.saveSupplier(supplier);
            return ResponseEntity.ok(updatedSupplier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa nhà cung cấp
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable int id) {
        Optional<EntitySupplier> existingSupplier = supplierService.getSupplierById(id);
        if (existingSupplier.isPresent()) {
            supplierService.deleteSupplier(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
