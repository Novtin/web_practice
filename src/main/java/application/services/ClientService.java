package application.services;


import application.entity.Client;
import application.entity.Role;
import application.repositories.ClientRepository;
import application.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Client findClientByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }
    public Client findClientByClientId(Integer id) {
        return clientRepository.findClientByClientId(id);
    }


    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public boolean saveGamer(Client client) {
        Client clientFromDB = clientRepository.findClientByEmail(client.getEmail());
        if (clientFromDB != null) {
            return false;
        }
        Set<Role> roleSet = new HashSet<>();
        Role clientRole = roleRepository.getRoleById(1L);
        roleSet.add(clientRole);
        client.setRoles(roleSet);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save(client);
        return true;
    }


    public boolean loginClient(String email, String password) {
        Client client = clientRepository.findClientByEmail(email);
        return client != null && passwordEncoder.matches(password, client.getPassword());
    }

    public List<Client> findAllExceptEmail(String email){
        return clientRepository.findAllExceptEmail(email);
    }

}

