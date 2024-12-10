package com.laptopshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@NoArgsConstructor
@AllArgsConstructor
public class SearchSanPhamObject {
    private String danhMucId;
    private String hangSXId;
    private String donGia;

    // sap xep theo gia
    private String sapXepTheoGia;
    private String[] keyword;
    private String sort;

    // sap xep theo danhmuc va hangsx
    private String brand;
    private String manufactor;

    // theo ram, pin, OS
    private String os;
    private String ram;
    private String pin;

    public SearchSanPhamObject() {
        this.danhMucId = "";
        this.hangSXId = "";
        this.donGia = "";
        this.sapXepTheoGia = "ASC";
    }

    @Override
    public String toString() {
        return "SearchObject [danhMucId=" + this.danhMucId + ", hangSXId=" + this.hangSXId + ", price=" + this.donGia + "]";
    }
}
