package br.com.treinaweb.twprojetos.api.docs;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.treinaweb.twprojetos.api.annotations.ApiPageable;
import br.com.treinaweb.twprojetos.api.dto.CargoDTO;
import br.com.treinaweb.twprojetos.api.exceptions.ApiError;
import br.com.treinaweb.twprojetos.api.exceptions.ApiErrorValidation;
import br.com.treinaweb.twprojetos.entidades.Cargo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "Cargos", description = "Cargo Controller")
public interface CargoControllerAPIDoc {
    
    @ApiOperation(value = "Listar todos os cargos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Listagem dos cargos realizada com sucesso")
    })
    @ApiPageable
    CollectionModel<EntityModel<Cargo>> buscarTodos(@ApiIgnore Pageable paginacao);

    @ApiOperation(value = "Buscar cargo por ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargo encontrado com sucesso"),
        @ApiResponse(code = 404, message = "Cargo não encontrado", response = ApiError.class)
    })
    EntityModel<Cargo> buscarPorId(Long id);

    @ApiOperation(value = "Cadastrar cargo")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Cargo cadastrado com sucesso"),
        @ApiResponse(code = 400, message = "Houveram erros de validação", response = ApiErrorValidation.class)
    })
    EntityModel<Cargo> cadastrar(CargoDTO cargoDTO);

    @ApiOperation(value = "Atualizar cargo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargo atualizado com sucesso"),
        @ApiResponse(code = 400, message = "Houveram erros de validação", response = ApiErrorValidation.class),
        @ApiResponse(code = 404, message = "Cargo não encontrado", response = ApiError.class)
    })
    EntityModel<Cargo> atualizar(CargoDTO cargoDTO, Long id);

    @ApiOperation(value = "Excluir cargo por ID")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Cargo excluído com sucesso"),
        @ApiResponse(code = 404, message = "Cargo não encontrado", response = ApiError.class)
    })
    ResponseEntity<?> excluirPorId(Long id);

}
