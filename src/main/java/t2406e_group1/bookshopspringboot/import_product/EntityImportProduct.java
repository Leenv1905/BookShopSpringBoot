package t2406e_group1.bookshopspringboot.import_product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.util.Date;
// import t2406e_group1.bookshopspringboot.product.Product; // Import đúng package
import t2406e_group1.bookshopspringboot.supplier.EntitySupplier; // Import đúng package

@Entity
@Getter
@Setter
@Table(name = "entity_import_product") // Đặt tên bảng chuẩn
public class EntityImportProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Khóa chính của bảng nhập sản phẩm

    // @ManyToOne
    // @JoinColumn(name = "product_id", nullable = false) // Đặt tên chuẩn SQL
    // private Product product; // Liên kết với bảng Product

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false) // Đặt tên chuẩn SQL
    private EntitySupplier supplier; // Liên kết với bảng Supplier

    @Column(nullable = false)
    private int quantity; // Số lượng nhập kho

    @Column(nullable = false)
    private float price; // Giá nhập (Sử dụng float)

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date importDate; // Ngày nhập hàng
}
