package t2406e_group1.bookshopspringboot.order.details;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t2406e_group1.bookshopspringboot.order.EntityOrder;
import t2406e_group1.bookshopspringboot.product.EntityProduct;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@Table(name = "order_details")
public class EntityDetailsOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference
    private EntityOrder order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private EntityProduct product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double price;

    public EntityDetailsOrder() {
    }
}