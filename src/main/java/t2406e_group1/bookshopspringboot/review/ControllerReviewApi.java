package t2406e_group1.bookshopspringboot.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import t2406e_group1.bookshopspringboot.review.ServiceReview;
// import t2406e_group1.bookshopspringboot.review.EntityReview;
// import t2406e_group1.bookshopspringboot.review.EntityReview;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")

public class ControllerReviewApi {

        @Autowired
    private ServiceReview serviceReview;

    // LẤY THÔNG TIN TẤT CẢ Review
    @GetMapping
    public List<EntityReview> getAllReview() { // Lấy danh sách tất cả user
        return serviceReview.findAll(); // Gọi phương thức findAll() từ ServiceUser
    }

    // LẤY THÔNG TIN Review THEO ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityReview> getReviewById(@PathVariable int id) {
    Optional<EntityReview> entityReview = serviceReview.findById(id); // Gọi phương thức
    // findById() từ ServiceUser
    return entityReview.map(ResponseEntity::ok).orElseGet(() ->
    ResponseEntity.notFound().build());
    }

    // 
    @PostMapping
    public EntityReview createReview(@RequestBody EntityReview entityReview) {
    return serviceReview.saveEntityReview(entityReview);
    }
    // Gọi phương thức saveEntityReview() từ
    // EntityReview, SẻviceReview


    
}
