package t2406e_group1.bookshopspringboot.import_product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import t2406e_group1.bookshopspringboot.product.EntityProduct;
import t2406e_group1.bookshopspringboot.product.JpaProduct;
import t2406e_group1.bookshopspringboot.supplier.JpaSupplier;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceImportProduct {

    @Autowired
    private JpaImportProduct jpaImportProduct;

    @Autowired
    private JpaImportProductDetail jpaImportProductDetail;

    @Autowired
    private JpaProduct jpaProduct;

    @Autowired
    private JpaSupplier jpaSupplier; // Inject JpaSupplier

    public List<EntityImportProduct> getAllImportProducts() {
        try {
            return jpaImportProduct.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách phiếu nhập hàng", e);
        }
    }

    public EntityImportProduct getImportProductById(int id) {
        return jpaImportProduct.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phiếu nhập hàng với ID: " + id));
    }

    @Transactional
    public EntityImportProduct createImportProduct(ImportProductDTO importProductDTO) {
        // Tạo phiếu nhập hàng
        EntityImportProduct importProduct = new EntityImportProduct();
        importProduct.setImportDate(importProductDTO.getImportDate());
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

            // Tạo chi tiết phiếu nhập
            EntityImportProductDetail detail = new EntityImportProductDetail();
            detail.setImportProduct(importProduct);
            detail.setProduct(product);
            detail.setProductName(detailDTO.getProductName());
            detail.setImportPrice(detailDTO.getImportPrice());
            detail.setQuantity(detailDTO.getQuantity());
            detail.setSupplier(jpaSupplier.findById(detailDTO.getSupplierId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy nhà cung cấp với ID: " + detailDTO.getSupplierId())));
            details.add(detail);

            // Cập nhật sản phẩm
            int newQuantity = product.getQuantity() + detailDTO.getQuantity();
            float newPrice = (product.getPrice() * product.getQuantity() + 
                             detailDTO.getImportPrice() * detailDTO.getQuantity()) / newQuantity;
            product.setQuantity(newQuantity);
            product.setPrice(newPrice);
            jpaProduct.save(product);
        }

        // Lưu chi tiết phiếu nhập
        importProduct.setDetails(details);
        jpaImportProductDetail.saveAll(details);

        return importProduct;
    }

    @Transactional
    public EntityImportProduct updateImportProduct(int id, ImportProductDTO importProductDTO) {
        EntityImportProduct existingImportProduct = getImportProductById(id);

        // Cập nhật thông tin phiếu nhập
        existingImportProduct.setImportDate(importProductDTO.getImportDate());
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
            EntityProduct product = jpaProduct.findById(detailDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sản phẩm với ID: " + detailDTO.getProductId()));

            EntityImportProductDetail detail = new EntityImportProductDetail();
            detail.setImportProduct(existingImportProduct);
            detail.setProduct(product);
            detail.setProductName(detailDTO.getProductName());
            detail.setImportPrice(detailDTO.getImportPrice());
            detail.setQuantity(detailDTO.getQuantity());
            detail.setSupplier(jpaSupplier.findById(detailDTO.getSupplierId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy nhà cung cấp với ID: " + detailDTO.getSupplierId())));
            details.add(detail);

            // Cập nhật sản phẩm
            int newQuantity = product.getQuantity() + detailDTO.getQuantity();
            float newPrice = (product.getPrice() * product.getQuantity() + 
                             detailDTO.getImportPrice() * detailDTO.getQuantity()) / newQuantity;
            product.setQuantity(newQuantity);
            product.setPrice(newPrice);
            jpaProduct.save(product);
        }

        existingImportProduct.setDetails(details);
        jpaImportProductDetail.saveAll(details);

        return jpaImportProduct.save(existingImportProduct);
    }

    @Transactional
    public void deleteImportProduct(int id) {
        EntityImportProduct importProduct = getImportProductById(id);
        jpaImportProductDetail.deleteAll(importProduct.getDetails());
        jpaImportProduct.delete(importProduct);
    }
}