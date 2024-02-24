package app.app.entities;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroEntity extends CrudGenericEntity {
    private String issn;
    private String titulo;
    private String sinopse;
    private Integer ano;
    private Integer numeroDePaginas;
}
