package com.hvs.webstore.back.app.usecase.webstore.permissao;

import com.hvs.webstore.back.domain.entity.webstore.permissao.PermissaoDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PermissaoUseCaseConfig {

    @Bean
    public CreatePermissaoUseCase createPermissaoUseCaseBean(PermissaoDomainGateway gateway) {

        return new CreatePermissaoUseCaseImpl(gateway);
    }
    @Bean
    public ReadPermissaoUseCase readPermissaoUseCaseBean(PermissaoDomainGateway gateway) {

        return new ReadPermissaoUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllPermissaoUseCase readAllPermissaoUseCaseBean(PermissaoDomainGateway gateway) {

        return new ReadAllPermissaoUseCaseImpl(gateway);
    }
    @Bean
    public UpdatePermissaoUseCase updatePermissaoUseCaseBean(PermissaoDomainGateway gateway) {

        return new UpdatePermissaoUseCaseImpl(gateway);
    }
    @Bean
    public PatchPermissaoUseCase patchPermissaoUseCaseBean(PermissaoDomainGateway gateway) {

        return new PatchPermissaoUseCaseImpl(gateway);
    }
    @Bean
    public DeletePermissaoUseCase deletePermissaoUseCaseBean(PermissaoDomainGateway gateway) {

        return new DeletePermissaoUseCaseImpl(gateway);
    }
}
