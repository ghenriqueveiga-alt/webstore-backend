package com.hvs.webstore.back.app.usecase.webstore.role;

import com.hvs.webstore.back.domain.entity.webstore.role.RoleDomainGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleUseCaseConfig {

    @Bean
    public CreateRoleUseCase createRoleUseCaseBean(RoleDomainGateway gateway) {

        return new CreateRoleUseCaseImpl(gateway);
    }
    @Bean
    public ReadRoleUseCase readRoleUseCaseBean(RoleDomainGateway gateway) {

        return new ReadRoleUseCaseImpl(gateway);
    }
    @Bean
    public ReadAllRoleUseCase readAllRoleUseCaseBean(RoleDomainGateway gateway) {

        return new ReadAllRoleUseCaseImpl(gateway);
    }
    @Bean
    public UpdateRoleUseCase updateRoleUseCaseBean(RoleDomainGateway gateway) {

        return new UpdateRoleUseCaseImpl(gateway);
    }
    @Bean
    public PatchRoleUseCase patchRoleUseCaseBean(RoleDomainGateway gateway) {

        return new PatchRoleUseCaseImpl(gateway);
    }
    @Bean
    public DeleteRoleUseCase deleteRoleUseCaseBean(RoleDomainGateway gateway) {

        return new DeleteRoleUseCaseImpl(gateway);
    }
}
