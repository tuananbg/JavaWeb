package com.laptopshop.controller;

import com.laptopshop.entities.NguoiDung;
import com.laptopshop.service.NguoiDungService;
import com.laptopshop.service.SecurityService;
import com.laptopshop.validator.NguoiDungValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final NguoiDungService nguoiDungService;

    private final SecurityService securityService;

    private final NguoiDungValidator nguoiDungValidator;


    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("newUser", new NguoiDung());
        return "client/register";
    }

    @PostMapping("/register")
    public String registerProcess(@ModelAttribute("newUser") @Valid NguoiDung nguoiDung, BindingResult bindingResult, Model model) {

        this.nguoiDungValidator.validate(nguoiDung, bindingResult);

        if (bindingResult.hasErrors()) {
            return "client/register";
        }

        this.nguoiDungService.saveUserForMember(nguoiDung);

        this.securityService.autologin(nguoiDung.getEmail(), nguoiDung.getConfirmPassword());

        return "redirect:/";
    }
}
