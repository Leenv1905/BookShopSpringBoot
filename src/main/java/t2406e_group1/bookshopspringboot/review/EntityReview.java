package t2406e_group1.bookshopspringboot.review;
// Nếu bạn dùng Hibernate để tự động tạo bảng, chỉ cần khởi động lại ứng dụng sau khi sửa entity.
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

// import static java.io.IO.print;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EntityReview {
    @Id // Đánh dấu id là khóa chính
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tự động tăng
    private int id; // id của user
    private String tenKhach; // Tên đầy đủ user
    private Integer sdt; // Số điện thoại

    public EntityReview() // hàm khởi tạo
    {
        // đảm bảo rằng một số trường thông tin quan trọng không bị NULL
        // khi nó được dùng để cung cấp dữ liệu ra bên ngoài.

    }

    public Boolean InputError() {  // KHÔNG HỢP LỆ
        var ipe = false;

        // if (this.fullName.length() < 2) {
        // ipe = true;
        // print("\n Lỗi->Tên phải từ 2 kí tự trở lên: ");
        // }

        // if (this.fullName.length() > 22) {
        // ipe = true;
        // print("\n Lỗi->Tên phải không quá 22 kí tự. ");
        // }

        if (this.tenKhach.length() < 2) {
            ipe = true;
            print("\n Lỗi->Tên phải từ 2 kí tự trở lên: ");
        }

        if (this.tenKhach.length() > 22) {
            ipe = true;
            print("\n Lỗi->Tên phải không quá 22 kí tự. ");
        }

        // if (this.phoneNumber.toString().length() < 10) {
        //     ipe = true;
        //     print("\n Lỗi->Số điện thoại phải từ 10 số trở lên: ");
        // }

        // THÊM CÁC PHƯƠNG THỨC VALIDATE KHÁC Ở ĐÂY

        return ipe;
    }

    private void print(String string) {
        // Khai báo phương thức print
        throw new UnsupportedOperationException("Unimplemented method 'print'");
    }


}
