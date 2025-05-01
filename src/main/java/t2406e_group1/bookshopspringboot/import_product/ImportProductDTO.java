package t2406e_group1.bookshopspringboot.import_product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ImportProductDTO {

    @NotNull(message = "Ngày nhập không được để trống")
    private Date importDate;

    @NotEmpty(message = "Danh sách chi tiết không được để trống")
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
}