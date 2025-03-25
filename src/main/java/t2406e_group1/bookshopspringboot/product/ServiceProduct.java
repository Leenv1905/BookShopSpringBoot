package t2406e_group1.bookshopspringboot.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// Sử dụng constructor injection thay vì field injection:
public class ServiceProduct {
    private final JpaProduct jpaProduct;
    
    @Autowired
    public ServiceProduct(JpaProduct jpaProduct) {
        this.jpaProduct = jpaProduct;
    }

    public List<EntityProduct> findAll() {
        return jpaProduct.findAll();
    }

    public Optional<EntityProduct> findById(int id) {
        return jpaProduct.findById(id);
    }

    public EntityProduct saveEntityProduct(EntityProduct entityProduct) {
        return jpaProduct.save(entityProduct);
    }

    public boolean existsById(int id) {
        return jpaProduct.existsById(id);
    }

    public void deleteById(int id) {
        jpaProduct.deleteById(id);
    }
}