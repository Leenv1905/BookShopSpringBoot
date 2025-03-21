package t2406e_group1.bookshopspringboot.maketing;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
// @Table(name = "discount")
// Nếu để cái này sẽ đặt tên bảng theo ý muốn
@Getter
@Setter
public class EntityDiscount {

        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Float salePrice;
    private Date dateCreate;
    private Date dateStart;
    private Date dateEnd;
    
    public EntityDiscount() // hàm khởi tạo
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

        if (this.salePrice < 1) {
            ipe = true;
            print("\n Lỗi->Giá phải lớn hơn 1: ");
        }

        if (this.salePrice > 9000000) {
            ipe = true;
            print("\n Lỗi->Giá phải dưới 9000000. ");
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
