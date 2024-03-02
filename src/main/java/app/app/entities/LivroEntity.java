package app.app.entities;

import app.app.coreClasses.genericCrudSuperClasses.CrudGenericEntity;
import jakarta.persistence.*;
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
public class LivroEntity extends CrudGenericEntity {
    @Column
    private String issn;
    @Column
    private String titulo;
    @Column
    private String sinopse;
    @Column
    private Long ano;
    @Column
    private Long numeroDePaginas;
    @ManyToMany
    private List<AutorEntity> autores;
    @ManyToOne
    private EditoraEntity editora;
}
