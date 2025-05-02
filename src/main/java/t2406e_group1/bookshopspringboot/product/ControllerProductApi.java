package t2406e_group1.bookshopspringboot.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t2406e_group1.bookshopspringboot.product.image.EntityImage;
import t2406e_group1.bookshopspringboot.product.image.ServiceImage;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ControllerProductApi {

    @Autowired
    private ServiceProduct serviceProduct;
    
    @Autowired
    private ServiceImage serviceImage;

    // LẤY THÔNG TIN TẤT CẢ SẢN PHẨM
    @GetMapping
    public List<ProductDTO> getAllProduct() {
        return serviceProduct.findAllAsDTO();
    }

    // LẤY THÔNG TIN SẢN PHẨM THEO ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
        Optional<ProductDTO> productDTO = serviceProduct.findByIdAsDTO(id);
        return productDTO.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // LẤY THÔNG TIN CHI TIẾT SẢN PHẨM THEO ID DÙNG RIÊNG CHO PRODUCTDETAIL
    @GetMapping("/{id}/detail")
    public ResponseEntity<ProductDetailDTO> getProductDetailById(@PathVariable int id) {
        Optional<ProductDetailDTO> productDetailDTO = serviceProduct.findByIdAsDetailDTO(id);
        return productDetailDTO.map(ResponseEntity::ok)
                              .orElseGet(() -> ResponseEntity.notFound().build());
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
            entityProduct.setDateAdded(productDetails.getDateAdded());
            entityProduct.setAuthor(productDetails.getAuthor());
            entityProduct.setDescription(productDetails.getDescription());
            entityProduct.setContent(productDetails.getContent());
            entityProduct.setLanguage(productDetails.getLanguage());
            entityProduct.setCategory(productDetails.getCategory());
            entityProduct.setStatus(productDetails.getStatus());
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

    // LẤY THÔNG TIN TẤT CẢ ẢNH
    @GetMapping("/images")
    public List<EntityImage> getAllImages() {
        return serviceImage.findAll();
    }

    // LẤY THÔNG TIN ẢNH THEO ID
    @GetMapping("/{id}/images")
    public ResponseEntity<EntityImage> getProductImageById(@PathVariable int id) {
        Optional<EntityImage> entityImage = serviceImage.findById(id);
        return entityImage.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // THÊM MỚI ẢNH
    @PostMapping("/images")
    public EntityImage createProductImage(@RequestBody ImageRequest imageRequest) {
        EntityImage entityImage = new EntityImage();
        entityImage.setImagePath(imageRequest.getImagePath());
        EntityProduct product = new EntityProduct();
        product.setId(imageRequest.getProduct().getId());
        entityImage.setProduct(product);
        return serviceImage.saveEntityImage(entityImage);
    }

    // SỬA ẢNH THEO ID
    @PutMapping("/{id}/images")
    public ResponseEntity<EntityImage> updateProductImage(@PathVariable int id, @RequestBody EntityImage productDetails) {
        Optional<EntityImage> optionalEntityImage = serviceImage.findById(id);
        if (optionalEntityImage.isPresent()) {
            EntityImage entityImage = optionalEntityImage.get();
            entityImage.setImagePath(productDetails.getImagePath());
            return ResponseEntity.ok(serviceImage.saveEntityImage(entityImage));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // XÓA ẢNH THEO ID
    @DeleteMapping("/{id}/images")
    public ResponseEntity<Void> deleteProductImage(@PathVariable int id) {
        if (serviceImage.existsById(id)) {
            serviceImage.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Inner class for image request
    public static class ImageRequest {
        private String imagePath;
        private ProductId product;

        public String getImagePath() { return imagePath; }
        public void setImagePath(String imagePath) { this.imagePath = imagePath; }
        public ProductId getProduct() { return product; }
        public void setProduct(ProductId product) { this.product = product; }

        public static class ProductId {
            private int id;

            public int getId() { return id; }
            public void setId(int id) { this.id = id; }
        }
    }
}