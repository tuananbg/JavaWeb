package com.laptopshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SearchLienHeObject {

    private String trangThaiLienHe;
    private String tuNgay;
    private String denNgay;

    public SearchLienHeObject() {
        this.trangThaiLienHe = "";
        this.tuNgay = "";
        this.denNgay = "";
    }

}
