package app.app.controllers;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericController;
import app.app.entities.EditoraEntity;
import app.app.services.EditoraService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/editora")
public class EditoraController extends CrudGenericController<EditoraEntity, EditoraService> {
}
