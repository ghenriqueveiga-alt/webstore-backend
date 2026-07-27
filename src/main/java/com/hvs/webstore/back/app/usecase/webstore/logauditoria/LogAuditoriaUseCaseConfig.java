package com.hvs.webstore.back.app.usecase.webstore.logauditoria;

import com.hvs.webstore.back.domain.entity.webstore.logauditoria.LogAuditoriaDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogAuditoriaUseCaseConfig {

    @Bean
    public CreateLogAuditoriaUseCase createLogAuditoriaUseCaseBean(LogAuditoriaDomainGateway gateway) {

        return new CreateLogAuditoriaUseCaseImpl(gateway);
    }
    @Bean
    public ReadLogAuditoriaUseCase readLogAuditoriaUseCaseBean(LogAuditoriaDomainGateway gateway) {

        return new ReadLogAuditoriaUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllLogAuditoriaUseCase readAllLogAuditoriaUseCaseBean(LogAuditoriaDomainGateway gateway) {

        return new ReadAllLogAuditoriaUseCaseImpl(gateway);
    }
    @Bean
    public ReadLogAuditoriaByEntidadeUseCase readLogAuditoriaByEntidadeUseCaseBean(LogAuditoriaDomainGateway gateway) {

        return new ReadLogAuditoriaByEntidadeUseCaseImpl(gateway);
    }
    @Bean
    public ReadLogAuditoriaByUsuarioIdUseCase readLogAuditoriaByUsuarioIdUseCaseBean(LogAuditoriaDomainGateway gateway) {

        return new ReadLogAuditoriaByUsuarioIdUseCaseImpl(gateway);
    }
}
