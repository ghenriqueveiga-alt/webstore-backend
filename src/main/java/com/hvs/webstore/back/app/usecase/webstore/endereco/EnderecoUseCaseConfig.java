package com.hvs.webstore.back.app.usecase.webstore.endereco;

import com.hvs.webstore.back.domain.entity.webstore.endereco.EnderecoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnderecoUseCaseConfig {

    @Bean
    public CreateEnderecoUseCase createEnderecoUseCaseBean(EnderecoDomainGateway gateway) {

        return new CreateEnderecoUseCaseImpl(gateway);
    }
    @Bean
    public ReadEnderecoUseCase readEnderecoUseCaseBean(EnderecoDomainGateway gateway) {

        return new ReadEnderecoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllEnderecoUseCase readAllEnderecoUseCaseBean(EnderecoDomainGateway gateway) {

        return new ReadAllEnderecoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateEnderecoUseCase updateEnderecoUseCaseBean(EnderecoDomainGateway gateway) {

        return new UpdateEnderecoUseCaseImpl(gateway);
    }
    @Bean
    public PatchEnderecoUseCase patchEnderecoUseCaseBean(EnderecoDomainGateway gateway) {

        return new PatchEnderecoUseCaseImpl(gateway);
    }
    @Bean
    public DeleteEnderecoUseCase deleteEnderecoUseCaseBean(EnderecoDomainGateway gateway) {

        return new DeleteEnderecoUseCaseImpl(gateway);
    }
}
