package com.laptopshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "chi_tiet_don_hang")
public class ChiTietDonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "ma_san_pham")
    private SanPham sanPham;

    @Column(name = "don_gia")
    private long donGia;

    @Column(name = "so_luong_dat")
    private Integer soLuongDat;

    @Column(name = "so_luong_nhan_hang")
    private Integer soLuongNhanHang;

    @ManyToOne
    @JoinColumn(name = "ma_don_hang")
    @JsonIgnore
    private DonHang donHang;
}
