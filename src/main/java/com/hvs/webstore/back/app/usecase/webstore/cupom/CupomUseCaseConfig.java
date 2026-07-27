package com.hvs.webstore.back.app.usecase.webstore.cupom;

import com.hvs.webstore.back.domain.entity.webstore.cupom.CupomDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CupomUseCaseConfig {

    @Bean
    public CreateCupomUseCase createCupomUseCaseBean(CupomDomainGateway gateway) {

        return new CreateCupomUseCaseImpl(gateway);
    }
    @Bean
    public ReadCupomUseCase readCupomUseCaseBean(CupomDomainGateway gateway) {

        return new ReadCupomUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllCupomUseCase readAllCupomUseCaseBean(CupomDomainGateway gateway) {

        return new ReadAllCupomUseCaseImpl(gateway);
    }
    @Bean
    public UpdateCupomUseCase updateCupomUseCaseBean(CupomDomainGateway gateway) {

        return new UpdateCupomUseCaseImpl(gateway);
    }
    @Bean
    public PatchCupomUseCase patchCupomUseCaseBean(CupomDomainGateway gateway) {

        return new PatchCupomUseCaseImpl(gateway);
    }
    @Bean
    public DeleteCupomUseCase deleteCupomUseCaseBean(CupomDomainGateway gateway) {

        return new DeleteCupomUseCaseImpl(gateway);
    }
    @Bean
    public ValidarCupomUseCase validarCupomUseCaseBean(CupomDomainGateway gateway) {

        return new ValidarCupomUseCaseImpl(gateway);
    }
}
