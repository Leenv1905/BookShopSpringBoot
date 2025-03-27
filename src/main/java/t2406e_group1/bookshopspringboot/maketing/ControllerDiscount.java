package t2406e_group1.bookshopspringboot.maketing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/discounts") // Endpoint chính của API
public class ControllerDiscount {

    @Autowired
    private ServiceDiscount serviceDiscount;

    //  LẤY DANH SÁCH TẤT CẢ KHUYẾN MÃI
    @GetMapping
    public ResponseEntity<List<EntityDiscount>> getAllDiscounts() {
        List<EntityDiscount> discounts = serviceDiscount.findAll();
        return ResponseEntity.ok(discounts);
    }

    //  LẤY THÔNG TIN KHUYẾN MÃI THEO ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityDiscount> getDiscountById(@PathVariable int id) {
        Optional<EntityDiscount> discount = serviceDiscount.findById(id);
        return discount.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //  LẤY KHUYẾN MÃI THEO GIÁ SALE
    @GetMapping("/saleprice/{salePrice}")
    public ResponseEntity<List<EntityDiscount>> getDiscountsBySalePrice(@PathVariable float salePrice) {
        List<EntityDiscount> discounts = serviceDiscount.findBySalePrice(salePrice);
        if (discounts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(discounts);
    }
      
    //  THÊM KHUYẾN MÃI MỚI
    @PostMapping
    public ResponseEntity<EntityDiscount> createDiscount(@RequestBody EntityDiscount entityDiscount) {
        EntityDiscount savedDiscount = serviceDiscount.saveEntityDiscount(entityDiscount);
        return ResponseEntity.ok(savedDiscount);
    }

    //  CẬP NHẬT THÔNG TIN KHUYẾN MÃI
    @PutMapping("/{id}")
    public ResponseEntity<EntityDiscount> updateDiscount(@PathVariable int id, @RequestBody EntityDiscount updatedDiscount) {
        Optional<EntityDiscount> existingDiscount = serviceDiscount.findById(id);
        if (existingDiscount.isPresent()) {
            updatedDiscount.setId(id); // Đảm bảo ID không thay đổi
            EntityDiscount savedDiscount = serviceDiscount.saveEntityDiscount(updatedDiscount);
            return ResponseEntity.ok(savedDiscount);
        }
        return ResponseEntity.notFound().build();
    }

    // XOÁ KHUYẾN MÃI THEO ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable int id) {
        if (serviceDiscount.existsById(id)) {
            serviceDiscount.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
