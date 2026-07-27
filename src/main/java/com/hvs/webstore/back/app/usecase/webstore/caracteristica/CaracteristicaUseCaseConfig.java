package com.hvs.webstore.back.app.usecase.webstore.caracteristica;

import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaracteristicaUseCaseConfig {

    @Bean
    public CreateCaracteristicaUseCase createCaracteristicaUseCaseBean(CaracteristicaDomainGateway gateway) {
        return new CreateCaracteristicaDefaultUseCase(gateway);
    }

    @Bean
    public ReadCaracteristicaUseCase readCaracteristicaUseCaseBean(CaracteristicaDomainGateway gateway) {
        return new ReadCaracteristicaDefaultUseCase(gateway);
    }

    @Bean
    public UpdateCaracteristicaUseCase updateCaracteristicaUseCaseBean(CaracteristicaDomainGateway gateway) {
        return new UpdateCaracteristicaDefaultUseCase(gateway);
    }

    @Bean
    public PatchCaracteristicaUseCase patchCaracteristicaUseCaseBean(CaracteristicaDomainGateway gateway) {
        return new PatchCaracteristicaDefaultUseCase(gateway);
    }

    @Bean
    public DeleteCaracteristicaUseCase deleteCaracteristicaUseCaseBean(CaracteristicaDomainGateway gateway) {
        return new DeleteCaracteristicaDefaultUseCase(gateway);
    }
}
