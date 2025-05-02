package t2406e_group1.bookshopspringboot.product.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t2406e_group1.bookshopspringboot.product.EntityProduct;
import t2406e_group1.bookshopspringboot.product.JpaProduct;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImage {
    private final JpaImage jpaImage;
    private final JpaProduct jpaProduct;

    @Autowired
    public ServiceImage(JpaImage jpaImage, JpaProduct jpaProduct) {
        this.jpaImage = jpaImage;
        this.jpaProduct = jpaProduct;
    }

    public List<EntityImage> findAll() {
        return jpaImage.findAll();
    }

    public Optional<EntityImage> findById(int id) {
        return jpaImage.findById(id);
    }

    public EntityImage saveEntityImage(EntityImage entityImage) {
        if (entityImage.getProduct() != null && entityImage.getProduct().getId() > 0) {
            Optional<EntityProduct> productOpt = jpaProduct.findById(entityImage.getProduct().getId());
            if (productOpt.isPresent()) {
                entityImage.setProduct(productOpt.get());
            } else {
                throw new IllegalArgumentException("Product not found for ID: " + entityImage.getProduct().getId());
            }
        } else {
            throw new IllegalArgumentException("Product or product ID is invalid");
        }
        return jpaImage.save(entityImage);
    }

    public boolean existsById(int id) {
        return jpaImage.existsById(id);
    }

    public void deleteById(int id) {
        jpaImage.deleteById(id);
    }
}