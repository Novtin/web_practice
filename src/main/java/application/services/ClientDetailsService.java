package application.services;

import application.entity.Client;
import application.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsService implements UserDetailsService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientDetailsService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findClientByEmail(email);

        if (client == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(client.getEmail())
                .password(client.getPassword())
                .roles("USER")
                .build();
    }

}

