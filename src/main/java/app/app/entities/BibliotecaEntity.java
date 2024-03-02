package app.app.entities;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class BibliotecaEntity extends CrudGenericEntity {
    @Column
    private String nome;
    @Column
    private String telefone;
    @OneToMany
    private List<LivroEntity> livros;
}