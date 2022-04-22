package com.project.rpgstoreback;

import com.project.rpgstoreback.models.*;
import com.project.rpgstoreback.repository.AccountRepository;
import com.project.rpgstoreback.repository.ProductRepository;
import com.project.rpgstoreback.repository.RoleRepository;
import com.project.rpgstoreback.repository.TagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.metrics.StartupStep;

import javax.transaction.Transactional;
import java.time.LocalDate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class RpgstorebackApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpgstorebackApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			AccountRepository accountRepository,
			TagRepository tagRepository,
			ProductRepository productRepository,
			RoleRepository roleRepository) {
		return new CommandLineRunner() {
			@Override
			@Transactional
			public void run(String... args) throws Exception {
				Role role1 = new Role(RoleEnum.USER);
				Role role2 = new Role(RoleEnum.ADMIN);
				Role role3 = new Role(RoleEnum.SELLER);

				List<Role> roleList = Arrays.asList(role1, role2, role3);
				roleRepository.saveAll(roleList);

				Account admin = new Account(
						"firstAdmin",
						"lastAdmin",
						"admin",
						"admin",
						"admin@gmail.com",
						LocalDate.now(),
						true,
						Arrays.asList(role2)
				);

				Account seller = new Account(
						"firstSeller",
						"lastSeller",
						"seller",
						"seller",
						"seller@gmail.com",
						LocalDate.now(),
						true,
						Arrays.asList(role3)
				);
				Account user = new Account(
						"firstUser",
						"lastUser",
						"user",
						"user",
						"user@gmail.com",
						LocalDate.now(),
						true,
						Arrays.asList(role1)
				);
				accountRepository.save(admin);
				accountRepository.save(seller);
				accountRepository.save(user); // pass en manage => si modifié ajouté dans le save (transactionnal)

				Tag swordTag = new Tag("sword","Longue arme blanche");
				Tag shieldTag = new Tag("shield","Une défense en plus");
				Tag gunTag = new Tag("gun","Arme distance moderne");
				Tag potionTag = new Tag("potion","Divers utilité");
				Tag instrumentTag = new Tag("instrument","De la musique en combat ? Quelle idée ...");
				Tag legendaryTag = new Tag("légendaire","Un object d'une rareté inouï");

				//tagRepository.save(swordTag);
				//tagRepository.save(shieldTag);
				tagRepository.save(gunTag);
				tagRepository.save(potionTag);
				tagRepository.save(instrumentTag);
				//tagRepository.save(legendaryTag);

				Weapon sword = new Weapon(
						"Muramasa",
						"j'aime les sabres",
						1,
						5000,
						seller,
						Arrays.asList("https://soranews24.com/wp-content/uploads/sites/3/2021/10/TR-12.jpeg?w=640"),
						Arrays.asList(swordTag,legendaryTag),
						120
				);
				Armor shield = new Armor(
						"Bouclier de Midas",
						"bouclier d'un avare",
						1,
						300,
						seller,
						Arrays.asList("https://c8.alamy.com/compfr/ay8yy6/historique-irlandais-bouclier-d-or-de-l-age-du-bronze-ay8yy6.jpg"),
						Arrays.asList(shieldTag),
						50
				);

				Usable ring = new Usable(
						"Anneau Unique",
						"Tu deviens juste invisible mdr",
						1,
						9999,
						admin,
						Arrays.asList("https://upload.wikimedia.org/wikipedia/commons/d/d4/One_Ring_Blender_Render.png"),
						Arrays.asList(legendaryTag),
						0
				);

				productRepository.save(sword);
				productRepository.save(shield);
				productRepository.save(ring);

				user.addProduct(sword); // update via premier save (dans état manage)
				user.addProduct(shield); // update via premier save (dans état manage)

				tagRepository.delete(legendaryTag.getId());
			}
		};
	}
}