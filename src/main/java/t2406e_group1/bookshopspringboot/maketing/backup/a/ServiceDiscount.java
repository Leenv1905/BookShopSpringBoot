// package t2406e_group1.bookshopspringboot.maketing.backup.a;
// // // 
// // package t2406e_group1.bookshopspringboot.maketing;

// // import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.stereotype.Service;
// // import t2406e_group1.bookshopspringboot.product.EntityProduct;
// // import t2406e_group1.bookshopspringboot.product.JpaProduct;

// // import java.util.List;
// // import java.util.Optional;
// // import java.util.stream.Collectors;

// // @Service
// // public class ServiceDiscount {

// //     @Autowired
// //     private JpaDiscount jpaDiscount;

// //     @Autowired
// //     private JpaDiscountProduct jpaDiscountProduct;

// //     @Autowired
// //     private JpaProduct jpaProduct;

// //     public List<EntityDiscount> findAll() {
// //         return jpaDiscount.findAll();
// //     }

// //     public Optional<EntityDiscount> findById(int id) {
// //         return jpaDiscount.findById(id);
// //     }

// //     public EntityDiscount createDiscount(DiscountDTO discountDTO) {
// //         if (discountDTO.getProducts() == null || discountDTO.getProducts().isEmpty()) {
// //             throw new IllegalArgumentException("Danh sách sản phẩm không được rỗng");
// //         }
// //         EntityDiscount discount = new EntityDiscount();
// //         discount.setDateCreate(discountDTO.getDateCreate());
// //         discount.setDateStart(discountDTO.getDateStart());
// //         discount.setDateEnd(discountDTO.getDateEnd());

// //         EntityDiscount savedDiscount = jpaDiscount.save(discount);

// //         List<DiscountProduct> discountProducts = discountDTO.getProducts().stream().map(p -> {
// //             if (p.getId() == null || p.getSalePrice() == null || p.getQuantity() == null) {
// //                 throw new IllegalArgumentException("Thông tin sản phẩm không hợp lệ: ID, salePrice hoặc quantity bị thiếu");
// //             }
// //             EntityProduct product = jpaProduct.findById(p.getId())
// //                 .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại: ID " + p.getId()));

// //             DiscountProduct dp = new DiscountProduct();
// //             dp.setId(new DiscountProductId(savedDiscount.getId(), product.getId()));
// //             dp.setDiscount(savedDiscount);
// //             dp.setProduct(product);
// //             dp.setSalePrice(p.getSalePrice());
// //             dp.setQuantity(p.getQuantity());
// //             return dp;
// //         }).collect(Collectors.toList());

// //         jpaDiscountProduct.saveAll(discountProducts);

// //         return savedDiscount;
// //     }

// //     public EntityDiscount updateDiscount(Integer id, DiscountDTO discountDTO) {
// //         if (id == null || id <= 0) {
// //             throw new IllegalArgumentException("ID mã giảm giá không hợp lệ");
// //         }
// //         if (discountDTO.getProducts() == null || discountDTO.getProducts().isEmpty()) {
// //             throw new IllegalArgumentException("Danh sách sản phẩm không được rỗng");
// //         }
// //         EntityDiscount discount = jpaDiscount.findById(id)
// //             .orElseThrow(() -> new IllegalArgumentException("Mã giảm giá không tồn tại: ID " + id));

// //         discount.setDateStart(discountDTO.getDateStart());
// //         discount.setDateEnd(discountDTO.getDateEnd());
// //         discount.setDateCreate(discountDTO.getDateCreate());

// //         jpaDiscountProduct.deleteByDiscount(discount);

// //         List<DiscountProduct> discountProducts = discountDTO.getProducts().stream().map(p -> {
// //             EntityProduct product = jpaProduct.findById(p.getId())
// //                 .orElseThrow(() -> new IllegalArgumentException("Sản phẩm không tồn tại: ID " + p.getId()));

// //             DiscountProduct dp = new DiscountProduct();
// //             dp.setId(new DiscountProductId(discount.getId(), product.getId()));
// //             dp.setDiscount(discount);
// //             dp.setProduct(product);
// //             dp.setSalePrice(p.getSalePrice());
// //             dp.setQuantity(p.getQuantity());
// //             return dp;
// //         }).collect(Collectors.toList());

// //         jpaDiscountProduct.saveAll(discountProducts);

// //         return jpaDiscount.save(discount);
// //     }

// //     public void deleteById(int id) {
// //         System.out.println("Attempting to delete discount ID: " + id);
// //         Optional<EntityDiscount> discountOpt = jpaDiscount.findById(id);
// //         if (discountOpt.isPresent()) {
// //             jpaDiscountProduct.deleteByDiscount(discountOpt.get());
// //             jpaDiscount.deleteById(id);
// //             System.out.println("Successfully deleted discount ID: " + id);
// //         } else {
// //             System.out.println("Discount ID not found: " + id);
// //         }
// //     }

// //     public boolean existsById(int id) {
// //         return jpaDiscount.existsById(id);
// //     }
// // }
