// package t2406e_group1.bookshopspringboot.order;

// public class EntityOrder {
    
// }

package t2406e_group1.bookshopspringboot.order;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import t2406e_group1.bookshopspringboot.user.EntityUser;
import java.util.Date;

@Entity
@Getter
@Setter
// @Table(name = "orders") // Tránh lỗi vì "order" là từ khóa SQL
// @Data
public class EntityOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "order_id") // Đảm bảo trùng với cột trong DB
    private int id;

    // @ManyToOne
    // @JoinColumn(name = "user_id", nullable = false)
    // private EntityUser user;

    // @Temporal(TemporalType.TIMESTAMP)
    // @Column(name = "order_date", nullable = false)
    private Date orderDate;

    // @Column(nullable = false, length = 50)
    private String status;
    
    // @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    // @Temporal(TemporalType.TIMESTAMP)
    // @Column(name = "date_added", nullable = false, updatable = false, insertable = false)
    // private Date dateAdded;

    public EntityOrder() // hàm khởi tạo
    {
        // đảm bảo rằng một số trường thông tin quan trọng không bị NULL
        // khi nó được dùng để cung cấp dữ liệu ra bên ngoài.

    }
}
