package ticketbooking.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ticketbooking.model.Client;
import ticketbooking.repository.ClientRepository;

@Transactional
@Service
public class ClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {

        List<Client> clients = clientRepository.findAll();
        return clients;
    }

    public Client find(Long id) {

        Client client = clientRepository.findById(id).get();
        return client;
    }

    public Client find(String username) {

        Client client = clientRepository.findByName(username).get();
        return client;
    }

    public void save(Client client) {

        clientRepository.save(client);
    }

    public void delete(Long id) {

        clientRepository.deleteById(id);
    }
}
