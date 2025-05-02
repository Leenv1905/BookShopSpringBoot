// LỚP NÀY ĐƯỢC TẠO RA ĐỂ PHỤC VỤ RIÊNG CHO ProductDetail.JSX
// VÌ NẾU DÙNG CHUNG ProductDTO THÌ SẼ DƯ THỪA DỮ LIỆU NHƯ MÔ TẢ, CHI TIẾT
// CÁI ANYF LÀ THỬ NGHIỆP, VÌ PRODUCTDTO CŨNG ĐÃ ĐỦ DỮ LIỆU RỒI

package t2406e_group1.bookshopspringboot.product;

import java.util.List;

public class ProductDetailDTO {
    private int id;
    private String name;
    private Float price;
    private Float salePrice;
    private Integer quantity;
    private String description;
    private List<String> images;
    private String author;
    private Integer discountPercentage;
    private Float rating;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Float getPrice() { return price; }
    public void setPrice(Float price) { this.price = price; }
    public Float getSalePrice() { return salePrice; }
    public void setSalePrice(Float salePrice) { this.salePrice = salePrice; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Integer getDiscountPercentage() { return discountPercentage; }
    public void setDiscountPercentage(Integer discountPercentage) { this.discountPercentage = discountPercentage; }
    public Float getRating() { return rating; }
    public void setRating(Float rating) { this.rating = rating; }
}