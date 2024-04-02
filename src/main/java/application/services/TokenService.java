package application.services;

import application.entity.Token;
import application.repositories.ClientRepository;
import application.repositories.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final ClientRepository clientRepository;

    public TokenService(TokenRepository tokenRepository, ClientRepository clientRepository) {
        this.tokenRepository = tokenRepository;
        this.clientRepository = clientRepository;
    }

    public void saveToken(String clientName, String token) {
        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setClientId(clientRepository.findClientByEmail(clientName).getClientId());
        tokenRepository.save(tokenEntity);
    }

    public void deleteToken(String token) {
        tokenRepository.deleteByToken(token);
    }

    public List<Token> findAll(){
        return tokenRepository.findAll();
    }
}
