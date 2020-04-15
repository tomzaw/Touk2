package ticketbooking.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ticketbooking.model.Client;
import ticketbooking.service.ClientService;
import ticketbooking.service.RoomService;
import ticketbooking.web.CreateClientRequest;
import ticketbooking.web.ClientRequest;
import ticketbooking.web.ClientResponse;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    private ClientService clientService;

    private RoomService roomService;

    public ClientController(ClientService clientService, RoomService roomService) {
        this.clientService = clientService;
        this.roomService = roomService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@Valid @RequestBody CreateClientRequest ccr) {

        Client client = new Client(ccr.getName(), ccr.getSurname(), ccr.getEmail());
        clientService.save(client);
        LOGGER.info("Client created");
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientResponse read(@PathVariable Long id) {

        Client client = clientService.find(id);
        return new ClientResponse(client.getId(), client.getName(), client.getSurname(), client.getEmail());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientResponse> readAll() {

        List<Client> clients = clientService.findAll();
        List<ClientResponse> clientsResponses = new ArrayList<>();
        for (Client client : clients) {
            clientsResponses.add(new ClientResponse(client.getId(), client.getName(), client.getSurname(), client.getEmail()));
        }
        return clientsResponses;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody ClientRequest cr) {

        Client client = new Client(cr.getName(), cr.getSurname(), cr.getEmail());
        client.setId(cr.getId());
        clientService.save(client);
        LOGGER.info("Client id=" + cr.getId() + "updated");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void delete(Long id) {

        clientService.delete(id);
        LOGGER.info("Client id=" + id + "deleted");
    }
}
