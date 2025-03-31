// package t2406e_group1.bookshopspringboot.order;

// public class ControllerOrderApi {
    
// }

package t2406e_group1.bookshopspringboot.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import t2406e_group1.bookshopspringboot.review.EntityReview;

// import t2406e_group1.bookshopspringboot.user.EntityUser;
// import t2406e_group1.bookshopspringboot.user.ServiceUser;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")

public class ControllerOrderApi {

    @Autowired
    private ServiceOrder serviceOrder;

    // @Autowired
    // private ServiceUser serviceUser;

    // ✅ 1. Tạo đơn hàng
    @PostMapping
    public EntityOrder createOrder(@RequestBody EntityOrder entityOrder) {
    return serviceOrder.saveEntityOrder(entityOrder);
    }

    // ✅ 2. Xem danh sách đơn hàng
    @GetMapping
    public List<EntityOrder> getAllOrders() {
        return serviceOrder.findAll();
    }

    // ✅ 3. Xem chi tiết đơn hàng
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable int id) {
        Optional<EntityOrder> optionalOrder = serviceOrder.findById(id);
        return optionalOrder.map(order -> ResponseEntity.ok(order))
        .orElseGet(() -> ResponseEntity.status(404).body(null));

    }

    // ✅ 4. Cập nhật đơn hàng
    // @PutMapping("/update/{id}")
    // public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestBody Map<String, Object> orderData) {
    //     Optional<EntityOrder> optionalOrder = serviceOrder.findById(id);
    //     if (!optionalOrder.isPresent()) {
    //         return ResponseEntity.status(404).body("Đơn hàng không tồn tại!");
    //     }

    //     EntityOrder order = optionalOrder.get();
    //     if (orderData.containsKey("status")) {
    //         order.setStatus((String) orderData.get("status"));
    //     }
    //     if (orderData.containsKey("phoneNumber")) {
    //         order.setPhoneNumber((String) orderData.get("phoneNumber"));
    //     }

    //     EntityOrder updatedOrder = serviceOrder.save(order);
    //     return ResponseEntity.ok(updatedOrder);
    // }

    // ✅ 5. Xóa đơn hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id) {
        Optional<EntityOrder> optionalOrder = serviceOrder.findById(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.status(404).body("Đơn hàng không tồn tại!");
        }

        serviceOrder.deleteById(id);
        return ResponseEntity.ok("Đơn hàng đã được xóa!");
    }
}
