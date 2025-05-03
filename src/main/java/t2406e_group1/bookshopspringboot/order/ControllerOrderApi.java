package t2406e_group1.bookshopspringboot.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import t2406e_group1.bookshopspringboot.auth.JwtUtil;
import t2406e_group1.bookshopspringboot.order.details.EntityDetailsOrder;
import t2406e_group1.bookshopspringboot.product.EntityProduct;
import t2406e_group1.bookshopspringboot.user.EntityUser;
import t2406e_group1.bookshopspringboot.user.JpaUser;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class ControllerOrderApi {

    @Autowired
    private ServiceOrder serviceOrder;

    @Autowired
    private JpaUser jpaUser;

    @Autowired
    private t2406e_group1.bookshopspringboot.product.JpaProduct jpaProduct;

    @Autowired
    private JwtUtil jwtUtil;

    // Tạo đơn hàng
    @PostMapping
    public ResponseEntity<?> createOrder(HttpServletRequest request, @RequestBody Map<String, Object> orderData) {
        // Lấy userId từ request attribute
        Integer userId = (Integer) request.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("User ID not found in token");
        }

        // Kiểm tra userId
        Optional<EntityUser> userOptional = jpaUser.findById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(404).body("User not found");
        }

        // Lấy items
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> items = (List<Map<String, Object>>) orderData.get("items");
        if (items == null || items.isEmpty()) {
            return ResponseEntity.status(400).body("Missing or empty items");
        }

        // Tạo EntityOrder
        EntityOrder entityOrder = new EntityOrder();
        entityOrder.setUser(userOptional.get());
        entityOrder.setOrderDate(new Date());
        entityOrder.setStatus("Pending");

        // Tạo danh sách EntityDetailsOrder
        List<EntityDetailsOrder> orderDetails = new ArrayList<>();
        for (Map<String, Object> item : items) {
            Integer productId = (Integer) item.get("productId");
            Integer quantity = (Integer) item.get("quantity");
            Double price = ((Number) item.get("price")).doubleValue();

            if (productId == null || quantity == null || price == null) {
                return ResponseEntity.status(400).body("Invalid item data");
            }

            Optional<EntityProduct> productOptional = jpaProduct.findById(productId);
            if (!productOptional.isPresent()) {
                return ResponseEntity.status(404).body("Product not found: " + productId);
            }

            EntityDetailsOrder detail = new EntityDetailsOrder();
            detail.setProduct(productOptional.get());
            detail.setQuantity(quantity);
            detail.setPrice(price);
            orderDetails.add(detail);
        }

        // Lưu đơn hàng
        EntityOrder savedOrder = serviceOrder.saveEntityOrder(entityOrder, orderDetails);
        return ResponseEntity.ok(savedOrder);
    }

    // Xem danh sách đơn hàng
    @GetMapping
    public List<EntityOrder> getAllOrders() {
        return serviceOrder.findAll();
    }

    // Xem chi tiết đơn hàng
    @GetMapping("/{id}")
    public ResponseEntity<EntityOrder> getOrderById(@PathVariable int id) {
        Optional<EntityOrder> optionalOrder = serviceOrder.findById(id);
        return optionalOrder.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body((EntityOrder) null));
    }

    // Cập nhật trạng thái đơn hàng
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable int id, @RequestBody String status) {
        Optional<EntityOrder> optionalOrder = serviceOrder.findById(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.status(404).body("Order not found");
        }
        EntityOrder order = optionalOrder.get();
        order.setStatus(status);
        EntityOrder updatedOrder = serviceOrder.saveEntityOrder(order, order.getOrderDetails());
        return ResponseEntity.ok(updatedOrder);
    }

    // Xóa đơn hàng
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
        Optional<EntityOrder> optionalOrder = serviceOrder.findById(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.status(404).body("Order not found");
        }
        serviceOrder.deleteById(id);
        return ResponseEntity.ok("Order deleted successfully");
    }
}