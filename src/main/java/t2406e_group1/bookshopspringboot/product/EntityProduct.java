package t2406e_group1.bookshopspringboot.product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
// import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import t2406e_group1.bookshopspringboot.product.image.EntityImage;

@Entity
@Getter
@Setter
public class EntityProduct {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String name; // Tên sản phẩm
    private Float price; // Giá sản phẩm
    private Integer quantity; // Số lượng trong kho
    private Date dateAdded; // Ngày thêm sản phẩm
    private String author; // Tác giả
    private String description; // Mô tả sản phẩm
    private String content; //Nội dung
    private String language; // Ngôn ngữ
    private String category; // Thể loại
    private Boolean status; // Trạng thái sản phẩm (còn bán hay không)

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<EntityImage> images = new ArrayList<>();

    public EntityProduct() {
         this.status = true; // Mặc định sản phẩm còn bán
    }

    public Boolean InputError() {
        var ipe = false;

        if (this.name == null || this.name.length() < 2) {
            ipe = true;
            System.out.println("\n Lỗi->Tên sản phẩm phải từ 2 kí tự trở lên");
        }

        if (this.price == null || this.price <= 0) {
            ipe = true;
            System.out.println("\n Lỗi->Giá sản phẩm phải lớn hơn 0");
        }

        if (this.quantity == null || this.quantity < 0) {
            ipe = true;
            System.out.println("\n Lỗi->Số lượng sản phẩm không được âm");
        }

        return ipe;
    }

    // private void print(String string) {
    //     // Khai báo phương thức print
    //     throw new UnsupportedOperationException("Unimplemented method 'print'");
    // }
}
