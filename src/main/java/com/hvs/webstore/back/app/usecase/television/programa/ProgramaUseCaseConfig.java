package com.hvs.webstore.back.app.usecase.television.programa;

import com.hvs.webstore.back.domain.entity.television.programa.ProgramaDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgramaUseCaseConfig {

    @Bean
    public CreateProgramaUseCase createProgramaUseCaseBean(ProgramaDomainGateway gateway) {

        return new CreateProgramaUseCaseImpl(gateway);
    }
    @Bean
    public ReadProgramaUseCase readProgramaUseCaseBean(ProgramaDomainGateway gateway) {

        return new ReadProgramaUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllProgramaUseCase readAllProgramaUseCaseBean(ProgramaDomainGateway gateway) {

        return new ReadAllProgramaUseCaseImpl(gateway);
    }
    @Bean
    public UpdateProgramaUseCase updateProgramaUseCaseBean(ProgramaDomainGateway gateway) {

        return new UpdateProgramaUseCaseImpl(gateway);
    }
    @Bean
    public PatchProgramaUseCase patchProgramaUseCaseBean(ProgramaDomainGateway gateway) {

        return new PatchProgramaUseCaseImpl(gateway);
    }
    @Bean
    public DeleteProgramaUseCase deleteProgramaUseCaseBean(ProgramaDomainGateway gateway) {

        return new DeleteProgramaUseCaseImpl(gateway);
    }
}
