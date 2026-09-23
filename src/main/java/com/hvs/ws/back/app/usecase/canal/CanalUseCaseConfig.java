package com.hvs.ws.back.app.usecase.canal;

import com.hvs.ws.back.domain.entity.canal.CanalDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CanalUseCaseConfig {

    @Bean
    public CreateCanalUseCase createCanalUseCaseBean(CanalDomainGateway gateway) {

        return new CreateCanalUseCaseImpl(gateway);
    }
    @Bean
    public ReadCanalUseCase readCanalUseCaseBean(CanalDomainGateway gateway) {

        return new ReadCanalUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllCanalUseCase readAllCanalUseCaseBean(CanalDomainGateway gateway) {

        return new ReadAllCanalUseCaseImpl(gateway);
    }
    @Bean
    public UpdateCanalUseCase updateCanalUseCaseBean(CanalDomainGateway gateway) {

        return new UpdateCanalUseCaseImpl(gateway);
    }
    @Bean
    public PatchCanalUseCase patchCanalUseCaseBean(CanalDomainGateway gateway) {

        return new PatchCanalUseCaseImpl(gateway);
    }
    @Bean
    public DeleteCanalUseCase deleteCanalUseCaseBean(CanalDomainGateway gateway) {

        return new DeleteCanalUseCaseImpl(gateway);
    }
}
