package com.project.rpgstoreback;

import com.project.rpgstoreback.models.Account;
import com.project.rpgstoreback.models.Role;
import com.project.rpgstoreback.models.RoleEnum;
import com.project.rpgstoreback.repository.AccountRepository;
import com.project.rpgstoreback.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RpgstorebackApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpgstorebackApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AccountRepository accountRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Role role1 = new Role(RoleEnum.USER);
				Role role2 = new Role(RoleEnum.ADMIN);
				Role role3 = new Role(RoleEnum.SELLER);

				List<Role> roleList = Arrays.asList(role1, role2, role3);
				roleRepository.saveAll(roleList);

				//super(firstName, lastName, username, password, email, registrationDate, isActive, roleList);
				Account admin = new Account(
						"firstAdmin",
						"lastAdmin",
						"admin",
						passwordEncoder.encode("admin") ,
						"admin@gmail.com",
						LocalDate.now(),
						true,
						Arrays.asList(role2)
				);

				accountRepository.save(admin);
			}
		};
	}
}