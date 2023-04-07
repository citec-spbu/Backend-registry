package org.spburegistry.backend.controller;

import org.spburegistry.backend.dto.ClientTO;
import org.spburegistry.backend.dto.UserTO;
import org.spburegistry.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/data/clients")
@RestController
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    @GetMapping
    public Iterable<UserTO> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/client")
    public UserTO getClientById(@RequestParam Long id) {
        return clientService.findById(id);
    }

    @PostMapping("/client")
    public ClientTO addNewClient(@RequestBody ClientTO clientTO) {
        return clientService.addClient(clientTO);
    }
}
