package t2406e_group1.bookshopspringboot.product.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImage {
    private final JpaImage jpaImage;
    
    @Autowired
    public ServiceImage(JpaImage jpaImage) {
        this.jpaImage = jpaImage;
    }

    public List<EntityImage> findAll() {
        return jpaImage.findAll();
    }

    public Optional<EntityImage> findById(int id) {
        return jpaImage.findById(id);
    }

    public EntityImage saveEntityImage(EntityImage entityImage) {
        return jpaImage.save(entityImage);
    }

    public boolean existsById(int id) {
        return jpaImage.existsById(id);
    }

    public void deleteById(int id) {
        jpaImage.deleteById(id);
    }
}
