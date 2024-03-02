package app.app.controllers;
import app.app.coreClasses.genericCrudSuperClasses.CrudGenericController;
import app.app.coreClasses.genericCrudSuperClasses.CrudGenericEndPointName;
import app.app.dto.entrada.BibliotecaDTOIn;
import app.app.dto.saida.BibliotecaDTOOut;
import app.app.entities.BibliotecaEntity;
import app.app.repositories.BibliotecaRepository;
import app.app.services.BibliotecaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CrudGenericEndPointName.LIBRARY)
public class BibliotecaController extends CrudGenericController<
        BibliotecaEntity,
        BibliotecaService,
        BibliotecaRepository,
        BibliotecaDTOIn,
        BibliotecaDTOOut> {
}
