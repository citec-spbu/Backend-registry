package org.spburegistry.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(description = "Get all clients")
    @ApiResponse(content = { @Content(
            mediaType = "application/json",
            array = @ArraySchema(schema = @Schema(implementation = UserTO.class)))
    })
    public Iterable<UserTO> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/client")
    @Operation(description = "Get client by id")
    public UserTO getClientById(@RequestParam Long id) {
        return clientService.findById(id);
    }

    @PostMapping("/client")
    @Operation(description = "Add new client")
    public ClientTO addNewClient(@RequestBody ClientTO clientTO) {
        return clientService.addClient(clientTO);
    }
}
