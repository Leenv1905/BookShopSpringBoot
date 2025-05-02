package t2406e_group1.bookshopspringboot.maketing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import t2406e_group1.bookshopspringboot.product.EntityProduct;
import t2406e_group1.bookshopspringboot.product.JpaProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceDiscount {
    private static final Logger logger = LoggerFactory.getLogger(ServiceDiscount.class);

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

    @Transactional
    public EntityDiscount createDiscount(DiscountDTO discountDTO) {
        logger.info("Creating discount with DTO: {}", discountDTO);
        if (discountDTO.getDiscountProducts() == null || discountDTO.getDiscountProducts().isEmpty()) {
            logger.error("Product list is empty");
            throw new IllegalArgumentException("Danh sách sản phẩm không được rỗng");
        }
        if (discountDTO.getDateStart() == null || discountDTO.getDateEnd() == null) {
            logger.error("Invalid date range: dateStart or dateEnd is null");
            throw new IllegalArgumentException("Ngày bắt đầu và kết thúc không được rỗng");
        }

        // Kiểm tra trùng sản phẩm
        for (DiscountProductDTO productDTO : discountDTO.getDiscountProducts()) {
            if (productDTO.getProductId() == null) {
                logger.error("Invalid product info: productId is null");
                throw new IllegalArgumentException("ID sản phẩm không được rỗng");
            }
            List<DiscountProduct> existingDiscounts = jpaDiscountProduct.findByProductId(productDTO.getProductId());
            for (DiscountProduct dp : existingDiscounts) {
                EntityDiscount existingDiscount = dp.getDiscount();
                if (isOverlapping(discountDTO.getDateStart(), discountDTO.getDateEnd(),
                                 existingDiscount.getDateStart(), existingDiscount.getDateEnd())) {
                    logger.error("Product {} already in another discount during overlapping period", productDTO.getProductId());
                    throw new IllegalArgumentException("Sản phẩm ID " + productDTO.getProductId() +
                                                      " đã thuộc khuyến mại khác trong khoảng thời gian trùng lặp");
                }
            }
        }

        EntityDiscount discount = new EntityDiscount();
        discount.setDateCreate(discountDTO.getDateCreate());
        discount.setDateStart(discountDTO.getDateStart());
        discount.setDateEnd(discountDTO.getDateEnd());

        EntityDiscount savedDiscount = jpaDiscount.save(discount);

        List<DiscountProduct> discountProducts = discountDTO.getDiscountProducts().stream().map(p -> {
            if (p.getProductId() == null || p.getSalePrice() == null || p.getQuantity() == null) {
                logger.error("Invalid product info: {}", p);
                throw new IllegalArgumentException("Thông tin sản phẩm không hợp lệ: productId, salePrice hoặc quantity bị thiếu");
            }
            EntityProduct product = jpaProduct.findById(p.getProductId())
                .orElseThrow(() -> {
                    logger.error("Product not found: {}", p.getProductId());
                    return new IllegalArgumentException("Sản phẩm không tồn tại: ID " + p.getProductId());
                });

            DiscountProduct dp = new DiscountProduct();
            dp.setId(new DiscountProduct.DiscountProductId(savedDiscount.getId(), product.getId()));
            dp.setDiscount(savedDiscount);
            dp.setProduct(product);
            dp.setSalePrice(p.getSalePrice());
            dp.setQuantity(p.getQuantity());
            return dp;
        }).collect(Collectors.toList());

        jpaDiscountProduct.saveAll(discountProducts);
        logger.info("Created discount: {}", savedDiscount);
        return savedDiscount;
    }

    @Transactional
    public EntityDiscount updateDiscount(Integer id, DiscountDTO discountDTO) {
        logger.info("Updating discount id: {}, DTO: {}", id, discountDTO);
        if (id == null || id <= 0) {
            logger.error("Invalid discount ID: {}", id);
            throw new IllegalArgumentException("ID mã giảm giá không hợp lệ");
        }
        if (discountDTO.getDiscountProducts() == null || discountDTO.getDiscountProducts().isEmpty()) {
            logger.error("Product list is empty for discount id: {}", id);
            throw new IllegalArgumentException("Danh sách sản phẩm không được rỗng");
        }
        if (discountDTO.getDateStart() == null || discountDTO.getDateEnd() == null) {
            logger.error("Invalid date range: dateStart or dateEnd is null");
            throw new IllegalArgumentException("Ngày bắt đầu và kết thúc không được rỗng");
        }

        EntityDiscount discount = jpaDiscount.findById(id)
            .orElseThrow(() -> {
                logger.error("Discount not found: {}", id);
                return new IllegalArgumentException("Mã giảm giá không tồn tại: ID " + id);
            });

        // Kiểm tra trùng sản phẩm
        for (DiscountProductDTO productDTO : discountDTO.getDiscountProducts()) {
            if (productDTO.getProductId() == null) {
                logger.error("Invalid product info: productId is null");
                throw new IllegalArgumentException("ID sản phẩm không được rỗng");
            }
            List<DiscountProduct> existingDiscounts = jpaDiscountProduct.findByProductId(productDTO.getProductId());
            for (DiscountProduct dp : existingDiscounts) {
                EntityDiscount existingDiscount = dp.getDiscount();
                // Bỏ qua chính khuyến mại đang cập nhật
                if (existingDiscount.getId().equals(id)) {
                    continue;
                }
                if (isOverlapping(discountDTO.getDateStart(), discountDTO.getDateEnd(),
                                 existingDiscount.getDateStart(), existingDiscount.getDateEnd())) {
                    logger.error("Product {} already in another discount during overlapping period", productDTO.getProductId());
                    throw new IllegalArgumentException("Sản phẩm ID " + productDTO.getProductId() +
                                                      " đã thuộc khuyến mại khác trong khoảng thời gian trùng lặp");
                }
            }
        }

        discount.setDateStart(discountDTO.getDateStart());
        discount.setDateEnd(discountDTO.getDateEnd());
        discount.setDateCreate(discountDTO.getDateCreate());

        jpaDiscountProduct.deleteByDiscount(discount);

        List<DiscountProduct> discountProducts = discountDTO.getDiscountProducts().stream().map(p -> {
            if (p.getProductId() == null || p.getSalePrice() == null || p.getQuantity() == null) {
                logger.error("Invalid product info: {}", p);
                throw new IllegalArgumentException("Thông tin sản phẩm không hợp lệ: productId, salePrice hoặc quantity bị thiếu");
            }
            EntityProduct product = jpaProduct.findById(p.getProductId())
                .orElseThrow(() -> {
                    logger.error("Product not found: {}", p.getProductId());
                    return new IllegalArgumentException("Sản phẩm không tồn tại: ID " + p.getProductId());
                });

            DiscountProduct dp = new DiscountProduct();
            dp.setId(new DiscountProduct.DiscountProductId(discount.getId(), product.getId()));
            dp.setDiscount(discount);
            dp.setProduct(product);
            dp.setSalePrice(p.getSalePrice());
            dp.setQuantity(p.getQuantity());
            return dp;
        }).collect(Collectors.toList());

        jpaDiscountProduct.saveAll(discountProducts);
        EntityDiscount updatedDiscount = jpaDiscount.save(discount);
        logger.info("Updated discount: {}", updatedDiscount);
        return updatedDiscount;
    }

    @Transactional
    public void deleteById(int id) {
        logger.info("Deleting discount id: {}", id);
        Optional<EntityDiscount> discountOpt = jpaDiscount.findById(id);
        if (discountOpt.isPresent()) {
            jpaDiscountProduct.deleteByDiscount(discountOpt.get());
            jpaDiscount.deleteById(id);
            logger.info("Successfully deleted discount id: {}", id);
        } else {
            logger.warn("Discount not found: {}", id);
        }
    }

    public boolean existsById(int id) {
        return jpaDiscount.existsById(id);
    }

    public List<DiscountDTO> getAllDiscount() {
        List<EntityDiscount> discounts = jpaDiscount.findAll();
        List<DiscountDTO> discountDTOs = new ArrayList<>();
        for (EntityDiscount discount : discounts) {
            DiscountDTO discountDTO = new DiscountDTO();
            discountDTO.setId(discount.getId());
            discountDTO.setDateCreate(discount.getDateCreate());
            discountDTO.setDateStart(discount.getDateStart());
            discountDTO.setDateEnd(discount.getDateEnd());

            List<DiscountProduct> discountProducts = jpaDiscountProduct.findByDiscountId(discount.getId());
            List<DiscountProductDTO> discountProductDTOs = new ArrayList<>();
            for (DiscountProduct dp : discountProducts) {
                DiscountProductDTO dpDTO = new DiscountProductDTO();
                dpDTO.setProductId(Integer.valueOf(dp.getId().getProductId()));
                dpDTO.setSalePrice(dp.getSalePrice());
                dpDTO.setQuantity(dp.getQuantity());

                EntityProduct product = dp.getProduct();
                if (product != null) {
                    dpDTO.setName(product.getName());
                    dpDTO.setPrice(product.getPrice() != null ? product.getPrice().floatValue() : 0.0f);
                } else {
                    dpDTO.setName("Không xác định");
                    dpDTO.setPrice(0.0f);
                }
                discountProductDTOs.add(dpDTO);
            }
            discountDTO.setDiscountProducts(discountProductDTOs);
            discountDTOs.add(discountDTO);
        }
        return discountDTOs;
    }

    private boolean isOverlapping(java.sql.Date start1, java.sql.Date end1, java.sql.Date start2, java.sql.Date end2) {
        return start1.before(end2) && end1.after(start2);
    }
}