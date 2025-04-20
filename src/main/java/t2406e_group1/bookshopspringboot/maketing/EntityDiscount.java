// EntityDiscount để sử dụng @ManyToMany với EntityProduct
package t2406e_group1.bookshopspringboot.maketing;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import t2406e_group1.bookshopspringboot.product.EntityProduct;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "entity_discount")
public class EntityDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany
    @JoinTable(
        name = "discount_product",
        joinColumns = @JoinColumn(name = "discount_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<EntityProduct> products = new ArrayList<>();

    @Column(name = "date_create")
    private String dateCreate;

    @Column(name = "date_start")
    private String dateStart;

    @Column(name = "date_end")
    private String dateEnd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<EntityProduct> getProducts() {
        return products;
    }

    public void setProducts(List<EntityProduct> products) {
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