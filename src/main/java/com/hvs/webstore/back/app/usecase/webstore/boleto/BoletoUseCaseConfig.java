package com.hvs.webstore.back.app.usecase.webstore.boleto;

import com.hvs.webstore.back.domain.entity.webstore.boleto.BoletoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoletoUseCaseConfig {

    @Bean
    public CreateBoletoUseCase createBoletoUseCaseBean(BoletoDomainGateway gateway) {

        return new CreateBoletoUseCaseImpl(gateway);
    }
    @Bean
    public ReadBoletoUseCase readBoletoUseCaseBean(BoletoDomainGateway gateway) {

        return new ReadBoletoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllBoletoUseCase readAllBoletoUseCaseBean(BoletoDomainGateway gateway) {

        return new ReadAllBoletoUseCaseImpl(gateway);
    }
    @Bean
    public UpdateBoletoUseCase updateBoletoUseCaseBean(BoletoDomainGateway gateway) {

        return new UpdateBoletoUseCaseImpl(gateway);
    }
    @Bean
    public PatchBoletoUseCase patchBoletoUseCaseBean(BoletoDomainGateway gateway) {

        return new PatchBoletoUseCaseImpl(gateway);
    }
    @Bean
    public DeleteBoletoUseCase deleteBoletoUseCaseBean(BoletoDomainGateway gateway) {

        return new DeleteBoletoUseCaseImpl(gateway);
    }
}
