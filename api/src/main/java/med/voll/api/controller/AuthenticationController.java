package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.user.AuthenticationDataDTO;
import med.voll.api.domain.user.User;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDataDTO authenticationDataDTO) {
        var token = new UsernamePasswordAuthenticationToken(authenticationDataDTO.login(), authenticationDataDTO.password());
        var authentication = manager.authenticate(token);

        return ResponseEntity.ok(tokenService.generateToken((User) authentication.getPrincipal()));
    }
}
