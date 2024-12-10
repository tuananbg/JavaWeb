package com.laptopshop.service.impl;

import com.laptopshop.entities.ChiMucGioHang;
import com.laptopshop.entities.GioHang;
import com.laptopshop.entities.SanPham;
import com.laptopshop.repository.ChiMucGioHangRepository;
import com.laptopshop.service.ChiMucGioHangService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChiMucGioHangServiceImpl implements ChiMucGioHangService {

    private final ChiMucGioHangRepository chiMucGioHangRepository;

    @Override
    public ChiMucGioHang getChiMucGioHangBySanPhamAndGioHang(SanPham sp, GioHang g) {
        return this.chiMucGioHangRepository.findBySanPhamAndGioHang(sp, g);
    }

    @Override
    public ChiMucGioHang saveChiMucGiohang(ChiMucGioHang c) {
        return this.chiMucGioHangRepository.save(c);
    }

    @Override
    public void deleteChiMucGiohang(ChiMucGioHang c) {
        this.chiMucGioHangRepository.delete(c);
    }

    @Override
    public List<ChiMucGioHang> getChiMucGioHangByGioHang(GioHang g) {
        return this.chiMucGioHangRepository.findByGioHang(g);
    }

    @Override
    public void deleteAllChiMucGiohang(List<ChiMucGioHang> c) {
        this.chiMucGioHangRepository.deleteAll(c);
    }

}
