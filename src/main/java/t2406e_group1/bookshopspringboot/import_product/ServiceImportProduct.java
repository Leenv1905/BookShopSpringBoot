package t2406e_group1.bookshopspringboot.import_product;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import t2406e_group1.bookshopspringboot.product.EntityProduct;
import t2406e_group1.bookshopspringboot.product.JpaProduct;
import t2406e_group1.bookshopspringboot.supplier.EntitySupplier;
import t2406e_group1.bookshopspringboot.supplier.JpaSupplier;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImportProduct {

    private static final Logger logger = LoggerFactory.getLogger(ServiceImportProduct.class);

    @Autowired
    private JpaImportProduct jpaImportProduct;

    @Autowired
    private JpaImportProductDetail jpaImportProductDetail;

    @Autowired
    private JpaProduct jpaProduct;

    @Autowired
    private JpaSupplier jpaSupplier;

    private void updateProduct(EntityProduct product, float importPrice, int importQuantity) {
        int newQuantity = product.getQuantity() + importQuantity;
        float newPrice = (product.getPrice() * product.getQuantity() + 
                         importPrice * importQuantity) / newQuantity;
        product.setQuantity(newQuantity);
        product.setPrice(newPrice);
        jpaProduct.save(product);
    }

    public List<EntityImportProduct> getAllImportProducts() {
        try {
            logger.info("Lấy danh sách phiếu nhập hàng");
            List<EntityImportProduct> importProducts = jpaImportProduct.findAll();
            // Khởi tạo lazy-loaded properties
            for (EntityImportProduct importProduct : importProducts) {
                Hibernate.initialize(importProduct.getDetails());
                for (EntityImportProductDetail detail : importProduct.getDetails()) {
                    Hibernate.initialize(detail.getProduct());
                    Hibernate.initialize(detail.getSupplier());
                    if (detail.getProduct() != null) {
                        Hibernate.initialize(detail.getProduct().getImages());
                    }
                }
            }
            logger.info("Tìm thấy {} phiếu nhập hàng", importProducts.size());
            return importProducts;
        } catch (Exception e) {
            logger.error("Lỗi khi lấy danh sách phiếu nhập hàng", e);
            throw new RuntimeException("Lỗi khi lấy danh sách phiếu nhập hàng", e);
        }
    }

    public EntityImportProduct getImportProductById(int id) {
        try {
            logger.info("Lấy phiếu nhập hàng với ID: {}", id);
            EntityImportProduct importProduct = jpaImportProduct.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phiếu nhập hàng với ID: " + id));
            // Khởi tạo lazy-loaded properties
            Hibernate.initialize(importProduct.getDetails());
            for (EntityImportProductDetail detail : importProduct.getDetails()) {
                Hibernate.initialize(detail.getProduct());
                Hibernate.initialize(detail.getSupplier());
                if (detail.getProduct() != null) {
                    Hibernate.initialize(detail.getProduct().getImages());
                }
            }
            logger.info("Tìm thấy phiếu nhập hàng với ID: {}", id);
            return importProduct;
        } catch (Exception e) {
            logger.error("Lỗi khi lấy phiếu nhập hàng với ID: {}", id, e);
            throw new RuntimeException("Lỗi khi lấy phiếu nhập hàng với ID: " + id, e);
        }
    }

    @Transactional
    public EntityImportProduct createImportProduct(ImportProductDTO importProductDTO) {
        logger.info("Tạo phiếu nhập hàng mới");
        // Tạo phiếu nhập hàng
        EntityImportProduct importProduct = new EntityImportProduct();
        // Chuyển Date thành LocalDateTime
        importProduct.setImportDate(importProductDTO.getImportDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        importProduct.setTotalQuantity(importProductDTO.getDetails()
                .stream()
                .mapToInt(ImportProductDetailDTO::getQuantity)
                .sum());

        // Lưu phiếu nhập hàng
        importProduct = jpaImportProduct.save(importProduct);

        // Xử lý chi tiết phiếu nhập
        List<EntityImportProductDetail> details = new ArrayList<>();
        for (ImportProductDetailDTO detailDTO : importProductDTO.getDetails()) {
            // Tìm sản phẩm
            EntityProduct product = jpaProduct.findById(detailDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sản phẩm với ID: " + detailDTO.getProductId()));

            // Tìm nhà cung cấp
            EntitySupplier supplier = jpaSupplier.findById(detailDTO.getSupplierId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy nhà cung cấp với ID: " + detailDTO.getSupplierId()));

            // Tạo chi tiết phiếu nhập
            EntityImportProductDetail detail = new EntityImportProductDetail();
            detail.setImportProduct(importProduct);
            detail.setProduct(product);
            detail.setProductName(detailDTO.getProductName());
            // Chuyển float thành Double
            detail.setImportPrice(Double.valueOf(detailDTO.getImportPrice()));
            detail.setQuantity(detailDTO.getQuantity());
            detail.setSupplier(supplier);
            details.add(detail);

            // Cập nhật sản phẩm
            updateProduct(product, detailDTO.getImportPrice(), detailDTO.getQuantity());
        }

        // Lưu chi tiết phiếu nhập
        importProduct.setDetails(details);
        jpaImportProductDetail.saveAll(details);

        logger.info("Tạo thành công phiếu nhập hàng với ID: {}", importProduct.getId());
        return importProduct;
    }

    @Transactional
    public EntityImportProduct updateImportProduct(int id, ImportProductDTO importProductDTO) {
        logger.info("Cập nhật phiếu nhập hàng với ID: {}", id);
        EntityImportProduct existingImportProduct = getImportProductById(id);

        // Cập nhật thông tin phiếu nhập
        // Chuyển Date thành LocalDateTime
        existingImportProduct.setImportDate(importProductDTO.getImportDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());
        existingImportProduct.setTotalQuantity(importProductDTO.getDetails()
                .stream()
                .mapToInt(ImportProductDetailDTO::getQuantity)
                .sum());

        // Xóa chi tiết cũ
        jpaImportProductDetail.deleteAll(existingImportProduct.getDetails());
        existingImportProduct.getDetails().clear();

        // Thêm chi tiết mới
        List<EntityImportProductDetail> details = new ArrayList<>();
        for (ImportProductDetailDTO detailDTO : importProductDTO.getDetails()) {
            // Tìm sản phẩm
            EntityProduct product = jpaProduct.findById(detailDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sản phẩm với ID: " + detailDTO.getProductId()));

            // Tìm nhà cung cấp
            EntitySupplier supplier = jpaSupplier.findById(detailDTO.getSupplierId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy nhà cung cấp với ID: " + detailDTO.getSupplierId()));

            // Tạo chi tiết phiếu nhập
            EntityImportProductDetail detail = new EntityImportProductDetail();
            detail.setImportProduct(existingImportProduct);
            detail.setProduct(product);
            detail.setProductName(detailDTO.getProductName());
            // Chuyển float thành Double
            detail.setImportPrice(Double.valueOf(detailDTO.getImportPrice()));
            detail.setQuantity(detailDTO.getQuantity());
            detail.setSupplier(supplier);
            details.add(detail);

            // Cập nhật sản phẩm
            updateProduct(product, detailDTO.getImportPrice(), detailDTO.getQuantity());
        }

        existingImportProduct.setDetails(details);
        jpaImportProductDetail.saveAll(details);

        EntityImportProduct updatedImportProduct = jpaImportProduct.save(existingImportProduct);
        logger.info("Cập nhật thành công phiếu nhập hàng với ID: {}", id);
        return updatedImportProduct;
    }

    @Transactional
    public void deleteImportProduct(int id) {
        logger.info("Xóa phiếu nhập hàng với ID: {}", id);
        EntityImportProduct importProduct = getImportProductById(id);
        jpaImportProductDetail.deleteAll(importProduct.getDetails());
        jpaImportProduct.delete(importProduct);
        logger.info("Xóa thành công phiếu nhập hàng với ID: {}", id);
    }
}