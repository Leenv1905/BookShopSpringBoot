package t2406e_group1.bookshopspringboot.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import t2406e_group1.bookshopspringboot.maketing.DiscountProduct;
import t2406e_group1.bookshopspringboot.maketing.EntityDiscount;
import t2406e_group1.bookshopspringboot.maketing.JpaDiscount;
import t2406e_group1.bookshopspringboot.maketing.JpaDiscountProduct;
import t2406e_group1.bookshopspringboot.product.image.EntityImage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceProduct {
    private final JpaProduct jpaProduct;
    private final JpaDiscountProduct jpaDiscountProduct;
    private final JpaDiscount jpaDiscount;

    @Autowired
    public ServiceProduct(JpaProduct jpaProduct, JpaDiscountProduct jpaDiscountProduct, JpaDiscount jpaDiscount) {
        this.jpaProduct = jpaProduct;
        this.jpaDiscountProduct = jpaDiscountProduct;
        this.jpaDiscount = jpaDiscount;
    }

    public List<EntityProduct> findAll() {
        return jpaProduct.findAll();
    }

    public Optional<EntityProduct> findById(int id) {
        return jpaProduct.findById(id);
    }

    public EntityProduct saveEntityProduct(EntityProduct entityProduct) {
        return jpaProduct.save(entityProduct);
    }

    public boolean existsById(int id) {
        return jpaProduct.existsById(id);
    }

    public void deleteById(int id) {
        jpaProduct.deleteById(id);
    }

    public List<ProductDTO> findAllAsDTO() {
        List<EntityProduct> products = jpaProduct.findAll();
        List<DiscountProduct> discountProducts = jpaDiscountProduct.findAll();
        List<EntityDiscount> activeDiscounts = jpaDiscount.findAll().stream()
            .filter(d -> {
                Date currentDate = new Date();
                return d.getDateStart().before(currentDate) && d.getDateEnd().after(currentDate);
            })
            .collect(Collectors.toList());
        List<Integer> activeDiscountIds = activeDiscounts.stream()
            .map(EntityDiscount::getId)
            .collect(Collectors.toList());

        return products.stream().map(product -> {
            ProductDTO dto = new ProductDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setQuantity(product.getQuantity());
            dto.setAuthor(product.getAuthor());
            dto.setCategory(product.getCategory());
            dto.setStatus(product.getStatus());
            dto.setDescription(product.getDescription());
            dto.setContent(product.getContent());
            dto.setLanguage(product.getLanguage());
            if (product.getDateAdded() != null) {
                dto.setDateAdded(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(product.getDateAdded()));
            }
            dto.setImages(product.getImages().stream()
                .map(image -> {
                    ImageDTO imageDTO = new ImageDTO();
                    imageDTO.setId(image.getId());
                    imageDTO.setImagePath(image.getImagePath());
                    return imageDTO;
                })
                .collect(Collectors.toList()));

            discountProducts.stream()
                .filter(dp -> dp.getId().getProductId() == product.getId())
                .filter(dp -> activeDiscountIds.contains(dp.getId().getDiscountId()))
                .findFirst()
                .ifPresent(dp -> {
                    dto.setSalePrice(dp.getSalePrice());
                    if (dp.getSalePrice() != null && product.getPrice() != null && product.getPrice() > 0) {
                        int discountPercentage = (int) ((product.getPrice() - dp.getSalePrice()) / product.getPrice() * 100);
                        dto.setDiscountPercentage(discountPercentage);
                    }
                });

            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<ProductDTO> findByIdAsDTO(int id) {
        Optional<EntityProduct> productOpt = jpaProduct.findById(id);
        if (!productOpt.isPresent()) {
            return Optional.empty();
        }
        EntityProduct product = productOpt.get();
        List<DiscountProduct> discountProducts = jpaDiscountProduct.findByProductId(id);
        List<EntityDiscount> activeDiscounts = jpaDiscount.findAll().stream()
            .filter(d -> {
                Date currentDate = new Date();
                return d.getDateStart().before(currentDate) && d.getDateEnd().after(currentDate);
            })
            .collect(Collectors.toList());
        List<Integer> activeDiscountIds = activeDiscounts.stream()
            .map(EntityDiscount::getId)
            .collect(Collectors.toList());

        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setAuthor(product.getAuthor());
        dto.setCategory(product.getCategory());
        dto.setStatus(product.getStatus());
        dto.setDescription(product.getDescription());
        dto.setContent(product.getContent());
        dto.setLanguage(product.getLanguage());
        if (product.getDateAdded() != null) {
            dto.setDateAdded(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(product.getDateAdded()));
        }
        dto.setImages(product.getImages().stream()
            .map(image -> {
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(image.getId());
                imageDTO.setImagePath(image.getImagePath());
                return imageDTO;
            })
            .collect(Collectors.toList()));

        discountProducts.stream()
            .filter(dp -> activeDiscountIds.contains(dp.getId().getDiscountId()))
            .findFirst()
            .ifPresent(dp -> {
                dto.setSalePrice(dp.getSalePrice());
                if (dp.getSalePrice() != null && product.getPrice() != null && product.getPrice() > 0) {
                    int discountPercentage = (int) ((product.getPrice() - dp.getSalePrice()) / product.getPrice() * 100);
                    dto.setDiscountPercentage(discountPercentage);
                }
            });

        return Optional.of(dto);
    }

    public Optional<ProductDetailDTO> findByIdAsDetailDTO(int id) {
        Optional<EntityProduct> productOpt = jpaProduct.findById(id);
        if (!productOpt.isPresent()) {
            return Optional.empty();
        }
        EntityProduct product = productOpt.get();
        List<DiscountProduct> discountProducts = jpaDiscountProduct.findByProductId(id);
        List<EntityDiscount> activeDiscounts = jpaDiscount.findAll().stream()
            .filter(d -> {
                Date currentDate = new Date();
                return d.getDateStart().before(currentDate) && d.getDateEnd().after(currentDate);
            })
            .collect(Collectors.toList());
        List<Integer> activeDiscountIds = activeDiscounts.stream()
            .map(EntityDiscount::getId)
            .collect(Collectors.toList());

        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());
        dto.setDescription(product.getDescription());
        dto.setAuthor(product.getAuthor());
        dto.setImages(product.getImages().stream()
            .map(EntityImage::getImagePath)
            .collect(Collectors.toList()));
        dto.setRating(4.0f); // Giả lập rating, chờ bảng EntityRating

        discountProducts.stream()
            .filter(dp -> activeDiscountIds.contains(dp.getId().getDiscountId()))
            .findFirst()
            .ifPresent(dp -> {
                dto.setSalePrice(dp.getSalePrice());
                if (dp.getSalePrice() != null && product.getPrice() != null && product.getPrice() > 0) {
                    int discountPercentage = (int) ((product.getPrice() - dp.getSalePrice()) / product.getPrice() * 100);
                    dto.setDiscountPercentage(discountPercentage);
                }
            });

        return Optional.of(dto);
    }
}