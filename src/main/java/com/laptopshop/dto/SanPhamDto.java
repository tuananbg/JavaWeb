package com.laptopshop.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SanPhamDto {

    private String id;

    private String tenSanPham;

    private String donGia;

    private String donViKho;

    private String thongTinBaoHanh;

    private String thongTinChung;

    private String manHinh;

    private String heDieuHanh;

    private String cpu;

    private String ram;

    private String thietKe;

    private String dungLuongPin;

    private long danhMucId;

    private long nhaSXId;

    private MultipartFile hinhAnh;

    @Override
    public String toString() {
        return "SanPhamDto [id=" + this.id + ", tenSanPham=" + this.tenSanPham + ", donGia=" + this.donGia + ", donViKho=" + this.donViKho
                + ", thongTinBaoHanh=" + this.thongTinBaoHanh + ", thongTinChung=" + this.thongTinChung + ", manHinh=" + this.manHinh
                + ", heDieuHanh=" + this.heDieuHanh + ", cpu=" + this.cpu + ", ram=" + this.ram + ", thietKe=" + this.thietKe
                + ", dungLuongPin=" + this.dungLuongPin + ", danhMucId=" + this.danhMucId + ", nhaSXId=" + this.nhaSXId + ", hinhAnh="
                + this.hinhAnh + "]";
    }
}
