package app.app.controllers;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericController;
import app.app.entities.BibliotecaEntity;
import app.app.services.BibliotecaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/biblioteca")
public class BibliotecaController extends CrudGenericController<BibliotecaEntity, BibliotecaService> {
}