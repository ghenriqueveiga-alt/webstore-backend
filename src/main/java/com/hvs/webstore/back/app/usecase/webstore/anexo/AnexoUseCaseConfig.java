package com.hvs.webstore.back.app.usecase.webstore.anexo;

import com.hvs.webstore.back.domain.entity.webstore.anexo.AnexoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnexoUseCaseConfig {

    @Bean
    public CreateAnexoUseCase createAnexoUseCaseBean(AnexoDomainGateway gateway) {

        return new CreateAnexoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAnexoUseCase readAnexoUseCaseBean(AnexoDomainGateway gateway) {

        return new ReadAnexoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateAnexoUseCase updateAnexoUseCaseBean(AnexoDomainGateway gateway) {

        return new UpdateAnexoUseCaseImpl(gateway);
    }
    @Bean
    public PatchAnexoUseCase patchAnexoUseCaseBean(AnexoDomainGateway gateway) {

        return new PatchAnexoUseCaseImpl(gateway);
    }
    @Bean
    public DeleteAnexoUseCase deleteAnexoUseCaseBean(AnexoDomainGateway gateway) {

        return new DeleteAnexoUseCaseImpl(gateway);
    }
}
