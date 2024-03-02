package app.app.entities;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class AutorEntity extends CrudGenericEntity {
    @Column
    private String nome;
    @Column
    private String cpf;
    @Column
    private Integer idade;
    @ManyToMany(mappedBy = "autores")
    private List<LivroEntity> livros;
}
