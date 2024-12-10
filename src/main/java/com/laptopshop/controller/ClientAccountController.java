package com.laptopshop.controller;

import com.google.common.collect.Lists;
import com.laptopshop.dto.PasswordDTO;
import com.laptopshop.entities.DonHang;
import com.laptopshop.entities.NguoiDung;
import com.laptopshop.entities.ResponseObject;
import com.laptopshop.service.DonHangService;
import com.laptopshop.service.NguoiDungService;
import com.laptopshop.service.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/")
@RequiredArgsConstructor
public class ClientAccountController {

    private final SanPhamService sanPhamService;

    private final NguoiDungService nguoiDungService;

    private final DonHangService donHangService;

    private final BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("loggedInUser")
    public NguoiDung loggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return this.nguoiDungService.findByEmail(auth.getName());
    }

    public NguoiDung getSessionUser(HttpServletRequest request) {
        return (NguoiDung) request.getSession().getAttribute("loggedInUser");
    }

    @GetMapping("/account")
    public String accountPage(HttpServletRequest res, Model model) {
        NguoiDung currentUser = this.getSessionUser(res);
        model.addAttribute("user", currentUser);
        List<DonHang> list = Lists.reverse(this.donHangService.getDonHangByNguoiDung(currentUser));
        model.addAttribute("list", list);
        return "client/account";
    }

    @GetMapping("/changeInformation")
    public String clientChangeInformationPage(HttpServletRequest res, Model model) {
        NguoiDung currentUser = this.getSessionUser(res);
        model.addAttribute("user", currentUser);
        return "client/information";
    }

    @GetMapping("/changePassword")
    public String clientChangePasswordPage() {
        return "client/passwordChange";
    }

    @PostMapping("/updateInfo")
    @ResponseBody
    public ResponseObject commitChange(HttpServletRequest res, @RequestBody NguoiDung ng) {
        NguoiDung currentUser = this.getSessionUser(res);
        currentUser.setHoTen(ng.getHoTen());
        currentUser.setSoDienThoai(ng.getSoDienThoai());
        currentUser.setDiaChi(ng.getDiaChi());
        this.nguoiDungService.updateUser(currentUser);
        return new ResponseObject();
    }

    @PostMapping("/updatePassword")
    @ResponseBody
    public ResponseObject passwordChange(HttpServletRequest res, @RequestBody PasswordDTO dto) {
        NguoiDung currentUser = this.getSessionUser(res);
        if (!this.passwordEncoder.matches(dto.getOldPassword(), currentUser.getPassword())) {
            ResponseObject re = new ResponseObject();
            re.setStatus("old");
            return re;
        }
        this.nguoiDungService.changePass(currentUser, dto.getNewPassword());
        return new ResponseObject();
    }

}
