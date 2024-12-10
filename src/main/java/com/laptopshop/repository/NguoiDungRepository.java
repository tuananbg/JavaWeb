package com.laptopshop.repository;

import com.laptopshop.entities.NguoiDung;
import com.laptopshop.entities.VaiTro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Long> {

    NguoiDung findByEmail(String email);

    Page<NguoiDung> findByVaiTro(Set<VaiTro> vaiTro, Pageable of);

    List<NguoiDung> findByVaiTro(Set<VaiTro> vaiTro);
}
