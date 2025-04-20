// Lớp DTO trung gian để xử lý dữ liệu từ front end
package t2406e_group1.bookshopspringboot.maketing;

import java.util.List;

public class DiscountDTO {
    private Integer id;
    private List<ProductDTO> products;
    private String dateCreate;
    private String dateStart;
    private String dateEnd;

    public static class ProductDTO {
        private Integer id;
        private Double salePrice;
        private Integer quantity;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Double getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(Double salePrice) {
            this.salePrice = salePrice;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}