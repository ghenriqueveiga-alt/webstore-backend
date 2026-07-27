package com.hvs.webstore.back.domain.entity.webstore.caracteristica;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;

public class CaracteristicaValidator extends Validator {

    private final Caracteristica caracteristica;

    public CaracteristicaValidator(ValidationHandler aHandler,
                                   final Caracteristica caracteristica) {

        super(aHandler);
        this.caracteristica = caracteristica;
    }

    @Override
    public void validate() {
    }

    public Caracteristica getCaracteristica() { return caracteristica; }
}
