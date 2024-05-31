package service;

import dto.ClientDTO;
import dto.ClientMapper;
import model.ClientEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.ClientRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerTodos() {
        ClientEntity client = new ClientEntity();
        client.setId(123);
        client.setName("John");
        when(clientRepository.findAll()).thenReturn(Arrays.asList(client));

        Iterable<ClientEntity> clientes = clientService.getAll();
        assertNotNull(clientes);
        assertEquals(1, ((List<ClientEntity>) clientes).size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        ClientEntity cliente = new ClientEntity();
        cliente.setId(123);
        cliente.setName("John");
        when(clientRepository.findById(123)).thenReturn(Optional.of(cliente));

        Optional<ClientEntity> clienteDTO = clientService.getById(123);
        assertTrue(clienteDTO.isPresent());
        assertEquals("John", clienteDTO.get().getName());
        verify(clientRepository, times(1)).findById(123);
    }

    @Test
    void testCrear() {
        ClientEntity client = new ClientEntity();
        client.setName("John");
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(client);

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John");
        ClientDTO newClient = ClientMapper.INSTANCE.clientEntityToClienteDTO(clientService.create(client));

        assertNotNull(newClient);
        assertEquals("John", newClient.getName());
        verify(clientRepository, times(1)).save(any(ClientEntity.class));
    }

    @Test
    void testActualizar() {
        ClientEntity client = new ClientEntity();
        client.setId(123);
        client.setName("John");
        when(clientRepository.existsById(123)).thenReturn(true);
        when(clientRepository.save(any(ClientEntity.class))).thenReturn(client);

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setName("John");
        ClientDTO actualizado = ClientMapper.INSTANCE.clientEntityToClienteDTO(clientService.update(123, client));

        assertNotNull(actualizado);
        assertEquals("John", actualizado.getName());
        verify(clientRepository, times(1)).save(any(ClientEntity.class));
    }

    @Test
    void testEliminar() {
        doNothing().when(clientRepository).deleteById(123);

        clientService.delete(123);
        verify(clientRepository, times(1)).deleteById(123);
    }
}
}
