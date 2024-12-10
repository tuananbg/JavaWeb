package com.laptopshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {

    @NotEmpty(message = "Phải nhập mật khẩu cũ")
    private String oldPassword;

    @NotEmpty(message = "Phải nhập mật khẩu mới")
    @Length(min = 8, max = 32, message = "Mật khẩu phải dài 8-32 ký tự")
    private String newPassword;

    @NotEmpty(message = "Phải nhắc lại mật khẩu mới")
    private String confirmNewPassword;

    @Override
    public String toString() {
        return "PasswordDTO [oldPassword=" + this.oldPassword + ", newPassword=" + this.newPassword + ", confirmNewPassword="
                + this.confirmNewPassword + "]";
    }
}
