package com.laptopshop.api.admin;

import com.laptopshop.dto.SearchDonHangObject;
import com.laptopshop.entities.ChiTietDonHang;
import com.laptopshop.entities.DonHang;
import com.laptopshop.entities.SanPham;
import com.laptopshop.service.DonHangService;
import com.laptopshop.service.NguoiDungService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/don-hang")
@RequiredArgsConstructor
public class DonHangApi {

    private final DonHangService donHangService;

    private final NguoiDungService nguoiDungService;

    // lấy danh sách đơn hàng theo search object
    @GetMapping("/all")
    public Page<DonHang> getDonHangByFilter(@RequestParam(defaultValue = "1") int page, @RequestParam String trangThai,
                                            @RequestParam String tuNgay, @RequestParam String denNgay) throws ParseException {

        SearchDonHangObject object = new SearchDonHangObject();
        object.setDenNgay(denNgay);
        object.setTrangThaiDon(trangThai);
        object.setTuNgay(tuNgay);
        Page<DonHang> listDonHang = this.donHangService.getAllDonHangByFilter(object, page);
        return listDonHang;
    }

    @GetMapping("/{id}")
    public DonHang getDonHangById(@PathVariable long id) {
        return this.donHangService.findById(id);
    }

    // phân công đơn hàng
    @PostMapping("/assign")
    public void phanCongDonHang(@RequestParam("shipper") String emailShipper,
                                @RequestParam("donHangId") long donHangId) {
        DonHang dh = this.donHangService.findById(donHangId);
        dh.setTrangThaiDonHang("Đang giao");
        dh.setShipper(this.nguoiDungService.findByEmail(emailShipper));

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            String dateStr = format.format(new Date());
            Date date = format.parse(dateStr);
            dh.setNgayGiaoHang(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.donHangService.save(dh);
    }

    // xác nhận hoàn thành đơn hàng
    @PostMapping("/update")
    public void xacNhanHoanThanhDon(@RequestParam("donHangId") long donHangId,
                                    @RequestParam("ghiChu") String ghiChuAdmin) {
        DonHang dh = this.donHangService.findById(donHangId);

        for (ChiTietDonHang ct : dh.getDanhSachChiTiet()) {
            SanPham sp = ct.getSanPham();
            sp.setDonViBan(sp.getDonViBan() + ct.getSoLuongNhanHang());
            sp.setDonViKho(sp.getDonViKho() - ct.getSoLuongNhanHang());
        }
        dh.setTrangThaiDonHang("Hoàn thành");
        String ghiChu = dh.getGhiChu();
        if (!ghiChuAdmin.equals("")) {
            ghiChu += "<br> Ghi chú admin:\n" + ghiChuAdmin;
        }
        dh.setGhiChu(ghiChu);
        this.donHangService.save(dh);
    }

    // xác nhận hoàn thành đơn hàng
    @PostMapping("/cancel")
    public void huyDonHangAdmin(@RequestParam("donHangId") long donHangId) {
        DonHang dh = this.donHangService.findById(donHangId);
        dh.setTrangThaiDonHang("Đã bị hủy");
        this.donHangService.save(dh);
    }

    // lấy dữ liệu làm báo cáo thống kê
    @GetMapping("/report")
    public List<Object> test() {
        return this.donHangService.layDonHangTheoThangVaNam();
    }
}
