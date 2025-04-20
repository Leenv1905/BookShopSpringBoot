package t2406e_group1.bookshopspringboot.maketing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/discounts")
@CrossOrigin(origins = "http://localhost:3000") // Bật CORS cho địa chỉ này
public class ControllerDiscount {

    @Autowired
    private ServiceDiscount serviceDiscount;

    @GetMapping
    public ResponseEntity<List<EntityDiscount>> getAllDiscounts() {
        List<EntityDiscount> discounts = serviceDiscount.findAll();
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityDiscount> getDiscountById(@PathVariable int id) {
        Optional<EntityDiscount> discount = serviceDiscount.findById(id);
        return discount.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/saleprice/{salePrice}")
    public ResponseEntity<List<EntityDiscount>> getDiscountsBySalePrice(@PathVariable float salePrice) {
        List<EntityDiscount> discounts = serviceDiscount.findBySalePrice(salePrice);
        if (discounts.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(discounts);
    }

    @PostMapping
    public ResponseEntity<EntityDiscount> createDiscount(@RequestBody EntityDiscount entityDiscount) {
        if (entityDiscount.getProduct() == null || entityDiscount.getProduct().getId() == 0) {
            return ResponseEntity.badRequest().build(); // Kiểm tra product không null và có ID hợp lệ
        }
        EntityDiscount savedDiscount = serviceDiscount.saveEntityDiscount(entityDiscount);
        return ResponseEntity.ok(savedDiscount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityDiscount> updateDiscount(@PathVariable int id, @RequestBody EntityDiscount updatedDiscount) {
        Optional<EntityDiscount> existingDiscount = serviceDiscount.findById(id);
        if (existingDiscount.isPresent()) {
            if (updatedDiscount.getProduct() == null || updatedDiscount.getProduct().getId() == 0) {
                return ResponseEntity.badRequest().build(); // Kiểm tra product không null và có ID hợp lệ
            }
            updatedDiscount.setId(id);
            EntityDiscount savedDiscount = serviceDiscount.saveEntityDiscount(updatedDiscount);
            return ResponseEntity.ok(savedDiscount);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable int id) {
        if (serviceDiscount.existsById(id)) {
            serviceDiscount.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}