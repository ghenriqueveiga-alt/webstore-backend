package com.hvs.ws.back.app.usecase.bloco;

import com.hvs.ws.back.domain.entity.bloco.BlocoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlocoUseCaseConfig {

    @Bean
    public CreateBlocoUseCase createBlocoUseCaseBean(BlocoDomainGateway gateway) {

        return new CreateBlocoUseCaseImpl(gateway);
    }
    @Bean
    public ReadBlocoUseCase readBlocoUseCaseBean(BlocoDomainGateway gateway) {

        return new ReadBlocoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllBlocoUseCase readAllBlocoUseCaseBean(BlocoDomainGateway gateway) {

        return new ReadAllBlocoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateBlocoUseCase updateBlocoUseCaseBean(BlocoDomainGateway gateway) {

        return new UpdateBlocoUseCaseImpl(gateway);
    }
    @Bean
    public PatchBlocoUseCase patchBlocoUseCaseBean(BlocoDomainGateway gateway) {

        return new PatchBlocoUseCaseImpl(gateway);
    }
    @Bean
    public DeleteBlocoUseCase deleteBlocoUseCaseBean(BlocoDomainGateway gateway) {

        return new DeleteBlocoUseCaseImpl(gateway);
    }
}
