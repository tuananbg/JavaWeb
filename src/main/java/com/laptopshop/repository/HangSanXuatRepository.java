package com.laptopshop.repository;

import com.laptopshop.entities.HangSanXuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HangSanXuatRepository extends JpaRepository<HangSanXuat, Long> {

}
