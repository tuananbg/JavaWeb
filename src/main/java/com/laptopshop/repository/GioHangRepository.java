package com.laptopshop.repository;

import com.laptopshop.entities.GioHang;
import com.laptopshop.entities.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, Long> {

    GioHang findByNguoiDung(NguoiDung n);

}
