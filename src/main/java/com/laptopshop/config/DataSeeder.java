package com.laptopshop.config;

import com.laptopshop.entities.NguoiDung;
import com.laptopshop.entities.VaiTro;
import com.laptopshop.repository.NguoiDungRepository;
import com.laptopshop.repository.VaiTroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private NguoiDungRepository userRepository;

    @Autowired
    private VaiTroRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // vai trof
        if (this.roleRepository.findByTenVaiTro("ROLE_ADMIN") == null) {
            this.roleRepository.save(new VaiTro("ROLE_ADMIN"));
        }

        if (this.roleRepository.findByTenVaiTro("ROLE_MEMBER") == null) {
            this.roleRepository.save(new VaiTro("ROLE_MEMBER"));
        }


        // Admin account
        if (this.userRepository.findByEmail("admin@gmail.com") == null) {
            NguoiDung admin = new NguoiDung();
            admin.setEmail("admin@gmail.com");
            admin.setPassword(this.passwordEncoder.encode("123456"));
            admin.setHoTen("Hoàng Tuan An");
            admin.setSoDienThoai("123456789");
            HashSet<VaiTro> roles = new HashSet<>();
            roles.add(this.roleRepository.findByTenVaiTro("ROLE_ADMIN"));
            roles.add(this.roleRepository.findByTenVaiTro("ROLE_MEMBER"));
            admin.setVaiTro(roles);
            this.userRepository.save(admin);
        }

        // Member account
        if (this.userRepository.findByEmail("member@gmail.com") == null) {
            NguoiDung member = new NguoiDung();
            member.setEmail("member@gmail.com");
            member.setPassword(this.passwordEncoder.encode("123456"));
            HashSet<VaiTro> roles = new HashSet<>();
            roles.add(this.roleRepository.findByTenVaiTro("ROLE_MEMBER"));
            member.setVaiTro(roles);
            this.userRepository.save(member);
        }

    }
}
