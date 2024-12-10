package com.laptopshop.repository;

import com.laptopshop.entities.ChiMucGioHang;
import com.laptopshop.entities.GioHang;
import com.laptopshop.entities.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiMucGioHangRepository extends JpaRepository<ChiMucGioHang, Long> {

    ChiMucGioHang findBySanPhamAndGioHang(SanPham sp, GioHang g);

    List<ChiMucGioHang> findByGioHang(GioHang g);

}
