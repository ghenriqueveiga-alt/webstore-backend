package com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia;

import com.hvs.webstore.back.domain.entity.webstore.categoriahierarquia.CategoriaHierarquiaDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoriaHierarquiaUseCaseConfig {

    @Bean
    public CreateCategoriaHierarquiaUseCase createCategoriaHierarquiaUseCaseBean(CategoriaHierarquiaDomainGateway gateway) {

        return new CreateCategoriaHierarquiaUseCaseImpl(gateway);
    }
    @Bean
    public ReadCategoriaHierarquiaUseCase readCategoriaHierarquiaUseCaseBean(CategoriaHierarquiaDomainGateway gateway) {

        return new ReadCategoriaHierarquiaUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllCategoriaHierarquiaUseCase readAllCategoriaHierarquiaUseCaseBean(CategoriaHierarquiaDomainGateway gateway) {

        return new ReadAllCategoriaHierarquiaUseCaseImpl(gateway);
    }
    @Bean
    public ReadByCategoriaIdUseCase readByCategoriaIdUseCaseBean(CategoriaHierarquiaDomainGateway gateway) {

        return new ReadByCategoriaIdUseCaseImpl(gateway);
    }
    @Bean
    public ReadByParentIdUseCase readByParentIdUseCaseBean(CategoriaHierarquiaDomainGateway gateway) {

        return new ReadByParentIdUseCaseImpl(gateway);
    }
    @Bean
    public ReadRootsUseCase readRootsUseCaseBean(CategoriaHierarquiaDomainGateway gateway) {

        return new ReadRootsUseCaseImpl(gateway);
    }
    @Bean
    public UpdateCategoriaHierarquiaUseCase updateCategoriaHierarquiaUseCaseBean(CategoriaHierarquiaDomainGateway gateway) {

        return new UpdateCategoriaHierarquiaUseCaseImpl(gateway);
    }
    @Bean
    public PatchCategoriaHierarquiaUseCase patchCategoriaHierarquiaUseCaseBean(CategoriaHierarquiaDomainGateway gateway) {

        return new PatchCategoriaHierarquiaUseCaseImpl(gateway);
    }
    @Bean
    public DeleteCategoriaHierarquiaUseCase deleteCategoriaHierarquiaUseCaseBean(CategoriaHierarquiaDomainGateway gateway) {

        return new DeleteCategoriaHierarquiaUseCaseImpl(gateway);
    }
}
