package com.laptopshop.api.admin;

import com.laptopshop.dto.LienHeDTO;
import com.laptopshop.dto.SearchLienHeObject;
import com.laptopshop.entities.LienHe;
import com.laptopshop.entities.ResponseObject;
import com.laptopshop.service.LienHeService;
import com.laptopshop.service.NguoiDungService;
import com.laptopshop.ulti.EmailUlti;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lien-he")
@RequiredArgsConstructor
public class LienHeApi {

    private final LienHeService lienHeService;

    private final NguoiDungService nguoiDungService;

    private final EmailUlti emailUlti;

    @GetMapping("/all")
    public Page<LienHe> getLienHeByFilter(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam String trangThaiLienHe, @RequestParam String tuNgay, @RequestParam String denNgay)
            throws ParseException {

        SearchLienHeObject object = new SearchLienHeObject();
        object.setDenNgay(denNgay);
        object.setTrangThaiLienHe(trangThaiLienHe);
        object.setTuNgay(tuNgay);

        Page<LienHe> listLienHe = this.lienHeService.getLienHeByFilter(object, page);
        return listLienHe;
    }

    @GetMapping("/{id}")
    public LienHe getLienHeById(@PathVariable long id) {
        return this.lienHeService.findById(id);
    }

    @PostMapping("/reply")
    public ResponseObject tradLoiLienHeProcess(@RequestBody @Valid LienHeDTO dto, BindingResult result) {

        ResponseObject ro = new ResponseObject();

        if (result.hasErrors()) {

            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            ro.setErrorMessages(errors);

//			List<String> keys = new ArrayList<String>(errors.keySet());
//			for (String key : keys) {
//				System.out.println(key + ": " + errors.get(key));
//			}

            ro.setStatus("fail");
        } else {
            System.out.println(dto.toString());

            this.emailUlti.sendEmail(dto.getDiaChiDen(), dto.getTieuDe(), dto.getNoiDungTraLoi());

            LienHe lienHe = this.lienHeService.findById(dto.getId());
            lienHe.setTrangThai("Đã trả lời");
            lienHe.setNgayTraLoi(new Date());
            lienHe.setNoiDungTraLoi(dto.getNoiDungTraLoi());

            this.lienHeService.save(lienHe);
            ro.setStatus("success");
        }
        return ro;

    }
}
