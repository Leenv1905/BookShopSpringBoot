package t2406e_group1.bookshopspringboot.user;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// Bảo mật mật khẩu bằng spring security
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// Sử dụng constructor injection thay vì field injection:
public class ServiceUser {
    private final JpaUser jpaUser;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ServiceUser(JpaUser jpaUser, PasswordEncoder passwordEncoder) {
        this.jpaUser = jpaUser;
        this.passwordEncoder = passwordEncoder;
    }

    // public EntityUser saveEntityUser(EntityUser entityUser) {
    // KHÔNG MÃ HÓA MẬT KHẨU
    // entityUser.setDateJoin(new java.sql.Date(System.currentTimeMillis()));
    // return jpaUser.save(entityUser); }

    // MÃ HÓA MẬT KHẨU TRƯỚC KHI LƯU
    public EntityUser saveEntityUser(EntityUser entityUser) {
        entityUser.setDateJoin(new java.sql.Date(System.currentTimeMillis()));
        entityUser.setPassword(passwordEncoder.encode(entityUser.getPassword())); // Mã hóa mật khẩu
        if (entityUser.getRoles() == null || entityUser.getRoles().isEmpty()) {
            entityUser.setRoles("ROLE_USER");  // Gán vai trò mặc định trước khi lưu
        }
        return jpaUser.save(entityUser);
    }

    // kiểm tra mật khẩu bằng passwordEncoder.matches(), chứ không so sánh trực tiếp
    // chuỗi
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword); // Dùng BCrypt
        // để kiểm tra mật khẩu
        // return rawPassword.equals(encodedPassword); // Không mã hóa, so sánh trực
        // tiếp, DÙng tạm khi mật khẩu trong
        // database chưa mã hóa
    }
    // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    // return passwordEncoder.matches(rawPassword, encodedPassword);

    public Optional<EntityUser> findByEmail(String email) { // Phương thức tìm kiếm theo email
        return jpaUser.findByEmail(email);
    }

    public List<EntityUser> findAll() {
        return jpaUser.findAll();
    }

    public Optional<EntityUser> findById(int id) {
        return jpaUser.findById(id);
    }

    public void deleteById(int id) {
        jpaUser.deleteById(id);
    }

    public boolean existsById(int id) {
        return jpaUser.existsById(id);
    }
 //phương thức lấy User theo vai trò
    public List<EntityUser> findByRoles(String roles) {
        return jpaUser.findByRolesContaining(roles);
    }

}
