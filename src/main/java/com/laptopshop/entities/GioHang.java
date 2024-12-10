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
@Entity(name = "gio_hang")
public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String tong_tien;

    @OneToOne
    @JoinColumn(name = "ma_nguoi_dung")
    private NguoiDung nguoiDung;

}
