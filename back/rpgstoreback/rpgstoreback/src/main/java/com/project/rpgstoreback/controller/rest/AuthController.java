package com.project.rpgstoreback.controller.rest;

import com.project.rpgstoreback.controller.rest.payload.MessageResponse;
import com.project.rpgstoreback.controller.rest.payload.SigninRequest;
import com.project.rpgstoreback.controller.rest.payload.SignupRequest;
import com.project.rpgstoreback.models.Account;
import com.project.rpgstoreback.security.jwt.JwtResponse;
import com.project.rpgstoreback.security.jwt.JwtUtils;
import com.project.rpgstoreback.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController("authRestController")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AccountService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequest dto) {

        boolean userAlreadyExist = userService.checkUsernameExist(dto.getUsername());

        if (userAlreadyExist) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("User already exist"));
        }

        Account createdUser = userService.signup(dto.getFirstName(), dto.getLastName(), dto.getUsername(), dto.getPassword(), dto.getEmail());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("User registered successfully"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@Valid @RequestBody SigninRequest dto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String tokenJwtGenerated = jwtUtils.generateJwtToken(authentication);

        Account user = (Account) authentication.getPrincipal();
        System.out.println("ici le token " + tokenJwtGenerated);
        return ResponseEntity
                .ok(new JwtResponse(
                        user.getId(),
                        user.getUsername(),
                        tokenJwtGenerated
                ));
    }
}
