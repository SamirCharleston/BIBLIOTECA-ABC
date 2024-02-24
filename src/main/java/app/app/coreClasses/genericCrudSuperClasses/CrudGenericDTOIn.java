package com.sismanut.sismanut.coreClasses.genericCrudSuperClasses;

import com.sismanut.sismanut.config.messageHandling.errorMessages.ValidationErrorMessages;
import com.sismanut.sismanut.config.validations.RegexValidation;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @Positive(message = ValidationErrorMessages.POSITIVE)
    private Long quantityToList;
    private boolean descOrder;
}
