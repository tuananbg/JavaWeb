package com.laptopshop.controller;

import com.laptopshop.entities.*;
import com.laptopshop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@SessionAttributes("loggedInUser")
@RequiredArgsConstructor
public class CheckOutController {

    private final SanPhamService sanPhamService;

    private final NguoiDungService nguoiDungService;

    private final GioHangService gioHangService;

    private final ChiMucGioHangService chiMucGioHangService;

    private final DonHangService donHangService;

    private final ChiTietDonHangService chiTietDonHangService;

    @ModelAttribute("loggedInUser")
    public NguoiDung loggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return this.nguoiDungService.findByEmail(auth.getName());
    }

    public NguoiDung getSessionUser(HttpServletRequest request) {
        return (NguoiDung) request.getSession().getAttribute("loggedInUser");
    }

    @GetMapping("/checkout")
    public String checkoutPage(HttpServletRequest res, Model model) {
        NguoiDung currentUser = this.getSessionUser(res);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<Long, String> quanity = new HashMap<>();
        List<SanPham> listsp = new ArrayList<>();

        if (auth == null || auth.getPrincipal() == "anonymousUser")     //Lay tu cookie
        {
            Cookie[] cl = res.getCookies();
            Set<Long> idList = new HashSet<>();
            for (int i = 0; i < cl.length; i++) {
                if (cl[i].getName().matches("[0-9]+")) {
                    idList.add(Long.parseLong(cl[i].getName()));
                    quanity.put(Long.parseLong(cl[i].getName()), cl[i].getValue());
                }

            }
            listsp = this.sanPhamService.getAllSanPhamByList(idList);
        } else     //Lay tu database
        {
            GioHang g = this.gioHangService.getGioHangByNguoiDung(currentUser);
            if (g != null) {
                List<ChiMucGioHang> listchimuc = this.chiMucGioHangService.getChiMucGioHangByGioHang(g);

                for (ChiMucGioHang c : listchimuc) {

                    listsp.add(c.getSanPham());
                    quanity.put(c.getSanPham().getId(), Integer.toString(c.getSo_luong()));

                }
            }
        }

        model.addAttribute("cart", listsp);
        model.addAttribute("quanity", quanity);
        model.addAttribute("user", currentUser);
        model.addAttribute("donhang", new DonHang());

        return "client/checkout";
    }

    @PostMapping(value = "/thankyou")
    public String thankyouPage(@ModelAttribute("donhang") DonHang donhang, HttpServletRequest req, HttpServletResponse response, Model model) {
        donhang.setNgayDatHang(new Date());
        donhang.setTrangThaiDonHang("Đang chờ giao");

        NguoiDung currentUser = this.getSessionUser(req);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<Long, String> quanity = new HashMap<>();
        List<SanPham> listsp = new ArrayList<>();
        List<ChiTietDonHang> listDetailDH = new ArrayList<>();

        if (auth == null || auth.getPrincipal() == "anonymousUser")     //Lay tu cookie
        {
            DonHang d = this.donHangService.save(donhang);
            Cookie[] cl = req.getCookies();
            Set<Long> idList = new HashSet<>();
            for (int i = 0; i < cl.length; i++) {
                if (cl[i].getName().matches("[0-9]+")) {
                    idList.add(Long.parseLong(cl[i].getName()));
                    quanity.put(Long.parseLong(cl[i].getName()), cl[i].getValue());
                }
            }
            listsp = this.sanPhamService.getAllSanPhamByList(idList);
            for (SanPham sp : listsp) {
                ChiTietDonHang detailDH = new ChiTietDonHang();
                detailDH.setSanPham(sp);
                detailDH.setSoLuongDat(Integer.parseInt(quanity.get(sp.getId())));
                detailDH.setDonGia(Integer.parseInt(quanity.get(sp.getId())) * sp.getDonGia());
                detailDH.setDonHang(d);
                listDetailDH.add(detailDH);
            }
        } else     //Lay tu database
        {
            donhang.setNguoiDat(currentUser);
            DonHang d = this.donHangService.save(donhang);
            GioHang g = this.gioHangService.getGioHangByNguoiDung(currentUser);
            List<ChiMucGioHang> listchimuc = this.chiMucGioHangService.getChiMucGioHangByGioHang(g);
            for (ChiMucGioHang c : listchimuc) {
                ChiTietDonHang detailDH = new ChiTietDonHang();
                detailDH.setSanPham(c.getSanPham());
                detailDH.setDonGia(c.getSo_luong() * c.getSanPham().getDonGia());
                detailDH.setSoLuongDat(c.getSo_luong());
                detailDH.setDonHang(d);
                listDetailDH.add(detailDH);

                listsp.add(c.getSanPham());
                quanity.put(c.getSanPham().getId(), Integer.toString(c.getSo_luong()));
            }

        }

        this.chiTietDonHangService.save(listDetailDH);

        this.cleanUpAfterCheckOut(req, response);
        model.addAttribute("donhang", donhang);
        model.addAttribute("cart", listsp);
        model.addAttribute("quanity", quanity);
        return "client/thankYou";
    }

    public void cleanUpAfterCheckOut(HttpServletRequest request, HttpServletResponse response) {
        NguoiDung currentUser = this.getSessionUser(request);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getPrincipal() == "anonymousUser")    //Su dung cookie de luu
        {
            Cookie[] clientCookies = request.getCookies();
            for (int i = 0; i < clientCookies.length; i++) {
                if (clientCookies[i].getName().matches("[0-9]+")) {
                    clientCookies[i].setMaxAge(0);
                    clientCookies[i].setPath("/laptopshop");
                    response.addCookie(clientCookies[i]);
                }
            }
        } else //Su dung database de luu
        {
            GioHang g = this.gioHangService.getGioHangByNguoiDung(currentUser);
            List<ChiMucGioHang> c = this.chiMucGioHangService.getChiMucGioHangByGioHang(g);
            this.chiMucGioHangService.deleteAllChiMucGiohang(c);
        }
    }


}
