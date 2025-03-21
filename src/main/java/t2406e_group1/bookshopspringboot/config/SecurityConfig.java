package t2406e_group1.bookshopspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import t2406e_group1.bookshopspringboot.auth.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // Tắt CSRF để tránh lỗi với Postman
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/test/**").permitAll() // Chấp nhận tất cả từ API này
            .requestMatchers("/discounts/**").permitAll() // Chấp nhận tất cả từ API này

                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Cho phép preflight requests.
                // Cho phép OPTIONS request (cho CORS) và cho phép truy cập /api/auth/login mà không cần token:
                // khi frontend gửi sẽ gửi OPTIONS trước HEADER nên ko có sự cho phéo này sẽ lỗi
                .requestMatchers("/api/auth/login").permitAll() // ⚠️ Đảm bảo dòng này có mặt permitAll() để mở API login (không cần JWT)
                .requestMatchers(HttpMethod.POST, "/api/user").permitAll() // Cho phép đăng ký tài khoản mà không cần JWT(phải đặt trên authenticate)
                // CÁC CÁI CÓ ROLE CHO Ở DƯỚI
                .requestMatchers(HttpMethod.GET, "/api/user/**").hasRole("ADMIN") // Chỉ ADMIN xem danh sách User
                // Dùng .hasRole("USER") hoặc .hasRole("ADMIN") (không cần tiền tố ROLE_ vì Spring tự thêm)
                // .requestMatchers("/api/user/**").authenticated() // Yêu cầu có JWT để truy cập các API liên quan đến user (đăng ký thì loại trừ bên JwTFilter)
                .requestMatchers("/api/user/**").hasRole("ADMIN") // Admin quản lý user
                .requestMatchers(HttpMethod.GET, "/api/product/**").hasRole("USER") // USER được xem sản phẩm
                .requestMatchers("/api/product/**").hasRole("ADMIN") // Chỉ admin thêm sửa xóa sản phẩm
                .anyRequest().permitAll()

                // THÊM CÁC PHƯƠNG THỨC KHÁC Ở ĐÂY
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // Thêm JwtFilter
            .build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // khai báo phần này để sử dụng mã hóa mật khẩu

}

// Mở /api/auth/login cho tất cả
// Chặn API khác nếu không có JWT
