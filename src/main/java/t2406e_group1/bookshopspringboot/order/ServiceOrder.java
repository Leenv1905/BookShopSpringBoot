package t2406e_group1.bookshopspringboot.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import t2406e_group1.bookshopspringboot.order.details.EntityDetailsOrder;
import t2406e_group1.bookshopspringboot.order.details.JpaDetailsOrder;
import t2406e_group1.bookshopspringboot.product.EntityProduct;
import t2406e_group1.bookshopspringboot.product.JpaProduct;
import t2406e_group1.bookshopspringboot.user.EntityUser;
import t2406e_group1.bookshopspringboot.user.JpaUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceOrder {

    @Autowired
    private JpaOrder jpaOrder;

    @Autowired
    private JpaDetailsOrder jpaDetailsOrder;

    @Autowired
    private JpaUser jpaUser;

    @Autowired
    private JpaProduct jpaProduct;

    public ServiceOrder(JpaOrder jpaOrder, JpaDetailsOrder jpaDetailsOrder, JpaUser jpaUser, JpaProduct jpaProduct) {
        this.jpaOrder = jpaOrder;
        this.jpaDetailsOrder = jpaDetailsOrder;
        this.jpaUser = jpaUser;
        this.jpaProduct = jpaProduct;
    }

    public List<EntityOrder> findAll() {
        return jpaOrder.findAll();
    }

    public Optional<EntityOrder> findById(int id) {
        return jpaOrder.findById(id);
    }

    @Transactional
    public EntityOrder saveEntityOrder(EntityOrder entityOrder, List<EntityDetailsOrder> orderDetails) {
        // Gán order cho mỗi detail
        for (EntityDetailsOrder detail : orderDetails) {
            detail.setOrder(entityOrder);
        }
        entityOrder.setOrderDetails(orderDetails);
        // Tính tổng tiền
        double total = orderDetails.stream()
                .mapToDouble(detail -> detail.getPrice() * detail.getQuantity())
                .sum();
        entityOrder.setTotal(total);
        return jpaOrder.save(entityOrder);
    }

    public void deleteById(int id) {
        jpaOrder.deleteById(id);
    }
}