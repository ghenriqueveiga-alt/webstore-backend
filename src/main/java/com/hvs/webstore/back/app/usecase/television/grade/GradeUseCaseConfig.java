package com.hvs.webstore.back.app.usecase.television.grade;

import com.hvs.webstore.back.domain.entity.television.grade.GradeDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GradeUseCaseConfig {

    @Bean
    public CreateGradeUseCase createGradeUseCaseBean(GradeDomainGateway gateway) {

        return new CreateGradeUseCaseImpl(gateway);
    }
    @Bean
    public ReadGradeUseCase readGradeUseCaseBean(GradeDomainGateway gateway) {

        return new ReadGradeUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllGradeUseCase readAllGradeUseCaseBean(GradeDomainGateway gateway) {

        return new ReadAllGradeUseCaseImpl(gateway);
    }
    @Bean
    public UpdateGradeUseCase updateGradeUseCaseBean(GradeDomainGateway gateway) {

        return new UpdateGradeUseCaseImpl(gateway);
    }
    @Bean
    public PatchGradeUseCase patchGradeUseCaseBean(GradeDomainGateway gateway) {

        return new PatchGradeUseCaseImpl(gateway);
    }
    @Bean
    public DeleteGradeUseCase deleteGradeUseCaseBean(GradeDomainGateway gateway) {

        return new DeleteGradeUseCaseImpl(gateway);
    }
}
