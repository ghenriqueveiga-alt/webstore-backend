package com.hvs.webstore.back.app.usecase.television.genero;

import com.hvs.webstore.back.domain.entity.television.genero.GeneroDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneroUseCaseConfig {

    @Bean
    public CreateGeneroUseCase createGeneroUseCaseBean(GeneroDomainGateway gateway) {

        return new CreateGeneroUseCaseImpl(gateway);
    }
    @Bean
    public ReadGeneroUseCase readGeneroUseCaseBean(GeneroDomainGateway gateway) {

        return new ReadGeneroUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllGeneroUseCase readAllGeneroUseCaseBean(GeneroDomainGateway gateway) {

        return new ReadAllGeneroUseCaseImpl(gateway);
    }
    @Bean
    public UpdateGeneroUseCase updateGeneroUseCaseBean(GeneroDomainGateway gateway) {

        return new UpdateGeneroUseCaseImpl(gateway);
    }
    @Bean
    public PatchGeneroUseCase patchGeneroUseCaseBean(GeneroDomainGateway gateway) {

        return new PatchGeneroUseCaseImpl(gateway);
    }
    @Bean
    public DeleteGeneroUseCase deleteGeneroUseCaseBean(GeneroDomainGateway gateway) {

        return new DeleteGeneroUseCaseImpl(gateway);
    }
}
