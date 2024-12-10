package com.laptopshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "hang_san_xuat")
public class HangSanXuat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Tên hãng sản xuất không được trống")
    private String tenHangSanXuat;

    @JsonIgnore
    @OneToMany(mappedBy = "hangSanXuat")
    private List<SanPham> listSanPham;

}
