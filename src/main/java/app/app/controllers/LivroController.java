package app.app.controllers;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericController;
import app.app.entities.LivroEntity;
import app.app.services.LivroService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/livro")
public class LivroController extends CrudGenericController<LivroEntity, LivroService> {
}
