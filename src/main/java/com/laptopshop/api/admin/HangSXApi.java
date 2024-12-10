package com.laptopshop.api.admin;

import com.laptopshop.entities.HangSanXuat;
import com.laptopshop.entities.ResponseObject;
import com.laptopshop.service.HangSanXuatService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nhan-hieu")
@RequiredArgsConstructor
public class HangSXApi {

    private final HangSanXuatService hangSanXuatService;

    @GetMapping("/all")
    public Page<HangSanXuat> getAllHangSanXuat(@RequestParam(defaultValue = "1") int page) {
        return this.hangSanXuatService.getALlHangSX(page - 1, 6);
    }

    @GetMapping("/{id}")
    public HangSanXuat getHangSanXuatById(@PathVariable long id) {
        return this.hangSanXuatService.getHSXById(id);
    }

    @PostMapping(value = "/save")
    public ResponseObject addHangSanXuat(@RequestBody @Valid HangSanXuat newHangSanXuat, BindingResult result) {

        ResponseObject ro = new ResponseObject();

        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            ro.setErrorMessages(errors);
            ro.setStatus("fail");
        } else {
            this.hangSanXuatService.save(newHangSanXuat);
            ro.setData(newHangSanXuat);
            ro.setStatus("success");
        }
        return ro;
    }

    @PutMapping(value = "/update")
    public ResponseObject updateHangSanXuat(@RequestBody @Valid HangSanXuat editHangSanXuat, BindingResult result) {

        ResponseObject ro = new ResponseObject();
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            ro.setErrorMessages(errors);
            ro.setStatus("fail");
            errors = null;
        } else {
            this.hangSanXuatService.update(editHangSanXuat);
            ro.setData(editHangSanXuat);
            ro.setStatus("success");
        }

        return ro;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteHangSanXuat(@PathVariable long id) {
        this.hangSanXuatService.deleteById(id);
        return "OK !";
    }
}
