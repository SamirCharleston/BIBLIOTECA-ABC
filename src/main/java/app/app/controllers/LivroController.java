package app.app.controllers;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericController;
import app.app.coreClasses.genericCrudSuperClasses.CrudGenericEndPointName;
import app.app.dto.entrada.LivroDTOIn;
import app.app.dto.saida.LivroDTOOut;
import app.app.entities.LivroEntity;
import app.app.repositories.LivroRepository;
import app.app.services.LivroService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CrudGenericEndPointName.BOOK)
public class LivroController extends CrudGenericController<
        LivroEntity,
        LivroService,
        LivroRepository,
        LivroDTOIn,
        LivroDTOOut
> {
}
