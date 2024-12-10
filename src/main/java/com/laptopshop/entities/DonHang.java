package com.laptopshop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "don_hang")
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "donHang")
    private List<ChiTietDonHang> danhSachChiTiet;

    private String diaChiNhan;

    private String sdtNhanHang;

    private String hoTenNguoiNhan;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date ngayDatHang;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date ngayGiaoHang;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date ngayNhanHang;

    private String trangThaiDonHang;

    private String ghiChu;

    @ManyToOne(optional = true)
    @JoinColumn(name = "ma_nguoi_dat")
    private NguoiDung nguoiDat;

    @ManyToOne(optional = true)
    @JoinColumn(name = "ma_shipper")
    private NguoiDung shipper;

    private long tongGiaTri;

}
