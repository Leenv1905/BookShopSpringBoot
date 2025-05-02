package t2406e_group1.bookshopspringboot.maketing;

import java.sql.Date;
import java.util.List;

public class DiscountDTO {
    private Integer id;
    private Date dateCreate;
    private Date dateStart;
    private Date dateEnd;
    private List<DiscountProductDTO> discountProducts;

    // Getters và setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Date getDateCreate() { return dateCreate; }
    public void setDateCreate(Date dateCreate) { this.dateCreate = dateCreate; }
    public Date getDateStart() { return dateStart; }
    public void setDateStart(Date dateStart) { this.dateStart = dateStart; }
    public Date getDateEnd() { return dateEnd; }
    public void setDateEnd(Date dateEnd) { this.dateEnd = dateEnd; }
    public List<DiscountProductDTO> getDiscountProducts() { return discountProducts; }
    public void setDiscountProducts(List<DiscountProductDTO> discountProducts) { this.discountProducts = discountProducts; }
}