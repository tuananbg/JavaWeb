package com.laptopshop.repository;

import com.laptopshop.entities.DonHang;
import com.laptopshop.entities.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Long>, QuerydslPredicateExecutor<DonHang> {

    public List<DonHang> findByTrangThaiDonHangAndShipper(String trangThai, NguoiDung shipper);

    @Query(value = "select DATE_FORMAT(dh.ngayNhanHang, '%m') as month, "
            + " DATE_FORMAT(dh.ngayNhanHang, '%Y') as year, sum(ct.soLuongNhanHang * ct.donGia) as total "
            + " from don_hang dh, chi_tiet_don_hang ct"
            + " where dh.id = ct.donHang.id and dh.trangThaiDonHang ='Hoàn thành'"
            + " group by DATE_FORMAT(dh.ngayNhanHang, '%Y%m')"
            + " order by year asc")
    public List<Object> layDonHangTheoThangVaNam();

    public List<DonHang> findByNguoiDat(NguoiDung ng);

    public int countByTrangThaiDonHang(String trangThaiDonHang);

}
