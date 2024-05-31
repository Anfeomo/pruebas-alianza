package service;

import model.ClientEntity;
import java.util.Optional;

public interface ClientService {
    Iterable<ClientEntity> getAll();
    Optional<ClientEntity> getById(Integer id);
    ClientEntity create(ClientEntity client);
    ClientEntity update(Integer id, ClientEntity client);
    void delete(Integer id);
}
