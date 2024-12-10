package com.laptopshop.service.impl;

import com.laptopshop.entities.DanhMuc;
import com.laptopshop.repository.DanhMucRepository;
import com.laptopshop.service.DanhMucService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DanhMucServiceImpl implements DanhMucService {

    private final DanhMucRepository danhMucRepository;

    @Override
    public DanhMuc save(DanhMuc d) {
        return this.danhMucRepository.save(d);
    }

    @Override
    public DanhMuc update(DanhMuc d) {
        return this.danhMucRepository.save(d);
    }

    @Override
    public void deleteById(long id) {
        this.danhMucRepository.deleteById(id);
    }

    @Override
    public Page<DanhMuc> getAllDanhMucForPageable(int page, int size) {
        return this.danhMucRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public DanhMuc getDanhMucById(long id) {
        return this.danhMucRepository.findById(id).get();
    }

    @Override
    public List<DanhMuc> getAllDanhMuc() {
        return this.danhMucRepository.findAll();
    }

}
