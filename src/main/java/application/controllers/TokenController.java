package application.controllers;

import application.entity.Token;
import application.services.FcmService;
import application.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    private final TokenService tokenService;
    private final FcmService fcmService;

    public TokenController(TokenService tokenService, FcmService fcmService) {
        this.tokenService = tokenService;
        this.fcmService = fcmService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerToken(@RequestBody String token, Principal principal) {
        try {
            token = token.replace("\"", "");
            tokenService.saveToken(principal.getName(), token);
            List<String> tokens = new ArrayList<>();
            tokens.add(token);
            fcmService.subscribeUsers("product_updates", tokens);
            return ResponseEntity.ok().build();
        } catch (Exception exception){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteToken(@RequestBody String token) {
        try {
            token = token.replace("\"", "");
            tokenService.deleteToken(token);
            List<String> tokens = new ArrayList<>();
            tokens.add(token);
            fcmService.unsubscribeUsers("product_updates", tokens);
            return ResponseEntity.ok().build();
        } catch (Exception exception){
            return ResponseEntity.notFound().build();
        }
    }
}
