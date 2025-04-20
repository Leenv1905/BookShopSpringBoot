package t2406e_group1.bookshopspringboot.maketing;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import t2406e_group1.bookshopspringboot.product.EntityProduct;

@Entity
@Table(name = "entity_discount") // Đặt tên bảng trong DB là "entity_discount"
@Getter
@Setter
public class EntityDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false) // Khóa ngoại liên kết với bảng EntityProduct
    private EntityProduct product; // Thay productId bằng đối tượng EntityProduct

    @Column(nullable = false)
    private float salePrice;

    @Column(nullable = false)
    private Date dateCreate;

    @Column(nullable = false)
    private Date dateStart;

    @Column(nullable = false)
    private Date dateEnd;

    @Column(nullable = false)
    private int quantity;

    public EntityDiscount() {
        // Hàm khởi tạo
    }

    public Boolean InputError() {
        boolean ipe = false;

        if (this.salePrice < 1) {
            ipe = true;
            print("\n Lỗi->Giá phải lớn hơn 1: ");
        }

        if (this.salePrice > 9000000) {
            ipe = true;
            print("\n Lỗi->Giá phải dưới 9000000. ");
        }

        // Kiểm tra xem product có null hay không
        if (this.product == null) {
            ipe = true;
            print("\n Lỗi->Sản phẩm không được để trống: ");
        }

        return ipe;
    }

    private void print(String string) {
        // Thay thế bằng System.out.println để xử lý lỗi tạm thời
        System.out.println(string);
    }
}