package com.laptopshop.service.impl;

import com.laptopshop.dto.SanPhamDto;
import com.laptopshop.dto.SearchSanPhamObject;
import com.laptopshop.entities.QSanPham;
import com.laptopshop.entities.SanPham;
import com.laptopshop.repository.DanhMucRepository;
import com.laptopshop.repository.HangSanXuatRepository;
import com.laptopshop.repository.SanPhamRepository;
import com.laptopshop.service.SanPhamService;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SanPhamServiceImpl implements SanPhamService {

    private final SanPhamRepository sanPhamRepository;

    private final DanhMucRepository danhMucRepository;

    private final HangSanXuatRepository hangSanXuatRepository;

    // đổi từ SanPhamDto sang đối tượng SanPham để add vào db
    public SanPham convertFromSanPhamDto(SanPhamDto dto) {
        SanPham sanPham = new SanPham();
        if (!dto.getId().equals("")) {
            sanPham.setId(Long.parseLong(dto.getId()));
        }
        sanPham.setTenSanPham(dto.getTenSanPham());
        sanPham.setCpu(dto.getCpu());
        sanPham.setDanhMuc(this.danhMucRepository.findById(dto.getDanhMucId()).get());
        sanPham.setHangSanXuat(this.hangSanXuatRepository.findById(dto.getNhaSXId()).get());
        sanPham.setDonGia(Long.parseLong(dto.getDonGia()));
        sanPham.setThietKe(dto.getThietKe());
        sanPham.setThongTinBaoHanh(dto.getThongTinBaoHanh());
        sanPham.setThongTinChung(dto.getThongTinChung());
        sanPham.setManHinh(dto.getManHinh());
        sanPham.setRam(dto.getRam());
        sanPham.setDungLuongPin(dto.getDungLuongPin());
        sanPham.setDonViKho(Integer.parseInt(dto.getDonViKho()));
        sanPham.setHeDieuHanh(dto.getHeDieuHanh());

        return sanPham;
    }

    @Override
    public SanPham save(SanPhamDto dto) {
        SanPham sp = this.convertFromSanPhamDto(dto);
        System.out.println(sp);
        return this.sanPhamRepository.save(sp);
    }

    @Override
    public SanPham update(SanPhamDto dto) {
        return this.sanPhamRepository.save(this.convertFromSanPhamDto(dto));
    }

    @Override
    public void deleteById(long id) {
        this.sanPhamRepository.deleteById(id);
    }

    @Override
    public Page<SanPham> getAllSanPhamByFilter(SearchSanPhamObject object, int page, int limit) {
        BooleanBuilder builder = new BooleanBuilder();
        String price = object.getDonGia();

        // sắp xếp theo giá
        Sort sort = Sort.by(Direction.ASC, "donGia"); // mặc định tăng dần
        if (object.getSapXepTheoGia().equals("desc")) { // giảm dần
            sort = Sort.by(Direction.DESC, "donGia");
        }

        if (!object.getDanhMucId().equals("") && object.getDanhMucId() != null) {
            builder.and(QSanPham.sanPham.danhMuc.eq(this.danhMucRepository.findById(Long.parseLong(object.getDanhMucId())).get()));
        }

        if (!object.getHangSXId().equals("") && object.getHangSXId() != null) {
            builder.and(QSanPham.sanPham.hangSanXuat
                    .eq(this.hangSanXuatRepository.findById(Long.parseLong(object.getHangSXId())).get()));
        }

        // tim theo don gia
        switch (price) {
            case "duoi-2-trieu":
                builder.and(QSanPham.sanPham.donGia.lt(2000000));
                break;

            case "2-trieu-den-4-trieu":
                builder.and(QSanPham.sanPham.donGia.between(2000000, 4000000));
                break;

            case "4-trieu-den-6-trieu":
                builder.and(QSanPham.sanPham.donGia.between(4000000, 6000000));
                break;

            case "6-trieu-den-10-trieu":
                builder.and(QSanPham.sanPham.donGia.between(6000000, 10000000));
                break;

            case "tren-10-trieu":
                builder.and(QSanPham.sanPham.donGia.gt(10000000));
                break;

            default:
                break;
        }
        return this.sanPhamRepository.findAll(builder, PageRequest.of(page, limit, sort));
    }

    @Override
    public List<SanPham> getLatestSanPham() {
//        return this.sanPhamRepository.findFirst12ByDanhMucTenDanhMucContainingIgnoreCaseOrderByIdDesc("Laptop");
        return this.sanPhamRepository.findTop12ByHighestStock();
    }

    @Override
    public Iterable<SanPham> getSanPhamByTenSanPhamWithoutPaginate(SearchSanPhamObject object) {
        BooleanBuilder builder = new BooleanBuilder();
        final int resultPerPage = 12;
        String[] keywords = object.getKeyword();
        String sort = object.getSort();
        String price = object.getDonGia();
        // Keyword
        builder.and(QSanPham.sanPham.tenSanPham.like("%" + keywords[0] + "%"));
        if (keywords.length > 1) {
            for (int i = 1; i < keywords.length; i++) {
                builder.and(QSanPham.sanPham.tenSanPham.like("%" + keywords[i] + "%"));
            }
        }
        // Muc gia
        switch (price) {
            case "duoi-2-trieu":
                builder.and(QSanPham.sanPham.donGia.lt(2000000));
                break;

            case "2-trieu-den-4-trieu":
                builder.and(QSanPham.sanPham.donGia.between(2000000, 4000000));
                break;

            case "4-trieu-den-6-trieu":
                builder.and(QSanPham.sanPham.donGia.between(4000000, 6000000));
                break;

            case "6-trieu-den-10-trieu":
                builder.and(QSanPham.sanPham.donGia.between(6000000, 10000000));
                break;

            case "tren-10-trieu":
                builder.and(QSanPham.sanPham.donGia.gt(10000000));
                break;

            default:
                break;
        }
        return this.sanPhamRepository.findAll(builder);
    }

    @Override
    public SanPham getSanPhamById(long id) {
        return this.sanPhamRepository.findById(id).get();
    }

    // Tim kiem san pham theo keyword, sap xep, phan trang, loc theo muc gia, lay 12
    // san pham moi trang
    @Override
    public Page<SanPham> getSanPhamByTenSanPham(SearchSanPhamObject object, int page, int resultPerPage) {
        BooleanBuilder builder = new BooleanBuilder();
//		int resultPerPage = 12;
        String[] keywords = object.getKeyword();
        String sort = object.getSort();
        String price = object.getDonGia();
        String brand = object.getBrand();
        String manufactor = object.getManufactor();
        // Keyword
        builder.and(QSanPham.sanPham.tenSanPham.like("%" + keywords[0] + "%"));
        if (keywords.length > 1) {
            for (int i = 1; i < keywords.length; i++) {
                builder.and(QSanPham.sanPham.tenSanPham.like("%" + keywords[i] + "%"));
            }
        }
        // Muc gia
        switch (price) {
            case "duoi-2-trieu":
                builder.and(QSanPham.sanPham.donGia.lt(2000000));
                break;

            case "2-trieu-den-4-trieu":
                builder.and(QSanPham.sanPham.donGia.between(2000000, 4000000));
                break;

            case "4-trieu-den-6-trieu":
                builder.and(QSanPham.sanPham.donGia.between(4000000, 6000000));
                break;

            case "6-trieu-den-10-trieu":
                builder.and(QSanPham.sanPham.donGia.between(6000000, 10000000));
                break;

            case "tren-10-trieu":
                builder.and(QSanPham.sanPham.donGia.gt(10000000));
                break;

            default:
                break;
        }

        // Danh muc va hang san xuat
        if (brand.length() > 1) {
            builder.and(QSanPham.sanPham.danhMuc.tenDanhMuc.eq(brand));
        }
        if (manufactor.length() > 1) {
            builder.and(QSanPham.sanPham.hangSanXuat.tenHangSanXuat.eq(manufactor));
        }

        // Sap xep
        if (sort.equals("newest")) {
            return this.sanPhamRepository.findAll(builder, PageRequest.of(page - 1, resultPerPage, Sort.Direction.DESC, "id"));
        } else if (sort.equals("priceAsc")) {
            return this.sanPhamRepository.findAll(builder, PageRequest.of(page - 1, resultPerPage, Sort.Direction.ASC, "donGia"));
        } else if (sort.equals("priceDes")) {
            return this.sanPhamRepository.findAll(builder, PageRequest.of(page - 1, resultPerPage, Sort.Direction.DESC, "donGia"));
        }
        return this.sanPhamRepository.findAll(builder, PageRequest.of(page - 1, resultPerPage));
    }

    @Override
    public List<SanPham> getAllSanPhamByList(Set<Long> idList) {
        return this.sanPhamRepository.findByIdIn(idList);
    }

    @Override
    public Page<SanPham> getSanPhamByTenSanPhamForAdmin(String tenSanPham, int page, int size) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QSanPham.sanPham.tenSanPham.like("%" + tenSanPham + "%"));
        return this.sanPhamRepository.findAll(builder, PageRequest.of(page, size));
    }


    @Override
    public Iterable<SanPham> getSanPhamByTenDanhMuc(String brand) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(QSanPham.sanPham.danhMuc.tenDanhMuc.eq(brand));
        return this.sanPhamRepository.findAll(builder);
    }

    @Override
    public Page<SanPham> getSanPhamByBrand(SearchSanPhamObject object, int page, int resultPerPage) {
        BooleanBuilder builder = new BooleanBuilder();
        String price = object.getDonGia();
        String brand = object.getBrand();
        String manufactor = object.getManufactor();
        String os = object.getOs();
        String ram = object.getRam();
        String pin = object.getPin();
        // Muc gia
        switch (price) {
            case "duoi-2-trieu":
                builder.and(QSanPham.sanPham.donGia.lt(2000000));
                break;

            case "2-trieu-den-4-trieu":
                builder.and(QSanPham.sanPham.donGia.between(2000000, 4000000));
                break;

            case "4-trieu-den-6-trieu":
                builder.and(QSanPham.sanPham.donGia.between(4000000, 6000000));
                break;

            case "6-trieu-den-10-trieu":
                builder.and(QSanPham.sanPham.donGia.between(6000000, 10000000));
                break;

            case "tren-10-trieu":
                builder.and(QSanPham.sanPham.donGia.gt(10000000));
                break;

            default:
                break;
        }

        // Danh muc va hang san xuat
        if (brand.length() > 1) {
            builder.and(QSanPham.sanPham.danhMuc.tenDanhMuc.eq(brand));
        }
        if (manufactor.length() > 1) {
            builder.and(QSanPham.sanPham.hangSanXuat.tenHangSanXuat.eq(manufactor));
        }
        if (os.length() > 1) {
            builder.and(QSanPham.sanPham.heDieuHanh.like("%" + os + "%"));
        }
        if (ram.length() > 1) {
            builder.and(QSanPham.sanPham.ram.like("%" + ram + "%"));
        }
        if (pin.length() > 1) {
            builder.and(QSanPham.sanPham.dungLuongPin.eq(pin));
        }

        return this.sanPhamRepository.findAll(builder, PageRequest.of(page - 1, resultPerPage));
    }
}
