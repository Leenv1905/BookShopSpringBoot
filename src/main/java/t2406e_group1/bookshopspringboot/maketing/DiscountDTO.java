package t2406e_group1.bookshopspringboot.maketing;

import java.util.List;

public class DiscountDTO {
    private Integer id;
    private List<DiscountProductDTO> discountProducts;
    private String dateCreate;
    private String dateStart;
    private String dateEnd;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public List<DiscountProductDTO> getDiscountProducts() { return discountProducts; }
    public void setDiscountProducts(List<DiscountProductDTO> discountProducts) { this.discountProducts = discountProducts; }
    public String getDateCreate() { return dateCreate; }
    public void setDateCreate(String dateCreate) { this.dateCreate = dateCreate; }
    public String getDateStart() { return dateStart; }
    public void setDateStart(String dateStart) { this.dateStart = dateStart; }
    public String getDateEnd() { return dateEnd; }
    public void setDateEnd(String dateEnd) { this.dateEnd = dateEnd; }
}