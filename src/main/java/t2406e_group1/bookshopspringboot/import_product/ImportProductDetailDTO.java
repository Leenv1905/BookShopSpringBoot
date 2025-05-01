package t2406e_group1.bookshopspringboot.import_product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ImportProductDetailDTO {

    @NotNull(message = "ID sản phẩm không được để trống")
    @Positive(message = "ID sản phẩm phải lớn hơn 0")
    private int productId;

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String productName;

    @NotNull(message = "Giá nhập không được để trống")
    @Positive(message = "Giá nhập phải lớn hơn 0")
    private float importPrice;

    @NotNull(message = "Số lượng không được để trống")
    @Positive(message = "Số lượng phải lớn hơn 0")
    private int quantity;

    @NotNull(message = "ID nhà cung cấp không được để trống")
    @Positive(message = "ID nhà cung cấp phải lớn hơn 0")
    private int supplierId;

    // Getters, setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}