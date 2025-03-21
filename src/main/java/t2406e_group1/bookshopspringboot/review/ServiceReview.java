package t2406e_group1.bookshopspringboot.review;


import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// Bảo mật mật khẩu bằng spring security
import org.springframework.stereotype.Service;

// import t2406e_group1.bookshopspringboot.review.JpaReview;
// import t2406e_group1.bookshopspringboot.user.EntityUser;
// import t2406e_group1.bookshopspringboot.user.JpaUser;

import java.util.List;
import java.util.Optional;

@Service
// Sử dụng constructor injection thay vì field injection:
public class ServiceReview {
      private final JpaReview jpaReview;

          @Autowired
    public ServiceReview(JpaReview jpaReview) {
        this.jpaReview = jpaReview;
    }

      public EntityReview saveEntityReview(EntityReview entityReview) {
        return jpaReview.save(entityReview);
    }
      
    public Optional<EntityReview> findByTenKhach(String tenKhach) { // Phương thức tìm kiếm theo tên khách
        return jpaReview.findByTenKhach(tenKhach);
    }

    public List<EntityReview> findAll() {
        return jpaReview.findAll();
    }

    public Optional<EntityReview> findById(int id) {
        return jpaReview.findById(id);
    }

    public void deleteById(int id) {
        jpaReview.deleteById(id);
    }

    public boolean existsById(int id) {
        return jpaReview.existsById(id);
    }
    
}
