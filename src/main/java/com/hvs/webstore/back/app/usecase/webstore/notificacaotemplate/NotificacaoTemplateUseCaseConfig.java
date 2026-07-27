package com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoTemplateUseCaseConfig {

    @Bean
    public CreateNotificacaoTemplateUseCase createNotificacaoTemplateUseCaseBean(NotificacaoTemplateDomainGateway gateway) {

        return new CreateNotificacaoTemplateUseCaseImpl(gateway);
    }
    @Bean
    public ReadNotificacaoTemplateUseCase readNotificacaoTemplateUseCaseBean(NotificacaoTemplateDomainGateway gateway) {

        return new ReadNotificacaoTemplateUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllNotificacaoTemplateUseCase readAllNotificacaoTemplateUseCaseBean(NotificacaoTemplateDomainGateway gateway) {

        return new ReadAllNotificacaoTemplateUseCaseImpl(gateway);
    }
    @Bean
    public ReadByTipoNotificacaoTemplateUseCase readByTipoNotificacaoTemplateUseCaseBean(NotificacaoTemplateDomainGateway gateway) {

        return new ReadByTipoNotificacaoTemplateUseCaseImpl(gateway);
    }
    @Bean
    public UpdateNotificacaoTemplateUseCase updateNotificacaoTemplateUseCaseBean(NotificacaoTemplateDomainGateway gateway) {

        return new UpdateNotificacaoTemplateUseCaseImpl(gateway);
    }
    @Bean
    public PatchNotificacaoTemplateUseCase patchNotificacaoTemplateUseCaseBean(NotificacaoTemplateDomainGateway gateway) {

        return new PatchNotificacaoTemplateUseCaseImpl(gateway);
    }
    @Bean
    public DeleteNotificacaoTemplateUseCase deleteNotificacaoTemplateUseCaseBean(NotificacaoTemplateDomainGateway gateway) {

        return new DeleteNotificacaoTemplateUseCaseImpl(gateway);
    }
}
