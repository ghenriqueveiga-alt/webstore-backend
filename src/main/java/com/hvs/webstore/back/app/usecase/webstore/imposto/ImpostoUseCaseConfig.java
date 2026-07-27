package com.hvs.webstore.back.app.usecase.webstore.imposto;

import com.hvs.webstore.back.domain.entity.webstore.imposto.ImpostoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImpostoUseCaseConfig {

    @Bean
    public CreateImpostoUseCase createImpostoUseCaseBean(ImpostoDomainGateway gateway) {

        return new CreateImpostoUseCaseImpl(gateway);
    }
    @Bean
    public ReadImpostoUseCase readImpostoUseCaseBean(ImpostoDomainGateway gateway) {

        return new ReadImpostoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllImpostoUseCase readAllImpostoUseCaseBean(ImpostoDomainGateway gateway) {

        return new ReadAllImpostoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateImpostoUseCase updateImpostoUseCaseBean(ImpostoDomainGateway gateway) {

        return new UpdateImpostoUseCaseImpl(gateway);
    }
    @Bean
    public PatchImpostoUseCase patchImpostoUseCaseBean(ImpostoDomainGateway gateway) {

        return new PatchImpostoUseCaseImpl(gateway);
    }
    @Bean
    public DeleteImpostoUseCase deleteImpostoUseCaseBean(ImpostoDomainGateway gateway) {

        return new DeleteImpostoUseCaseImpl(gateway);
    }
}
