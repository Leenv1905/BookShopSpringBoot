package t2406e_group1.bookshopspringboot.product.image;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import t2406e_group1.bookshopspringboot.product.EntityProduct;

@Entity
@Getter
@Setter
public class EntityImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String imagePath; // URL của ảnh
    //private int productId; // ID của sản phẩm mà ảnh này thuộc về

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private EntityProduct product;

    
    public Boolean InputError() {
        var ipe = false;

        if (this.imagePath == null || this.imagePath.isEmpty()) {
            ipe = true;
            print("\n Lỗi->URL ảnh không được để trống: ");
        }

        // if (this.productId <= 0) {
        //     ipe = true;
        //     print("\n Lỗi->ID sản phẩm không hợp lệ: ");
        // }

        return ipe;
    }

    private void print(String string) {
        throw new UnsupportedOperationException("Unimplemented method 'print'");
    }
}
