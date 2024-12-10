package com.laptopshop.service.impl;

import com.laptopshop.entities.ChiTietDonHang;
import com.laptopshop.repository.ChiTietDonHangRepository;
import com.laptopshop.service.ChiTietDonHangService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChiTietDonHangServiceImpl implements ChiTietDonHangService {

    private final ChiTietDonHangRepository chiTietDonHangRepository;

    @Override
    public List<ChiTietDonHang> save(List<ChiTietDonHang> list) {
        return this.chiTietDonHangRepository.saveAll(list);
    }
}
