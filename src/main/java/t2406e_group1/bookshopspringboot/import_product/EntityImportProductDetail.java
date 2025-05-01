package t2406e_group1.bookshopspringboot.import_product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t2406e_group1.bookshopspringboot.product.EntityProduct;
import t2406e_group1.bookshopspringboot.supplier.EntitySupplier;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@Table(name = "entity_import_product_details")
public class EntityImportProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_product_id", nullable = false)
    // Sử dụng @JsonBackReference để tránh vòng lặp vô hạn khi tuần tự hóa JSON
    @JsonBackReference
    private EntityImportProduct importProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private EntityProduct product;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private float importPrice;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", nullable = false)
    private EntitySupplier supplier;

}