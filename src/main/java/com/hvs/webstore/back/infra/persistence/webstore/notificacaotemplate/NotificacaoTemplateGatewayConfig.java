package com.hvs.webstore.back.infra.persistence.webstore.notificacaotemplate;

import com.hvs.webstore.back.domain.entity.webstore.notificacaotemplate.NotificacaoTemplateDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.notificacaotemplate.NotificacaoTemplateDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.notificacaotemplate.NotificacaoTemplateJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoTemplateGatewayConfig {

    @Bean
    public NotificacaoTemplateDomainGateway notificacaoTemplateDomainGatewayBean(NotificacaoTemplateJpaRepository repository) {

        return new NotificacaoTemplateDomainGatewayImpl(repository);
    }
}
