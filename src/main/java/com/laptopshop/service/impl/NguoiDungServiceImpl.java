package com.laptopshop.service.impl;

import com.laptopshop.dto.TaiKhoanDTO;
import com.laptopshop.entities.NguoiDung;
import com.laptopshop.entities.VaiTro;
import com.laptopshop.repository.NguoiDungRepository;
import com.laptopshop.repository.VaiTroRepository;
import com.laptopshop.service.NguoiDungService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class NguoiDungServiceImpl implements NguoiDungService {

    private final NguoiDungRepository nguoiDungRepository;

    private final VaiTroRepository vaiTroRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public NguoiDung findByEmail(String email) {
        return this.nguoiDungRepository.findByEmail(email);
    }

    @Override
    public NguoiDung findByConfirmationToken(String confirmationToken) {
        return null;
    }

    @Override
    public NguoiDung saveUserForMember(NguoiDung nd) {
        nd.setPassword(this.bCryptPasswordEncoder.encode(nd.getPassword()));
        Set<VaiTro> setVaiTro = new HashSet<>();
        setVaiTro.add(this.vaiTroRepository.findByTenVaiTro("ROLE_MEMBER"));
        nd.setVaiTro(setVaiTro);
        return this.nguoiDungRepository.save(nd);
    }

    @Override
    public NguoiDung findById(long id) {
        NguoiDung nd = this.nguoiDungRepository.findById(id).get();
        return nd;
    }

    @Override
    public NguoiDung updateUser(NguoiDung nd) {
        return this.nguoiDungRepository.save(nd);
    }

    @Override
    public void changePass(NguoiDung nd, String newPass) {
        nd.setPassword(this.bCryptPasswordEncoder.encode(newPass));
        this.nguoiDungRepository.save(nd);
    }

    @Override
    public Page<NguoiDung> getNguoiDungByVaiTro(Set<VaiTro> vaiTro, int page) {
        return this.nguoiDungRepository.findByVaiTro(vaiTro, PageRequest.of(page - 1, 6));
    }

    @Override
    public List<NguoiDung> getNguoiDungByVaiTro(Set<VaiTro> vaiTro) {
        return this.nguoiDungRepository.findByVaiTro(vaiTro);
    }

    @Override
    public NguoiDung saveUserForAdmin(TaiKhoanDTO dto) {
        NguoiDung nd = new NguoiDung();
        nd.setHoTen(dto.getHoTen());
        nd.setDiaChi(dto.getDiaChi());
        nd.setEmail(dto.getEmail());
        nd.setSoDienThoai(dto.getSdt());
        nd.setPassword(this.bCryptPasswordEncoder.encode(dto.getPassword()));

        Set<VaiTro> vaiTro = new HashSet<>();
        vaiTro.add(this.vaiTroRepository.findByTenVaiTro(dto.getTenVaiTro()));
        nd.setVaiTro(vaiTro);

        return this.nguoiDungRepository.save(nd);
    }

    @Override
    public void deleteById(long id) {
        this.nguoiDungRepository.deleteById(id);
    }

}
