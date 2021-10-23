package com.izo.veterinaria;


import com.izo.veterinaria.model.AppUser;
import com.izo.veterinaria.model.Roles;
import com.izo.veterinaria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("1234");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("password2");

        AppUser user = userRepository.save(new AppUser("Usuario1", "Usuario1", "usuario1@gmail.com", hashedPassword, Roles.ADMIN));
        System.out.println(user.getAppUserRole());
        userRepository.save(new AppUser("Usuario2", "Usuario2", "Usuario2@gmail.com", hashedPassword2, Roles.USER));
    }
}