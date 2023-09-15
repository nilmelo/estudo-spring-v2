package br.com.treinaweb.twprojetos.api.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import br.com.treinaweb.twprojetos.api.controller.ClienteControllerAPI;
import br.com.treinaweb.twprojetos.api.controller.FuncionarioControllerAPI;
import br.com.treinaweb.twprojetos.api.controller.ProjetoControllerAPI;
import br.com.treinaweb.twprojetos.entidades.Projeto;
import br.com.treinaweb.twprojetos.excecoes.ProjetoNaoEncontradoException;

@Component
public class ProjetoAssembler implements SimpleRepresentationModelAssembler<Projeto> {

    @Override
    public void addLinks(EntityModel<Projeto> resource) {
        Long clienteId = resource.getContent().getCliente().getId();
        Long liderId = resource.getContent().getLider().getId();
        Long id = resource.getContent().getId();

        Link liderLink = linkTo(methodOn(FuncionarioControllerAPI.class).buscarPorId(liderId))
            .withRel("lider")
            .withType("GET");

        Link clienteLink = linkTo(methodOn(ClienteControllerAPI.class).buscarPorId(clienteId))
            .withRel("cliente")
            .withType("GET");

        Link selfLink = linkTo(methodOn(ProjetoControllerAPI.class).buscarPorId(id))
            .withSelfRel()
            .withType("GET");

        Link editarLink = linkTo(methodOn(ProjetoControllerAPI.class).atualizar(null, id))
            .withSelfRel()
            .withType("PUT");

        Link excluirLink = linkTo(methodOn(ProjetoControllerAPI.class).excluirPorId(id))
            .withSelfRel()
            .withType("DELETE");

        Link equipeLink = linkTo(methodOn(ProjetoControllerAPI.class).buscarEquipe(id))
            .withRel("equipe")
            .withType("GET");

        Link atualizarEquipeLink = linkTo(methodOn(ProjetoControllerAPI.class).atualizarEquipe(id, null))
            .withRel("equipe")
            .withType("PATCH");

        resource.add(liderLink, clienteLink, selfLink, editarLink, excluirLink, equipeLink, atualizarEquipeLink);
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Projeto>> resources) {
        Link selfLink = linkTo(methodOn(ProjetoControllerAPI.class).buscarTodos(null))
            .withSelfRel()
            .withType("GET");

        resources.add(selfLink);
    }
}
