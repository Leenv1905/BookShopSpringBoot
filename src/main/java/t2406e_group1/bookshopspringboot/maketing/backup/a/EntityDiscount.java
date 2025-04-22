// package t2406e_group1.bookshopspringboot.maketing.backup.a;
// // EntityDiscount để sử dụng @ManyToMany với EntityProduct
// package t2406e_group1.bookshopspringboot.maketing;

// import com.fasterxml.jackson.annotation.JsonManagedReference;
// import jakarta.persistence.*;
// import lombok.Getter;
// import lombok.Setter;

// import java.util.ArrayList;
// import java.util.List;

// @Entity
// @Getter
// @Setter
// @Table(name = "entity_discount")
// public class EntityDiscount {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;

//     @OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, orphanRemoval = true)
//     @JsonManagedReference
//     private List<DiscountProduct> discountProducts = new ArrayList<>();

//     @Column(name = "date_create")
//     private String dateCreate;

//     @Column(name = "date_start")
//     private String dateStart;

//     @Column(name = "date_end")
//     private String dateEnd;
// }