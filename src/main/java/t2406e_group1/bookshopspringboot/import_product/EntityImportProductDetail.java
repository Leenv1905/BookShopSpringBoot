package t2406e_group1.bookshopspringboot.import_product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t2406e_group1.bookshopspringboot.product.EntityProduct;
import t2406e_group1.bookshopspringboot.supplier.EntitySupplier;

@Entity
@Table(name = "entity_import_product_details")
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EntityImportProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_product_id")
    // Sử dụng @JsonBackReference để tránh vòng lặp vô hạn khi tuần tự hóa JSON
    // giữa EntityImportProduct và EntityImportProductDetail
    @JsonBackReference
    private EntityImportProduct importProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private EntityProduct product;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "import_price")
    private Double importPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private EntitySupplier supplier;
}