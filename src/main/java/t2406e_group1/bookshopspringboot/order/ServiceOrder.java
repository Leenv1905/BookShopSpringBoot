// package t2406e_group1.bookshopspringboot.order;

// public class ServiceOrder {
    
// }

package t2406e_group1.bookshopspringboot.order;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import t2406e_group1.bookshopspringboot.review.JpaReview;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceOrder {

    // private static final Logger logger = LoggerFactory.getLogger(ServiceOrder.class);

    @Autowired
    private JpaOrder jpaOrder; // Repository để thao tác với database

        public ServiceOrder(JpaOrder jpaOrder) {
        this.jpaOrder = jpaOrder;
    }

    public List<EntityOrder> findAll() {
            return jpaOrder.findAll();
    }

    public Optional<EntityOrder> findById(int id) {
            return jpaOrder.findById(id);
    }

    public EntityOrder saveEntityOrder(EntityOrder entityOrder) { 
            return jpaOrder.save(entityOrder);
    }
    
    public void deleteById(int id) {

            jpaOrder.deleteById(id);

    }

    // public EntityOrder saveEntityOrder(EntityOrder entityOrder) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'saveEntityOrder'");
    // }
}