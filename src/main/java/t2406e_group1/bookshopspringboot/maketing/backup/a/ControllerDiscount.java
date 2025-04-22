package t2406e_group1.bookshopspringboot.maketing.backup.a;
// package t2406e_group1.bookshopspringboot.maketing;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// @RestController
// @RequestMapping("/api/discounts")
// @CrossOrigin(origins = "http://localhost:3000")
// public class ControllerDiscount {

//     @Autowired
//     private ServiceDiscount serviceDiscount;

//     @Autowired
//     private JpaDiscountProduct jpaDiscountProduct;

//     @GetMapping
//     public ResponseEntity<List<EntityDiscount>> getAllDiscounts() {
//         return ResponseEntity.ok(serviceDiscount.findAll());
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<EntityDiscount> getDiscountById(@PathVariable Integer id) {
//         Optional<EntityDiscount> discount = serviceDiscount.findById(id);
//         return discount.map(ResponseEntity::ok)
//                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//     }

//     @GetMapping("/{id}/products")
//     public ResponseEntity<List<DiscountProductDetailsDTO>> getDiscountProducts(@PathVariable Integer id) {
//         Optional<EntityDiscount> discountOpt = serviceDiscount.findById(id);
//         if (!discountOpt.isPresent()) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//         }
//         List<DiscountProduct> discountProducts = jpaDiscountProduct.findByDiscount(discountOpt.get());
//         List<DiscountProductDetailsDTO> productDetails = discountProducts.stream()
//             .map(dp -> new DiscountProductDetailsDTO(
//                 dp.getProduct().getId(),
//                 dp.getProduct().getName(),
//                 dp.getProduct().getQuantity(),
//                 dp.getProduct().getPrice(),
//                 dp.getSalePrice(),
//                 dp.getQuantity()
//             ))
//             .collect(Collectors.toList());
//         return ResponseEntity.ok(productDetails);
//     }

//     @PostMapping
//     public ResponseEntity<EntityDiscount> createDiscount(@RequestBody DiscountDTO discountDTO) {
//         try {
//             EntityDiscount discount = serviceDiscount.createDiscount(discountDTO);
//             return ResponseEntity.status(HttpStatus.CREATED).body(discount);
//         } catch (IllegalArgumentException e) {
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//         }
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<EntityDiscount> updateDiscount(@PathVariable Integer id, @RequestBody DiscountDTO discountDTO) {
//         try {
//             EntityDiscount updatedDiscount = serviceDiscount.updateDiscount(id, discountDTO);
//             return ResponseEntity.ok(updatedDiscount);
//         } catch (IllegalArgumentException e) {
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//         } catch (RuntimeException e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteDiscount(@PathVariable Integer id) {
//         if (serviceDiscount.existsById(id)) {
//             serviceDiscount.deleteById(id);
//             return ResponseEntity.noContent().build();
//         }
//         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//     }
// }