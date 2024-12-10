package com.laptopshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LienHeDTO {

    private long id;

    @NotEmpty(message = "Nội dung trả lời không được trống")
    private String noiDungTraLoi;

    private String tieuDe;

    private String diaChiDen;

    private Date ngayTraLoi;

    @Override
    public String toString() {
        return "LienHeDTO [id=" + this.id + ", noiDungTraLoi=" + this.noiDungTraLoi + ", tieuDe=" + this.tieuDe + ", diaChiDen="
                + this.diaChiDen + ", ngayTraLoi=" + this.ngayTraLoi + "]";
    }
}
