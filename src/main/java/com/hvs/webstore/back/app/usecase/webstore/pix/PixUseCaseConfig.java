package com.hvs.webstore.back.app.usecase.webstore.pix;

import com.hvs.webstore.back.domain.entity.webstore.pix.PixDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PixUseCaseConfig {

    @Bean
    public CreatePixUseCase createPixUseCaseBean(PixDomainGateway gateway) {

        return new CreatePixUseCaseImpl(gateway);
    }
    @Bean
    public ReadPixUseCase readPixUseCaseBean(PixDomainGateway gateway) {

        return new ReadPixUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllPixUseCase readAllPixUseCaseBean(PixDomainGateway gateway) {

        return new ReadAllPixUseCaseImpl(gateway);
    }
    @Bean
    public UpdatePixUseCase updatePixUseCaseBean(PixDomainGateway gateway) {

        return new UpdatePixUseCaseImpl(gateway);
    }
    @Bean
    public PatchPixUseCase patchPixUseCaseBean(PixDomainGateway gateway) {

        return new PatchPixUseCaseImpl(gateway);
    }
    @Bean
    public DeletePixUseCase deletePixUseCaseBean(PixDomainGateway gateway) {

        return new DeletePixUseCaseImpl(gateway);
    }
}
