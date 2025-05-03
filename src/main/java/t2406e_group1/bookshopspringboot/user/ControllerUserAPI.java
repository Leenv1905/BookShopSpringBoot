package t2406e_group1.bookshopspringboot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class ControllerUserAPI {

    @Autowired
    private ServiceUser serviceUser;

    // LẤY THÔNG TIN TẤT CẢ USER VỚI PHÂN TRANG
    @GetMapping
    public ResponseEntity<Page<EntityUser>> getAllUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EntityUser> userPage = serviceUser.findAll(pageable);
        return ResponseEntity.ok(userPage);
    }

    // LẤY THÔNG TIN USER THEO ID
    @GetMapping("/{id}")
    public ResponseEntity<EntityUser> getUserById(@PathVariable int id) {
        Optional<EntityUser> entityUser = serviceUser.findById(id);
        return entityUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // THÊM MỚI USER
    @PostMapping
    public ResponseEntity<EntityUser> createUser(@RequestBody EntityUser entityUser) {
        if (entityUser.getEmail() == null || entityUser.getPassword() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        EntityUser savedUser = serviceUser.saveEntityUser(entityUser);
        return ResponseEntity.ok(savedUser);
    }

    // SỬA USER THEO ID
    @PutMapping("/{id}")
    public ResponseEntity<EntityUser> updateUser(@PathVariable int id, @RequestBody EntityUser userDetails) {
        Optional<EntityUser> optionalEntityUser = serviceUser.findById(id);
        if (optionalEntityUser.isPresent()) {
            EntityUser entityUser = optionalEntityUser.get();
            entityUser.setFullName(userDetails.getFullName());
            entityUser.setPhoneNumber(userDetails.getPhoneNumber());
            entityUser.setEmail(userDetails.getEmail());
            if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
                entityUser.setPassword(userDetails.getPassword());
            }
            entityUser.setDateJoin(userDetails.getDateJoin());
            entityUser.setBirthDay(userDetails.getBirthDay());
            entityUser.setAddress(userDetails.getAddress());
            entityUser.setRoles(userDetails.getRoles());
            entityUser.setGender(userDetails.getGender());
            entityUser.setAvatar(userDetails.getAvatar());
            return ResponseEntity.ok(serviceUser.saveEntityUser(entityUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // XÓA USER THEO ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        if (serviceUser.existsById(id)) {
            serviceUser.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


// package t2406e_group1.bookshopspringboot.user;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/user")
// public class ControllerUserAPI {

//     @Autowired
//     private ServiceUser serviceUser;

//     // LẤY THÔNG TIN TẤT CẢ USER
//     @GetMapping
//     public List<EntityUser> getAllUser() { // Lấy danh sách tất cả user
//         return serviceUser.findAll(); // Gọi phương thức findAll() từ ServiceUser
//     }

//     // LẤY THÔNG TIN USER THEO ID
//     @GetMapping("/{id}")
//     public ResponseEntity<EntityUser> getUserById(@PathVariable int id) {
//     Optional<EntityUser> entityUser = serviceUser.findById(id); // Gọi phương thức
//     // findById() từ ServiceUser
//     return entityUser.map(ResponseEntity::ok).orElseGet(() ->
//     ResponseEntity.notFound().build());
//     }

//     // THÊM MỚI USER
//     // ĐĂNG KÝ XONG SẼ KHÔNG TRẢ VỀ TOKEN MÀ PHẢI ĐĂNG NHẬP
//     @PostMapping
//     public EntityUser createUser(@RequestBody EntityUser entityUser) {
//     return serviceUser.saveEntityUser(entityUser);
//     }
//     // Gọi phương thức saveEntityUser() từ
//     // EntityUser
    


//     // SỬA USER THEO ID
//     @PutMapping("/{id}")
//     public ResponseEntity<EntityUser> updateUser(@PathVariable int id, @RequestBody
//     EntityUser userDetails) {
//     Optional<EntityUser> optionalEntityUser = serviceUser.findById(id); // Gọi phương thức
//     // findById() từ ServiceUser
//     if (optionalEntityUser.isPresent()) {
//     EntityUser entityUser = optionalEntityUser.get();
//     entityUser.setFullName(userDetails.getFullName());
//     entityUser.setPhoneNumber(userDetails.getPhoneNumber());
//     entityUser.setEmail(userDetails.getEmail());
//     entityUser.setPassword(userDetails.getPassword());
//     entityUser.setDateJoin(userDetails.getDateJoin());
//     entityUser.setBirthDay(userDetails.getBirthDay());
//     entityUser.setAddress(userDetails.getAddress());
//     entityUser.setRoles(userDetails.getRoles()); // Sửa vai trò người dùng
//     return ResponseEntity.ok(serviceUser.saveEntityUser(entityUser)); // Gọi phương
//     // thức saveEntityUser() từ ServiceUser
//     } else {
//     return ResponseEntity.notFound().build();
//     }
//     }


//     // XÓA USER THEO ID
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteUser(@PathVariable int id) {
//     if (serviceUser.existsById(id)) { // Gọi phương thức existsById() từ
//     // ServiceUser (Nếu có tồn tại id)
//     serviceUser.deleteById(id); // Gọi phương thức deleteById() từ
//     // ServiceUser (Thì xóa id đó)
//     return ResponseEntity.noContent().build();
//     } else {
//     return ResponseEntity.notFound().build();
//     }
//     }

// }
