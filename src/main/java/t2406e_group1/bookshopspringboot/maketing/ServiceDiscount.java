package t2406e_group1.bookshopspringboot.maketing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceDiscount {

    @Autowired
    private JpaDiscount jpaDiscount;

    //  LẤY DANH SÁCH TẤT CẢ KHUYẾN MÃI
    public List<EntityDiscount> findAll() {
        return jpaDiscount.findAll();
    }

    //  LẤY THÔNG TIN KHUYẾN MÃI THEO ID
    public Optional<EntityDiscount> findById(int id) {
        return jpaDiscount.findById(id);
    }

    //  LẤY DANH SÁCH KHUYẾN MÃI THEO GIÁ SALE
  // LẤY THÔNG TIN KHUYẾN MÃI THEO GIÁ SALE
public List<EntityDiscount> findBySalePrice(float salePrice) {
    return jpaDiscount.findBySalePrice(salePrice);
}
  

    //  THÊM HOẶC CẬP NHẬT KHUYẾN MÃI
    public EntityDiscount saveEntityDiscount(EntityDiscount entityDiscount) {
        return jpaDiscount.save(entityDiscount);
    }

    //  XOÁ KHUYẾN MÃI THEO ID
    public void deleteById(int id) {
        jpaDiscount.deleteById(id);
    }

    //  KIỂM TRA XEM KHUYẾN MÃI CÓ TỒN TẠI KHÔNG
    public boolean existsById(int id) {
        return jpaDiscount.existsById(id);
    }
}
