package br.com.treinaweb.twprojetos.api.mapper;

import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.dto.CargoDTO;
import br.com.treinaweb.twprojetos.entidades.Cargo;

@Component
public class CargoMapper {
    
    public Cargo converterParaEntidade(CargoDTO cargoDTO) {
        Cargo cargo = new Cargo();

        cargo.setNome(cargoDTO.getNome());

        return cargo;
    }
}
