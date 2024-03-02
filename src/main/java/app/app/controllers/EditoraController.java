package app.app.controllers;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericController;
import app.app.coreClasses.genericCrudSuperClasses.CrudGenericEndPointName;
import app.app.dto.entrada.EditoraDTOIn;
import app.app.dto.saida.EditoraDTOOut;
import app.app.entities.EditoraEntity;
import app.app.repositories.EditoraRepository;
import app.app.services.EditoraService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CrudGenericEndPointName.PUBLISHER)
public class EditoraController extends CrudGenericController<
        EditoraEntity,
        EditoraService,
        EditoraRepository,
        EditoraDTOIn,
        EditoraDTOOut
        > {
}
