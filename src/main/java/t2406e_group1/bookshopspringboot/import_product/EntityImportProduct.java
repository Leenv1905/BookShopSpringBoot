package t2406e_group1.bookshopspringboot.import_product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@Table(name = "entity_import_product")
public class EntityImportProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date importDate;

    @Column(nullable = false)
    private int totalQuantity;

    @OneToMany(mappedBy = "importProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<EntityImportProductDetail> details = new ArrayList<>();

}

// Xử lý vòng lặp vô hạn trong phản hồi API. Vấn đề này thường xảy ra do mối quan hệ hai chiều (@OneToMany và @ManyToOne) giữa EntityImportProduct và EntityImportProductDetail gây ra vòng lặp khi tuần tự hóa JSON. Tôi sẽ sửa bằng cách sử dụng @JsonManagedReference và @JsonBackReference để phá vỡ vòng lặp.
// Phân tích vấn đề vòng lặp vô hạn
// Vòng lặp vô hạn thường xảy ra khi Jackson (thư viện tuần tự hóa JSON của Spring) cố gắng serialize các thực thể có quan hệ hai chiều. Trong trường hợp của bạn:

// EntityImportProduct có @OneToMany tới List<EntityImportProductDetail>.
// EntityImportProductDetail có @ManyToOne tới EntityImportProduct.
// Khi serialize, Jackson sẽ cố gắng serialize EntityImportProduct → details → EntityImportProductDetail → importProduct → details → ... dẫn đến vòng lặp vô hạn.

// Giải pháp:

// Sử dụng @JsonManagedReference trong EntityImportProduct cho trường details.
// Sử dụng @JsonBackReference trong EntityImportProductDetail cho trường importProduct.
// Đảm bảo các quan hệ khác (như EntityProduct và EntitySupplier) không gây ra vấn đề tương tự.