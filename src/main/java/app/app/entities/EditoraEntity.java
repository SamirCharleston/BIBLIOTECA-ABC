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
public class EditoraEntity extends CrudGenericEntity {
    private String nome;
    private String endereco;
    private String telefone;
}
