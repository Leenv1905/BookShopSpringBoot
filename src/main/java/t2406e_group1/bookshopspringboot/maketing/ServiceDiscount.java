package t2406e_group1.bookshopspringboot.maketing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t2406e_group1.bookshopspringboot.product.EntityProduct;
import t2406e_group1.bookshopspringboot.product.JpaProduct;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceDiscount {

    @Autowired
    private JpaDiscount jpaDiscount;

    @Autowired
    private JpaDiscountProduct jpaDiscountProduct;

    @Autowired
    private JpaProduct jpaProduct;

    public List<EntityDiscount> findAll() {
        return jpaDiscount.findAll();
    }

    public Optional<EntityDiscount> findById(int id) {
        return jpaDiscount.findById(id);
    }

    public EntityDiscount createDiscount(DiscountDTO discountDTO) {
        EntityDiscount discount = new EntityDiscount();
        discount.setDateCreate(discountDTO.getDateCreate());
        discount.setDateStart(discountDTO.getDateStart());
        discount.setDateEnd(discountDTO.getDateEnd());

        EntityDiscount savedDiscount = jpaDiscount.save(discount);

        List<DiscountProduct> discountProducts = discountDTO.getProducts().stream().map(p -> {
            DiscountProduct dp = new DiscountProduct();
            dp.setDiscountId(savedDiscount.getId());
            dp.setProductId(p.getId());
            dp.setDiscount(savedDiscount);
            EntityProduct product = jpaProduct.findById(p.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + p.getId()));
            dp.setProduct(product);
            dp.setSalePrice(p.getSalePrice());
            dp.setQuantity(p.getQuantity());
            return dp;
        }).collect(Collectors.toList());

        jpaDiscountProduct.saveAll(discountProducts);

        return savedDiscount;
    }

    public EntityDiscount updateDiscount(Integer id, DiscountDTO discountDTO) {
        Optional<EntityDiscount> existingDiscountOpt = jpaDiscount.findById(id);
        if (!existingDiscountOpt.isPresent()) {
            throw new RuntimeException("Discount not found: " + id);
        }

        EntityDiscount discount = existingDiscountOpt.get();
        discount.setDateStart(discountDTO.getDateStart());
        discount.setDateEnd(discountDTO.getDateEnd());

        jpaDiscountProduct.deleteByDiscount(discount);

        List<DiscountProduct> discountProducts = discountDTO.getProducts().stream().map(p -> {
            DiscountProduct dp = new DiscountProduct();
            dp.setDiscountId(discount.getId());
            dp.setProductId(p.getId());
            dp.setDiscount(discount);
            EntityProduct product = jpaProduct.findById(p.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + p.getId()));
            dp.setProduct(product);
            dp.setSalePrice(p.getSalePrice());
            dp.setQuantity(p.getQuantity());
            return dp;
        }).collect(Collectors.toList());

        jpaDiscountProduct.saveAll(discountProducts);

        return jpaDiscount.save(discount);
    }

    public void deleteById(int id) {
        Optional<EntityDiscount> discountOpt = jpaDiscount.findById(id);
        if (discountOpt.isPresent()) {
            jpaDiscountProduct.deleteByDiscount(discountOpt.get());
            jpaDiscount.deleteById(id);
        }
    }

    public boolean existsById(int id) {
        return jpaDiscount.existsById(id);
    }
}