package t2406e_group1.bookshopspringboot.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test2/product")
public class ControllerProductApi {

    @Autowired
    private ServiceProduct serviceProduct;

    // LẤY THÔNG TIN TẤT CẢ SẢN PHẨM
    @GetMapping
    public List<EntityProduct> getAllProduct() {
        return serviceProduct.findAll();
    }

    // LẤY THÔNG TIN SẢN PHẨM THEO ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityProduct> getProductById(@PathVariable int id) {
        Optional<EntityProduct> entityProduct = serviceProduct.findById(id);
        return entityProduct.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build());
    }

    // THÊM MỚI SẢN PHẨM
    @PostMapping
    public EntityProduct createProduct(@RequestBody EntityProduct entityProduct) {
        return serviceProduct.saveEntityProduct(entityProduct);
    }

    // SỬA SẢN PHẨM THEO ID
    @PutMapping("/{id}")
    public ResponseEntity<EntityProduct> updateProduct(@PathVariable int id, @RequestBody EntityProduct productDetails) {
        Optional<EntityProduct> optionalEntityProduct = serviceProduct.findById(id);
        if (optionalEntityProduct.isPresent()) {
            EntityProduct entityProduct = optionalEntityProduct.get();
            entityProduct.setName(productDetails.getName());
            entityProduct.setPrice(productDetails.getPrice());
            entityProduct.setQuantity(productDetails.getQuantity());
            return ResponseEntity.ok(serviceProduct.saveEntityProduct(entityProduct));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // XÓA SẢN PHẨM THEO ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        if (serviceProduct.existsById(id)) {
            serviceProduct.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
