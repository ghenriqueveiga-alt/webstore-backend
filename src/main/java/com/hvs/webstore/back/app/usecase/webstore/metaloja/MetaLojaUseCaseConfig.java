package com.hvs.webstore.back.app.usecase.webstore.metaloja;

import com.hvs.webstore.back.domain.entity.webstore.metaloja.MetaLojaDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetaLojaUseCaseConfig {

    @Bean
    public CreateMetaLojaUseCase createMetaLojaUseCaseBean(MetaLojaDomainGateway gateway) {

        return new CreateMetaLojaUseCaseImpl(gateway);
    }
    @Bean
    public ReadMetaLojaUseCase readMetaLojaUseCaseBean(MetaLojaDomainGateway gateway) {

        return new ReadMetaLojaUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllMetaLojaUseCase readAllMetaLojaUseCaseBean(MetaLojaDomainGateway gateway) {

        return new ReadAllMetaLojaUseCaseImpl(gateway);
    }
    @Bean
    public ReadMetaLojaByChaveUseCase readMetaLojaByChaveUseCaseBean(MetaLojaDomainGateway gateway) {

        return new ReadMetaLojaByChaveUseCaseImpl(gateway);
    }
    @Bean
    public UpdateMetaLojaUseCase updateMetaLojaUseCaseBean(MetaLojaDomainGateway gateway) {

        return new UpdateMetaLojaUseCaseImpl(gateway);
    }
    @Bean
    public PatchMetaLojaUseCase patchMetaLojaUseCaseBean(MetaLojaDomainGateway gateway) {

        return new PatchMetaLojaUseCaseImpl(gateway);
    }
    @Bean
    public DeleteMetaLojaUseCase deleteMetaLojaUseCaseBean(MetaLojaDomainGateway gateway) {

        return new DeleteMetaLojaUseCaseImpl(gateway);
    }
}
