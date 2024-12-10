package com.laptopshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@NoArgsConstructor
@AllArgsConstructor
public class SearchDonHangObject {

    private String trangThaiDon;
    private String tuNgay;
    private String denNgay;

    public SearchDonHangObject() {
        this.trangThaiDon = "";
        this.tuNgay = "";
        this.denNgay = "";
    }

    @Override
    public String toString() {
        return "SearchDonHangObject [trangThaiDon=" + this.trangThaiDon + ", tuNgay=" + this.tuNgay + ", denNgay=" + this.denNgay
                + "]";
    }


}
