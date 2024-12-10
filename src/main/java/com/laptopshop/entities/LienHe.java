package com.laptopshop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "lien_he")
public class LienHe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String emailLienHe;

    private String noiDungLienHe;

    private String noiDungTraLoi;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "GMT+7")
    private Date ngayLienHe;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "GMT+7")
    private Date ngayTraLoi;

    private String trangThai;

    @ManyToOne
    @JoinColumn(name = "ma_nguoi_tra_loi")
    private NguoiDung nguoiTraLoi;

}
