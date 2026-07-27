package com.hvs.webstore.back.app.usecase.webstore.usuario;

import com.hvs.webstore.back.domain.entity.webstore.usuario.UsuarioDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioUseCaseConfig {

    @Bean
    public CreateUsuarioUseCase createUsuarioUseCaseBean(UsuarioDomainGateway gateway) {

        return new CreateUsuarioUseCaseImpl(gateway);
    }
    @Bean
    public ReadUsuarioUseCase readUsuarioUseCaseBean(UsuarioDomainGateway gateway) {

        return new ReadUsuarioUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllUsuarioUseCase readAllUsuarioUseCaseBean(UsuarioDomainGateway gateway) {

        return new ReadAllUsuarioUseCaseImpl(gateway);
    }
    @Bean
    public UpdateUsuarioUseCase updateUsuarioUseCaseBean(UsuarioDomainGateway gateway) {

        return new UpdateUsuarioUseCaseImpl(gateway);
    }
    @Bean
    public PatchUsuarioUseCase patchUsuarioUseCaseBean(UsuarioDomainGateway gateway) {

        return new PatchUsuarioUseCaseImpl(gateway);
    }
    @Bean
    public DeleteUsuarioUseCase deleteUsuarioUseCaseBean(UsuarioDomainGateway gateway) {

        return new DeleteUsuarioUseCaseImpl(gateway);
    }
}
