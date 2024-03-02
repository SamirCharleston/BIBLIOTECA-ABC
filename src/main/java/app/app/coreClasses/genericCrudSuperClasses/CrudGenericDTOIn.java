package app.app.coreClasses.genericCrudSuperClasses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrudGenericDTOIn{
    private UUID id;
    private Long quantityToList;
    private boolean descOrder;
}
