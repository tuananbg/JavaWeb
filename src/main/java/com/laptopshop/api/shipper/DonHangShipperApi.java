package com.laptopshop.api.shipper;

import com.laptopshop.dto.CapNhatDonHangShipper;
import com.laptopshop.dto.SearchDonHangObject;
import com.laptopshop.entities.ChiTietDonHang;
import com.laptopshop.entities.DonHang;
import com.laptopshop.entities.NguoiDung;
import com.laptopshop.service.DonHangService;
import com.laptopshop.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/shipper/don-hang")
public class DonHangShipperApi {

    @Autowired
    private DonHangService donHangService;

    @Autowired
    private NguoiDungService nguoiDungService;

    @GetMapping("/all")
    public Page<DonHang> getDonHangByFilter(@RequestParam(defaultValue = "1") int page, @RequestParam String trangThai,
                                            @RequestParam String tuNgay, @RequestParam String denNgay, @RequestParam long idShipper)
            throws ParseException {

        SearchDonHangObject object = new SearchDonHangObject();
        object.setDenNgay(denNgay);
        object.setTrangThaiDon(trangThai);
        object.setTuNgay(tuNgay);

        NguoiDung shipper = this.nguoiDungService.findById(idShipper);
        Page<DonHang> listDonHang = this.donHangService.findDonHangByShipper(object, page, 6, shipper);
        return listDonHang;
    }

    @GetMapping("/{id}")
    public DonHang getDonHangById(@PathVariable long id) {
        return this.donHangService.findById(id);
    }

    @PostMapping("/update")
    public void capNhatTrangThaiDonHang(@RequestBody CapNhatDonHangShipper capNhatDonHangShipper) {
        DonHang donHang = this.donHangService.findById(capNhatDonHangShipper.getIdDonHang());

        for (ChiTietDonHang chiTiet : donHang.getDanhSachChiTiet()) {
            for (CapNhatDonHangShipper.CapNhatChiTietDon chiTietCapNhat : capNhatDonHangShipper
                    .getDanhSachCapNhatChiTietDon()) {
                if (chiTiet.getId() == chiTietCapNhat.getIdChiTiet()) {
                    chiTiet.setSoLuongNhanHang(chiTietCapNhat.getSoLuongNhanHang());
                }
            }
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            String dateStr = format.format(new Date());
            Date date = format.parse(dateStr);
            donHang.setNgayNhanHang(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        donHang.setTrangThaiDonHang("Chờ duyệt");

        String ghiChu = capNhatDonHangShipper.getGhiChuShipper();

        if (!ghiChu.equals("")) {
            donHang.setGhiChu("Ghi chú shipper: \n" + capNhatDonHangShipper.getGhiChuShipper());
        }
        this.donHangService.save(donHang);

    }
}
