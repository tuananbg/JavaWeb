package com.laptopshop.service.impl;

import com.laptopshop.entities.VaiTro;
import com.laptopshop.repository.VaiTroRepository;
import com.laptopshop.service.VaiTroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VaiTroServiceImpl implements VaiTroService {

    private final VaiTroRepository vaiTroRepository;

    @Override
    public VaiTro findByTenVaiTro(String tenVaiTro) {
        return this.vaiTroRepository.findByTenVaiTro(tenVaiTro);
    }

    @Override
    public List<VaiTro> findAllVaiTro() {
        return this.vaiTroRepository.findAll();
    }


}
