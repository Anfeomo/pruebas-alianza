package dto;

import model.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface  ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO clientEntityToClienteDTO(ClientEntity client);
    ClientEntity clienteDTOToClientEntity(ClientDTO clientDTO);
}
