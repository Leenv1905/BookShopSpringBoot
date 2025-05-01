package t2406e_group1.bookshopspringboot.supplier;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
@Table(name = "entity_supplier") // Đặt tên bảng trong DB
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EntitySupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // Khóa chính

    @Column(nullable = false, length = 100)
    private String name; // Tên nhà cung cấp

    @Column(nullable = false, length = 255)
    private String address; // Địa chỉ nhà cung cấp

    @Column(nullable = false, length = 20, unique = true)
    private String phoneNumber; // Số điện thoại (Không trùng)

    @Column(nullable = false, length = 100, unique = true)
    private String email; // Email (Không trùng)
}
