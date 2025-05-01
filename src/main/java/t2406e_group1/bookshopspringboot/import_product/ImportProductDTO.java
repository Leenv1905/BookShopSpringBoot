package t2406e_group1.bookshopspringboot.import_product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class ImportProductDTO {

    @NotNull(message = "Ngày nhập không được để trống")
    private Date importDate;

    @NotEmpty(message = "Danh sách chi tiết không được để trống")
    @Valid
    @UniqueProductIds(message = "Danh sách chi tiết không được chứa productId trùng lặp")
    private List<ImportProductDetailDTO> details = new ArrayList<>();

    // Getters, setters
    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public List<ImportProductDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<ImportProductDetailDTO> details) {
        this.details = details;
    }

    // Annotation tùy chỉnh để kiểm tra trùng productId
    @Constraint(validatedBy = UniqueProductIdsValidator.class)
    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface UniqueProductIds {
        String message() default "Danh sách chi tiết không được chứa productId trùng lặp";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    // Validator cho UniqueProductIds
    public static class UniqueProductIdsValidator implements ConstraintValidator<UniqueProductIds, List<ImportProductDetailDTO>> {
        @Override
        public void initialize(UniqueProductIds constraintAnnotation) {
        }

        @Override
        public boolean isValid(List<ImportProductDetailDTO> details, ConstraintValidatorContext context) {
            if (details == null) {
                return true;
            }
            Set<Integer> productIds = details.stream()
                    .map(ImportProductDetailDTO::getProductId)
                    .collect(Collectors.toSet());
            return productIds.size() == details.size();
        }
    }
}