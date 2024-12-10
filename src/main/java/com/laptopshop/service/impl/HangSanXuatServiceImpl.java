package com.laptopshop.service.impl;

import com.laptopshop.entities.HangSanXuat;
import com.laptopshop.repository.HangSanXuatRepository;
import com.laptopshop.service.HangSanXuatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HangSanXuatServiceImpl implements HangSanXuatService {

    private final HangSanXuatRepository hangSanXuatRepository;

    @Override
    public List<HangSanXuat> getALlHangSX() {
        return this.hangSanXuatRepository.findAll();
    }

    @Override
    public HangSanXuat getHSXById(long id) {
        return this.hangSanXuatRepository.findById(id).get();
    }

    @Override
    public HangSanXuat save(HangSanXuat h) {
        return this.hangSanXuatRepository.save(h);
    }

    @Override
    public HangSanXuat update(HangSanXuat h) {
        return this.hangSanXuatRepository.save(h);
    }

    @Override
    public void deleteById(long id) {
        this.hangSanXuatRepository.deleteById(id);
    }

    @Override
    public Page<HangSanXuat> getALlHangSX(int page, int size) {
        return this.hangSanXuatRepository.findAll(PageRequest.of(page, size));
    }

}
