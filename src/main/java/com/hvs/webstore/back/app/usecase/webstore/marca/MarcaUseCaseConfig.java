package com.hvs.webstore.back.app.usecase.webstore.marca;

import com.hvs.webstore.back.domain.entity.webstore.marca.MarcaDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MarcaUseCaseConfig {

    @Bean
    public CreateMarcaUseCase createMarcaUseCaseBean(MarcaDomainGateway gateway) {

        return new CreateMarcaUseCaseImpl(gateway);
    }
    @Bean
    public ReadMarcaUseCase readMarcaUseCaseBean(MarcaDomainGateway gateway) {

        return new ReadMarcaUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllMarcaUseCase readAllMarcaUseCaseBean(MarcaDomainGateway gateway) {

        return new ReadAllMarcaUseCaseImpl(gateway);
    }
    @Bean
    public UpdateMarcaUseCase updateMarcaUseCaseBean(MarcaDomainGateway gateway) {

        return new UpdateMarcaUseCaseImpl(gateway);
    }
    @Bean
    public PatchMarcaUseCase patchMarcaUseCaseBean(MarcaDomainGateway gateway) {

        return new PatchMarcaUseCaseImpl(gateway);
    }
    @Bean
    public DeleteMarcaUseCase deleteMarcaUseCaseBean(MarcaDomainGateway gateway) {

        return new DeleteMarcaUseCaseImpl(gateway);
    }
}
