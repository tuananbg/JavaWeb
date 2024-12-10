package com.laptopshop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "nguoi_dung")
public class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String email;

    @JsonIgnore
    private String password;

    @Transient
    @JsonIgnore
    private String confirmPassword;

    private String hoTen;

    private String soDienThoai;

    private String diaChi;

    @ManyToMany
    @JoinTable(name = "nguoidung_vaitro",
            joinColumns = @JoinColumn(name = "ma_nguoi_dung"),
            inverseJoinColumns = @JoinColumn(name = "ma_vai_tro"))
    private Set<VaiTro> vaiTro;

    @Transient
    @JsonIgnore
    private List<DonHang> listDonHang;


    public NguoiDung(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "NguoiDung [id=" + this.id + ", email=" + this.email + ", password=" + this.password + ", confirmPassword="
                + this.confirmPassword + ", hoTen=" + this.hoTen + ", soDienThoai=" + this.soDienThoai + ", diaChi=" + this.diaChi;
    }
}
