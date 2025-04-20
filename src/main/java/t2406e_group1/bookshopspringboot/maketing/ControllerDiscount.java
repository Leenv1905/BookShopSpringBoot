package t2406e_group1.bookshopspringboot.maketing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/discounts")
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerDiscount {

    @Autowired
    private ServiceDiscount serviceDiscount;

    @Autowired
    private JpaDiscountProduct jpaDiscountProduct;

    @GetMapping
    public ResponseEntity<List<EntityDiscount>> getAllDiscounts() {
        return ResponseEntity.ok(serviceDiscount.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityDiscount> getDiscountById(@PathVariable Integer id) {
        Optional<EntityDiscount> discount = serviceDiscount.findById(id);
        return discount.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<DiscountProduct>> getDiscountProducts(@PathVariable Integer id) {
        Optional<EntityDiscount> discountOpt = serviceDiscount.findById(id);
        if (!discountOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<DiscountProduct> discountProducts = jpaDiscountProduct.findByDiscount(discountOpt.get());
        return ResponseEntity.ok(discountProducts);
    }

    @PostMapping
    public ResponseEntity<EntityDiscount> createDiscount(@RequestBody DiscountDTO discountDTO) {
        EntityDiscount discount = serviceDiscount.createDiscount(discountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(discount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityDiscount> updateDiscount(@PathVariable Integer id, @RequestBody DiscountDTO discountDTO) {
        try {
            EntityDiscount updatedDiscount = serviceDiscount.updateDiscount(id, discountDTO);
            return ResponseEntity.ok(updatedDiscount);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable Integer id) {
        if (serviceDiscount.existsById(id)) {
            serviceDiscount.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}