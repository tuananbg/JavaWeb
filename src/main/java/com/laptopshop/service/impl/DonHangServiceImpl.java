package com.laptopshop.service.impl;

import com.laptopshop.dto.SearchDonHangObject;
import com.laptopshop.entities.DonHang;
import com.laptopshop.entities.NguoiDung;
import com.laptopshop.entities.QDonHang;
import com.laptopshop.repository.DonHangRepository;
import com.laptopshop.service.DonHangService;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonHangServiceImpl implements DonHangService {

    private final DonHangRepository donHangRepository;

    @Override
    public Page<DonHang> getAllDonHangByFilter(SearchDonHangObject object, int page) throws ParseException {
        BooleanBuilder builder = new BooleanBuilder();

        String trangThaiDon = object.getTrangThaiDon();
        String tuNgay = object.getTuNgay();
        String denNgay = object.getDenNgay();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

        if (!trangThaiDon.equals("")) {
            builder.and(QDonHang.donHang.trangThaiDonHang.eq(trangThaiDon));
        }

        if (!tuNgay.equals("") && tuNgay != null) {
            if (trangThaiDon.equals("") || trangThaiDon.equals("Đang chờ giao") || trangThaiDon.equals("Đã hủy")) {
                builder.and(QDonHang.donHang.ngayDatHang.goe(formatDate.parse(tuNgay)));
            } else if (trangThaiDon.equals("Đang giao")) {
                builder.and(QDonHang.donHang.ngayGiaoHang.goe(formatDate.parse(tuNgay)));
            } else { // hoàn thành
                builder.and(QDonHang.donHang.ngayNhanHang.goe(formatDate.parse(tuNgay)));
            }
        }

        if (!denNgay.equals("") && denNgay != null) {
            if (trangThaiDon.equals("") || trangThaiDon.equals("Đang chờ giao") || trangThaiDon.equals("Đã hủy")) {
                builder.and(QDonHang.donHang.ngayDatHang.loe(formatDate.parse(denNgay)));
            } else if (trangThaiDon.equals("Đang giao")) {
                builder.and(QDonHang.donHang.ngayGiaoHang.loe(formatDate.parse(denNgay)));
            } else { // hoàn thành
                builder.and(QDonHang.donHang.ngayNhanHang.loe(formatDate.parse(denNgay)));
            }
        }

        return this.donHangRepository.findAll(builder, PageRequest.of(page - 1, 6));
    }

    @Override
    public DonHang update(DonHang dh) {
        return this.donHangRepository.save(dh);
    }

    @Override
    public DonHang findById(long id) {
        return this.donHangRepository.findById(id).get();
    }

    @Override
    public List<DonHang> findByTrangThaiDonHangAndShipper(String trangThai, NguoiDung shipper) {
        return this.donHangRepository.findByTrangThaiDonHangAndShipper(trangThai, shipper);
    }

    @Override
    public DonHang save(DonHang dh) {
        return this.donHangRepository.save(dh);
    }

    @Override
    public List<Object> layDonHangTheoThangVaNam() {
        return this.donHangRepository.layDonHangTheoThangVaNam();
    }

    @Override
    public List<DonHang> getDonHangByNguoiDung(NguoiDung ng) {
        return this.donHangRepository.findByNguoiDat(ng);
    }

    @Override
    public Page<DonHang> findDonHangByShipper(SearchDonHangObject object, int page, int size, NguoiDung shipper) throws ParseException {
        BooleanBuilder builder = new BooleanBuilder();

        String trangThaiDon = object.getTrangThaiDon();
        String tuNgay = object.getTuNgay();
        String denNgay = object.getDenNgay();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");

        builder.and(QDonHang.donHang.shipper.eq(shipper));

        if (!trangThaiDon.equals("")) {
            builder.and(QDonHang.donHang.trangThaiDonHang.eq(trangThaiDon));
        }

        if (!tuNgay.equals("") && tuNgay != null) {
            if (trangThaiDon.equals("Đang giao")) {
                builder.and(QDonHang.donHang.ngayGiaoHang.goe(formatDate.parse(tuNgay)));
            } else { // hoàn thành
                builder.and(QDonHang.donHang.ngayNhanHang.goe(formatDate.parse(tuNgay)));
            }
        }

        if (!denNgay.equals("") && denNgay != null) {
            if (trangThaiDon.equals("Đang giao")) {
                builder.and(QDonHang.donHang.ngayGiaoHang.loe(formatDate.parse(denNgay)));
            } else { // hoàn thành
                builder.and(QDonHang.donHang.ngayNhanHang.loe(formatDate.parse(denNgay)));
            }
        }

        return this.donHangRepository.findAll(builder, PageRequest.of(page - 1, size));
    }

    @Override
    public int countByTrangThaiDonHang(String trangThaiDonHang) {
        return this.donHangRepository.countByTrangThaiDonHang(trangThaiDonHang);
    }

}
