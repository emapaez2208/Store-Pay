package com.emapaez.storepay.auth.config;

import com.emapaez.storepay.auth.credentials.CredentialsEntity;
import com.emapaez.storepay.auth.credentials.CredentialsRepository;
import com.emapaez.storepay.auth.permissions.*;
import com.emapaez.storepay.common.model.Email;
import com.emapaez.storepay.features.user.UserRepository;
import com.emapaez.storepay.features.user.domain.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Configuration
public class DataBaseInitializerConfig {

    @Bean
    @Transactional
    public CommandLineRunner initDatabase(
            PermitRepository permitRepository,
            RoleRepository roleRepository,
            CredentialsRepository credentialsRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){
        return args ->{
            // 1. Check if exists permits loaded.
            if (permitRepository.count() > 0) return;

            System.out.println(">> Loading test data into the Database");

            // 2. Create and save permits
            PermitEntity owner = permitRepository.save(PermitEntity.builder().permits(PermitsEnum.OWNER).build());
            PermitEntity employee = permitRepository.save(PermitEntity.builder().permits(PermitsEnum.EMPLOYEE).build());
            PermitEntity admin = permitRepository.save(PermitEntity.builder().permits(PermitsEnum.ADMIN).build());

            // 3. Create and save roles with permits
            RoleEntity roleOwner = new RoleEntity(RolesEnum.ROLE_OWNER);
            roleOwner.addPermit(owner);
            roleRepository.save(roleOwner);

            RoleEntity roleEmployee = new RoleEntity(RolesEnum.ROLE_EMPLOYEE);
            roleEmployee.addPermit(employee);
            roleRepository.save(roleEmployee);

            RoleEntity roleAdmin = new RoleEntity(RolesEnum.ROLE_ADMIN);
            roleAdmin.addPermit(admin);
            roleRepository.save(roleAdmin);

            String flatPassword = "Password123";
            String passwordEncripted = passwordEncoder.encode(flatPassword);

            // example : create Admin
            UserEntity adminUser = new UserEntity();
            adminUser.setExternalId(UUID.randomUUID());
            adminUser.setEmail(new Email("admin@example.com"));
            adminUser.setName("admin");
            adminUser.setLastName("Admin");
            adminUser.setDni("99999999");
            adminUser.setPhoneNumber(1234567897L);
            userRepository.save(adminUser);

            CredentialsEntity adminCreds = new CredentialsEntity();
            adminCreds.setUsername("admin@example.com");
            adminCreds.setExternalId(adminUser.getExternalId());
            adminCreds.setPassword(passwordEncripted);
            adminCreds.setEnabled(true);
            adminCreds.setAccountNonLocked(true);
            adminCreds.getRoles().add(roleAdmin);
            adminCreds.setUser(adminUser);
            credentialsRepository.save(adminCreds);

            System.out.println(">> successfully uploaded test data using password encoder");
        };
    }
}
