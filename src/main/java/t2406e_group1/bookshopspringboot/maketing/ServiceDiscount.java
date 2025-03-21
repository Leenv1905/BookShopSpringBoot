package t2406e_group1.bookshopspringboot.maketing;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ServiceDiscount {
    
    @Autowired
    private JpaDiscount jpaDiscount;

    public ServiceDiscount(JpaDiscount jpaDiscount){
        this.jpaDiscount = jpaDiscount;
    }

          public EntityDiscount saveEntityDiscount(EntityDiscount entityDiscount) {
        return jpaDiscount.save(entityDiscount);
    }

    public List<EntityDiscount> findAll() {
        return jpaDiscount.findAll();
    }

    public Optional<EntityDiscount> findById(int id) {
        return jpaDiscount.findById(id);
    }

    public void deleteById(int id) {
        jpaDiscount.deleteById(id);
    }

    public boolean existsById(int id) {
        return jpaDiscount.existsById(id);
    }





}
