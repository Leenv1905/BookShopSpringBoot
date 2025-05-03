package t2406e_group1.bookshopspringboot.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EntityUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullName;
    private Integer phoneNumber;
    private String email;
    private String password;
    private Date dateJoin;
    private Date birthDay;
    private String address;
    private String roles;
    private String gender;
    private String avatar;

    public EntityUser() {
        this.fullName = "(chưa xác định)";
        this.roles = "ROLE_USER";
        this.gender = null; // Mặc định null
        this.avatar = null; // Mặc định null
    }

    public Boolean InputError() {
        var ipe = false;

        if (this.fullName.length() < 2) {
            ipe = true;
            print("\n Lỗi->Tên phải từ 2 kí tự trở lên: ");
        }

        if (this.fullName.length() > 22) {
            ipe = true;
            print("\n Lỗi->Tên phải không quá 22 kí tự. ");
        }

        return ipe;
    }

    private void print(String string) {
        throw new UnsupportedOperationException("Unimplemented method 'print'");
    }

    public List<String> getRoleList() {
        if (this.roles == null || this.roles.isEmpty()) {
            return new ArrayList<>();
        }
        return List.of(this.roles.split(","));
    }
}