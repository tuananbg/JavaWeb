package com.laptopshop.repository;

import com.laptopshop.entities.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long>, QuerydslPredicateExecutor<SanPham> {
    List<SanPham> findFirst12ByDanhMucTenDanhMucContainingIgnoreCaseOrderByIdDesc(String dm);

    List<SanPham> findByIdIn(Set<Long> idList);

    @Query(value = "SELECT * FROM san_pham ORDER BY don_vi_kho DESC LIMIT 12", nativeQuery = true)
    List<SanPham> findTop12ByHighestStock();
}
