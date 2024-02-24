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
public class AutorEntity extends CrudGenericEntity {
    private String nome;
    private String cpf;
    private Integer idade;
}
