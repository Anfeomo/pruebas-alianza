package service;

import model.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ClientRepository;

import java.util.Optional;
@Service
public class ClientServiceImpl implements  ClientService{
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Iterable<ClientEntity> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<ClientEntity> getById(Integer id) {
        return clientRepository.findById(id);
    }

    @Override
    public ClientEntity create(ClientEntity client) {
        return clientRepository.save(client);
    }

    @Override
    public ClientEntity update(Integer id, ClientEntity clientUpdate) {
        Optional<ClientEntity> client = clientRepository.findById(id);
        if (client.isPresent()) {
            clientUpdate.setId(id);
            return clientRepository.save(clientUpdate);
        } else {
            return null;
        }
    }
    @Override
    public void delete(Integer id) {
    clientRepository.deleteById(id);
    }
}
