package t2406e_group1.bookshopspringboot.maketing;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/discounts")
public class ControllerDiscount {
    
        @Autowired
    private ServiceDiscount serviceDiscount;

        // LẤY THÔNG TIN TẤT CẢ Discount
    @GetMapping
    public List<EntityDiscount> getAllDiscount() { // Lấy danh sách tất cả Discount
        return serviceDiscount.findAll(); // Gọi phương thức findAll() từ ServiceDiscount
    }

        // LẤY THÔNG TIN Discount THEO ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityDiscount> getDiscountById(@PathVariable int id) {
    Optional<EntityDiscount> entityDiscount = serviceDiscount.findById(id); // Gọi phương thức
    // findById() từ ServiceDiscount
    // return entityDiscount.map(discount -> ResponseEntity.ok(discount)).orElseGet(() ->
    return entityDiscount.map(ResponseEntity::ok).orElseGet(() ->

    ResponseEntity.notFound().build());
    }

   // POST
    @PostMapping
    public EntityDiscount createDiscount(@RequestBody EntityDiscount entityDiscount) {
    return serviceDiscount.saveEntityDiscount(entityDiscount);
    }
    // Gọi phương thức saveEntityDiscount() từ
    // EntityDiscount, SẻviceDiscount


}
