package com.sismanut.sismanut.coreClasses.genericCrudSuperClasses;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
//@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class CrudGenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    boolean status;
    @PrePersist
    private void prePersist(){
        this.status = true;
    }
}
