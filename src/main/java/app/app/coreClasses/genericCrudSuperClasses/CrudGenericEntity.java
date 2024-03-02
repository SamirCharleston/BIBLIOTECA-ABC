package app.app.coreClasses.genericCrudSuperClasses;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter @Setter
//@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class CrudGenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private LocalDateTime creationDateTime;
    @Column
    private LocalDateTime updateDateTime;
    @Column
    boolean status;
    @PrePersist
    private void prePersist(){
        this.status = true;
        this.creationDateTime = LocalDateTime.now();
    }
    @PreUpdate
    private void preUpdate(){
        this.status = true;
        this.updateDateTime = LocalDateTime.now();
    }
}
