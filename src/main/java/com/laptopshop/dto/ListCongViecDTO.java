package com.laptopshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ListCongViecDTO {

    private int soDonHangMoi; // số đơn hàng mới
    private int soLienHeMoi; // số liên hệ mới;
    private int soDonhangChoDuyet; // số đơn hàng chờ duyệt

}
