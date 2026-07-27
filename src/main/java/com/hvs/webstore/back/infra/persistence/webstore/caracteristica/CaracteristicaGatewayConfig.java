package com.hvs.webstore.back.infra.persistence.webstore.caracteristica;

import com.hvs.webstore.back.domain.entity.webstore.caracteristica.CaracteristicaDomainGateway;
import com.hvs.webstore.back.infra.persistence.webstore.caracteristica.CaracteristicaDomainGatewayImpl;
import com.hvs.webstore.back.infra.persistence.webstore.caracteristica.CaracteristicaJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaracteristicaGatewayConfig {

    @Bean
    public CaracteristicaDomainGateway caracteristicaDomainGatewayBean(CaracteristicaJpaRepository repository) {

        return new CaracteristicaDomainGatewayImpl(repository);
    }
}
