package br.com.treinaweb.twprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.controller.CargoControllerAPI;
import br.com.treinaweb.twprojetos.entidades.Cargo;

@Component
public class CargoAssembler implements SimpleRepresentationModelAssembler<Cargo> {

    @Override
    public void addLinks(EntityModel<Cargo> resource) {
        Long id = resource.getContent().getId();

        Link selfLink = linkTo(methodOn(CargoControllerAPI.class).buscarPorId(id))
                .withSelfRel()
                .withType("GET");

            Link editarLink = linkTo(methodOn(CargoControllerAPI.class).atualizar(null, id))
                .withSelfRel()
                .withType("PUT");

            Link excluirLink = linkTo(methodOn(CargoControllerAPI.class).excluirPorId(id))
                .withSelfRel()
                .withType("DELETE");

            resource.add(selfLink, editarLink, excluirLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Cargo>> resources) {
        Link cadastroLink = linkTo(methodOn(CargoControllerAPI.class).cadastrar(null))
            .withSelfRel()
            .withType("POST");

        Link selfLink = linkTo(methodOn(CargoControllerAPI.class).buscarTodos())
            .withSelfRel()
            .withType("GET");

        resources.add(selfLink, cadastroLink);
    }
}
