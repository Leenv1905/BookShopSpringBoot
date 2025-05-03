package t2406e_group1.bookshopspringboot.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t2406e_group1.bookshopspringboot.order.details.EntityDetailsOrder;
import t2406e_group1.bookshopspringboot.user.EntityUser;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class EntityOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private EntityUser user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false, length = 50)
    private String status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<EntityDetailsOrder> orderDetails;

    public EntityOrder() {
        this.orderDate = new Date();
        this.status = "Pending";
    }
}