package com.project.rpgstoreback.controller.rest;

import com.project.rpgstoreback.controller.rest.modelDTO.*;
import com.project.rpgstoreback.models.*;
import com.project.rpgstoreback.repository.AccountRepository;
import com.project.rpgstoreback.repository.RoleRepository;
import com.project.rpgstoreback.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/api/auth/accounts")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping()
    public ResponseEntity<?> fetchAll() {
        List<ResponseAccountDTO> response = new ArrayList<>();

        this.accountRepository.findAll().forEach(
            account -> {
                response.add(fetchAccountDto(account));
            }
        );

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/admin")
    public ResponseEntity<?> fetchAllAdmin() {
        List<ResponseAccountDTO> response = new ArrayList<>();

        this.accountRepository.findAll().forEach(
                account -> {
                    if (account.getRoleList().stream().anyMatch(role -> role.getName().name() == "ADMIN")){
                        response.add(fetchAccountDto(account));
                    }
                }
        );

        return ResponseEntity
                .ok()
                .body(response);

    }

    @GetMapping("/seller")
    public ResponseEntity<?> fetchAllSeller() {
        List<ResponseAccountDTO> response = new ArrayList<>();

        this.accountRepository.findAll().forEach(
                account -> {
                    if (account.getRoleList().stream().anyMatch(role -> role.getName().name() == "SELLER")){
                        response.add(fetchAccountDto(account));
                    }
                }
        );

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/user")
    public ResponseEntity<?> fetchAllUser() {
        List<ResponseAccountDTO> response = new ArrayList<>();

        this.accountRepository.findAll().forEach(
                account -> {
                    if (account.getRoleList().stream().anyMatch(role -> role.getName().name() == "USER")){
                        response.add(fetchAccountDto(account));
                    }
                }
        );

        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchOneById(@PathVariable("id") Long idAccount) {
        Account account = this.accountRepository.findById(idAccount).orElseThrow(() -> new UsernameNotFoundException("Account not found"));

        ResponseAccountDTO dto = fetchAccountDto(account);

        return ResponseEntity
                .ok()
                .body(dto);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody AccountDTO dto ){

        Account newAccount = new Account();

        newAccount.setFirstName(dto.getFirstName());
        newAccount.setLastName(dto.getLastName());
        newAccount.setUsername(dto.getUsername());
        newAccount.setPassword(encoder.encode(dto.getPassword())); // hash maintenant ou fait avant ?
        newAccount.setEmail(dto.getEmail());
        newAccount.setRegistrationDate(dto.getRegistrationDate()); // verif bien envoyé

        Iterable<Role> roles = this.roleRepository.findAllById(dto.getRoleList());
        newAccount.setRoleList((List) roles);

        this.accountRepository.save(newAccount);

        return fetchAll();
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody AccountDTO dto) {

       Account updateAc = this.accountRepository.findById(dto.getId()).orElseThrow(() -> new UsernameNotFoundException("Account not found"));

//        updateAc.setId(dto.getId()); // set Id en plus
        updateAc.setFirstName(dto.getFirstName());
        updateAc.setLastName(dto.getLastName());
        updateAc.setUsername(dto.getUsername());
        updateAc.setPassword(encoder.encode(dto.getPassword())); // hash maintenant ou fait avant ?
        updateAc.setEmail(dto.getEmail());

        updateAc.setActive(dto.isActive()); // pour admin

        Iterable<Role> roles = this.roleRepository.findAllById(dto.getRoleList());
        updateAc.setRoleList((List) roles);

        // list product ??

        this.accountRepository.save(updateAc);

        return ResponseEntity
                .ok()
                .body("Compte de ' " + updateAc.getUsername()+ " ' à été update");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idAccount){

        Account deleteAc = this.accountRepository.findById(idAccount).orElseThrow(() -> new UsernameNotFoundException("Account not found"));

        //vide List Prod/son panier avant suppr
        deleteAc.setListProducts(new ArrayList<>());
        this.accountRepository.save(deleteAc);


        //suppr porduct create



        this.accountRepository.deleteById(idAccount);

        return ResponseEntity
                .ok()
                .body("Compte de: " + deleteAc.getUsername() + ", à été supprimé");
    }


    public ResponseAccountDTO fetchAccountDto(Account account){
        ResponseAccountDTO dto = new ResponseAccountDTO();
        dto.setId(account.getId());
        dto.setFirstName(account.getFirstName());
        dto.setLastName(account.getLastName());
        dto.setUsername(account.getUsername());
        dto.setPassword(account.getPassword());
        dto.setEmail(account.getEmail());
        dto.setRegistrationDate(account.getRegistrationDate());
        dto.setActive(account.isActive());
        dto.setRoleList(account.getRoleList());

        //LIST PRODUCT
        account.getListProducts().forEach(
                product -> {
                    //SETTER LIST PROD DANS ACCOUNT
                    if (product instanceof Weapon){
                        ResponseProductDTO prodDto = getWeaponsDto(product);
                        dto.addProduct(prodDto);
                    }
                    if (product instanceof Armor){
                        ResponseProductDTO prodDto = getArmorsDTO(product);
                        dto.addProduct(prodDto);
                    }
                    if (product instanceof Usable){
                        ResponseProductDTO prodDto = getUsablesDTO(product);
                        dto.addProduct(prodDto);
                    }
                }
        );
        return dto;
    }

    public ResponseWeaponDTO getWeaponsDto(Product product){
        ResponseWeaponDTO prodDto = new ResponseWeaponDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getPictures(),
                ((Weapon) product).getDamage()
        );

        //SET TAG PRODUCT
        List<TagDTO> taglist = new ArrayList<>();
        product.getListTags().forEach(
                t -> taglist.add(new TagDTO(t.getId(),t.getName(),t.getDescription()))
        );
        prodDto.setListTags(taglist);

        //SET CREATOR PRODUCT
        List<Long> idsRole = new ArrayList<>();
        product.getCreator().getRoleList()
                .forEach(
                        e -> idsRole.add(e.getId())
                );


        ResponseAccountProductDTO creator = new ResponseAccountProductDTO(
                product.getCreator().getId(),
                product.getCreator().getFirstName(),
                product.getCreator().getLastName(),
                product.getCreator().getUsername(),
                product.getCreator().getPassword(),
                product.getCreator().getEmail(),
                product.getCreator().getRegistrationDate(),
                product.getCreator().isActive(),
                idsRole
        );

        prodDto.setCreator(creator);
        return prodDto;
    }

    public ResponseArmorDTO getArmorsDTO(Product product){
        ResponseArmorDTO prodDto = new ResponseArmorDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getPictures(),
                ((Armor) product).getResistance()
        );

        //SET TAG PRODUCT
        List<TagDTO> taglist = new ArrayList<>();
        product.getListTags().forEach(
                t -> taglist.add(new TagDTO(t.getId(),t.getName(),t.getDescription()))
        );
        prodDto.setListTags(taglist);

        //SET CREATOR PRODUCT
        List<Long> idsRole = new ArrayList<>();
        product.getCreator().getRoleList()
                .forEach(
                        e -> idsRole.add(e.getId())
                );


        ResponseAccountProductDTO creator = new ResponseAccountProductDTO(
                product.getCreator().getId(),
                product.getCreator().getFirstName(),
                product.getCreator().getLastName(),
                product.getCreator().getUsername(),
                product.getCreator().getPassword(),
                product.getCreator().getEmail(),
                product.getCreator().getRegistrationDate(),
                product.getCreator().isActive(),
                idsRole
        );

        prodDto.setCreator(creator);
        return prodDto;
    }

    public ResponseUsableDTO getUsablesDTO(Product product){
        ResponseUsableDTO prodDto = new ResponseUsableDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice(),
                product.getPictures(),
                ((Usable) product).getDurability()
        );

        //SET TAG PRODUCT
        List<TagDTO> taglist = new ArrayList<>();
        product.getListTags().forEach(
                t -> taglist.add(new TagDTO(t.getId(),t.getName(),t.getDescription()))
        );
        prodDto.setListTags(taglist);

        //SET CREATOR PRODUCT
        List<Long> idsRole = new ArrayList<>();
        product.getCreator().getRoleList()
                .forEach(
                        e -> idsRole.add(e.getId())
                );


        ResponseAccountProductDTO creator = new ResponseAccountProductDTO(
                product.getCreator().getId(),
                product.getCreator().getFirstName(),
                product.getCreator().getLastName(),
                product.getCreator().getUsername(),
                product.getCreator().getPassword(),
                product.getCreator().getEmail(),
                product.getCreator().getRegistrationDate(),
                product.getCreator().isActive(),
                idsRole
        );

        prodDto.setCreator(creator);
        return prodDto;
    }

}
