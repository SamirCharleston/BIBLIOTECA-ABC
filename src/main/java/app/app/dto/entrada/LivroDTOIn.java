package app.app.dto.entrada;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericDTOIn;

public class LivroDTOIn extends CrudGenericDTOIn {
    private String issn;
    private String titulo;
    private String sinopse;
    private Long ano;
    private Long numeroDePaginas;
}
