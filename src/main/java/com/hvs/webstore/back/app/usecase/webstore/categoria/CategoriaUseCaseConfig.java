package com.hvs.webstore.back.app.usecase.webstore.categoria;

import com.hvs.webstore.back.domain.entity.webstore.categoria.CategoriaDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaUseCaseConfig {

    @Bean
    public CreateCategoriaUseCase createCategoriaUseCaseBean(CategoriaDomainGateway gateway) {

        return new CreateCategoriaUseCaseImpl(gateway);
    }
    @Bean
    public ReadCategoriaUseCase readCategoriaUseCaseBean(CategoriaDomainGateway gateway) {

        return new ReadCategoriaUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllCategoriaUseCase readAllCategoriaUseCaseBean(CategoriaDomainGateway gateway) {

        return new ReadAllCategoriaUseCaseImpl(gateway);
    }
    @Bean
    public UpdateCategoriaUseCase updateCategoriaUseCaseBean(CategoriaDomainGateway gateway) {

        return new UpdateCategoriaUseCaseImpl(gateway);
    }
    @Bean
    public PatchCategoriaUseCase patchCategoriaUseCaseBean(CategoriaDomainGateway gateway) {

        return new PatchCategoriaUseCaseImpl(gateway);
    }
    @Bean
    public DeleteCategoriaUseCase deleteCategoriaUseCaseBean(CategoriaDomainGateway gateway) {

        return new DeleteCategoriaUseCaseImpl(gateway);
    }
}
