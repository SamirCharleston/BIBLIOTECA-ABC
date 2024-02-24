package app.app.controllers;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericController;
import app.app.coreClasses.genericCrudSuperClasses.CrudGenericEndPointName;
import app.app.entities.AutorEntity;
import app.app.services.AutorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CrudGenericEndPointName.AUTHOR)
public class AutorController extends CrudGenericController<AutorEntity, AutorService> {
}