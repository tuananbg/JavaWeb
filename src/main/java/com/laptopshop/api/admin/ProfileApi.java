package com.laptopshop.api.admin;

import com.laptopshop.dto.PasswordDTO;
import com.laptopshop.entities.NguoiDung;
import com.laptopshop.entities.ResponseObject;
import com.laptopshop.service.NguoiDungService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileApi {

    private final NguoiDungService nguoiDungService;

    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/{id}")
    public NguoiDung getNguoiDungById(@PathVariable long id) {
        NguoiDung nd = this.nguoiDungService.findById(id);
        return nd;
    }

    @PostMapping("/doiMatKhau")
    public ResponseObject changePass(@RequestBody @Valid PasswordDTO dto, BindingResult result,
                                     HttpServletRequest request) {
        System.out.println(dto.toString());
        NguoiDung currentUser = this.getSessionUser(request);

        ResponseObject ro = new ResponseObject();

        if (!this.passwordEncoder.matches(dto.getOldPassword(), currentUser.getPassword())) {
            result.rejectValue("oldPassword", "error.oldPassword", "Mật khẩu cũ không đúng");
        }

        if (!dto.getNewPassword().equals(dto.getConfirmNewPassword())) {
            result.rejectValue("confirmNewPassword", "error.confirmNewPassword", "Nhắc lại mật khẩu mới không đúng");
        }

        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            List<FieldError> errorsList = result.getFieldErrors();
            for (FieldError error : errorsList) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            ro.setErrorMessages(errors);
            ro.setStatus("fail");
            errors = null;
        } else {
            this.nguoiDungService.changePass(currentUser, dto.getNewPassword());
            ro.setStatus("success");
        }

        return ro;
    }

    public NguoiDung getSessionUser(HttpServletRequest request) {
        return (NguoiDung) request.getSession().getAttribute("loggedInUser");
    }
}
