package controller;

import model.ClientEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.ClientService;

import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clienteService;

    public ClientController(ClientService clienteService) {
        this.clienteService = clienteService;
    }
    @GetMapping
    public Iterable<ClientEntity> getClients() {
        return clienteService.getAll();
    }
    @PostMapping
    public ClientEntity crearCliente(@RequestBody ClientEntity cliente) {
        return clienteService.create(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClientByid(@PathVariable Integer id) {
        Optional<ClientEntity> cliente = clienteService.getById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientEntity> actualizarCliente(@PathVariable Integer id, @RequestBody ClientEntity clientUpdate) {
        ClientEntity actualizado = clienteService.update(id, clientUpdate);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
