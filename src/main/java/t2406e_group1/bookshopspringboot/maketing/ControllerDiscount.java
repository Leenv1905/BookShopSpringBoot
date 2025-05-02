package t2406e_group1.bookshopspringboot.maketing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/discounts")
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerDiscount {

    @Autowired
    private ServiceDiscount serviceDiscount;

    @Autowired
    private JpaDiscountProduct jpaDiscountProduct;

    @GetMapping
    public ResponseEntity<List<DiscountDTO>> getAllDiscounts() {
        List<DiscountDTO> discounts = serviceDiscount.getAllDiscount();
        return ResponseEntity.ok(discounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscountDTO> getDiscountById(@PathVariable Integer id) {
        Optional<EntityDiscount> discountOpt = serviceDiscount.findById(id);
        if (!discountOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        EntityDiscount discount = discountOpt.get();
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setId(discount.getId());
        discountDTO.setDateCreate(discount.getDateCreate());
        discountDTO.setDateStart(discount.getDateStart());
        discountDTO.setDateEnd(discount.getDateEnd());

        List<DiscountProductDTO> productDTOs = jpaDiscountProduct.findByDiscount(discount).stream()
            .map(dp -> new DiscountProductDTO(
                dp.getProduct().getId(),
                dp.getProduct().getName(),
                dp.getProduct().getPrice(),
                dp.getSalePrice(),
                dp.getQuantity()
            ))
            .collect(Collectors.toList());

        discountDTO.setDiscountProducts(productDTOs);
        return ResponseEntity.ok(discountDTO);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<DiscountProductDTO>> getDiscountProducts(@PathVariable Integer id) {
        Optional<EntityDiscount> discountOpt = serviceDiscount.findById(id);
        if (!discountOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<DiscountProduct> discountProducts = jpaDiscountProduct.findByDiscount(discountOpt.get());
        List<DiscountProductDTO> productDTOs = discountProducts.stream()
            .map(dp -> new DiscountProductDTO(
                dp.getProduct().getId(),
                dp.getProduct().getName(),
                dp.getProduct().getPrice(),
                dp.getSalePrice(),
                dp.getQuantity()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(productDTOs);
    }

    @PostMapping
    public ResponseEntity<?> createDiscount(@RequestBody DiscountDTO discountDTO) {
        try {
            EntityDiscount discount = serviceDiscount.createDiscount(discountDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(discount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating discount: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiscount(@PathVariable Integer id, @RequestBody DiscountDTO discountDTO) {
        try {
            EntityDiscount updatedDiscount = serviceDiscount.updateDiscount(id, discountDTO);
            return ResponseEntity.ok(updatedDiscount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating discount: " + e.getMessage());
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