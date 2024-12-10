package com.laptopshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "chi_muc_gio_hang")
public class ChiMucGioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ma_san_pham")
    private SanPham sanPham;

    @Column(name = "so_luong")
    private int so_luong;

    @ManyToOne
    @JoinColumn(name = "ma_gio_hang")
    private GioHang gioHang;

}
