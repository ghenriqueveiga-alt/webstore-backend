package com.hvs.webstore.back.app.usecase.television.arquivo;

import com.hvs.webstore.back.app.service.MediaPathResolver;
import com.hvs.webstore.back.domain.entity.television.arquivo.ArquivoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArquivoUseCaseConfig {

    @Bean
    public CreateArquivoUseCase createArquivoUseCaseBean(ArquivoDomainGateway gateway,
                                                         MediaPathResolver mediaPathResolver) {

        return new CreateArquivoUseCaseImpl(gateway, mediaPathResolver);
    }
    @Bean
    public ReadArquivoUseCase readArquivoUseCaseBean(ArquivoDomainGateway gateway) {

        return new ReadArquivoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllArquivoUseCase readAllArquivoUseCaseBean(ArquivoDomainGateway gateway) {

        return new ReadAllArquivoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateArquivoUseCase updateArquivoUseCaseBean(ArquivoDomainGateway gateway,
                                                         MediaPathResolver mediaPathResolver) {

        return new UpdateArquivoUseCaseImpl(gateway, mediaPathResolver);
    }
    @Bean
    public PatchArquivoUseCase patchArquivoUseCaseBean(ArquivoDomainGateway gateway,
                                                       MediaPathResolver mediaPathResolver) {

        return new PatchArquivoUseCaseImpl(gateway, mediaPathResolver);
    }
    @Bean
    public DeleteArquivoUseCase deleteArquivoUseCaseBean(ArquivoDomainGateway gateway) {

        return new DeleteArquivoUseCaseImpl(gateway);
    }
}
