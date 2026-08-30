package com.hvs.webstore.back.infra.persistence.television.endingdetectado;

import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectado;
import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectadoDomainGateway;
import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectadoId;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class EndingDetectadoDomainGatewayImpl implements EndingDetectadoDomainGateway {

    private final EndingDetectadoJpaRepository repository;

    public EndingDetectadoDomainGatewayImpl(final EndingDetectadoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public EndingDetectado create(final EndingDetectado aEndingDetectado) {

        return repository.save(EndingDetectadoEntity.from(aEndingDetectado)).toDomain();
    }

    @Override
    public Optional<EndingDetectado> read(final EndingDetectadoId aId) {

        return repository.findById(aId.getValue()).map(EndingDetectadoEntity::toDomain);
    }

    @Override
    public List<EndingDetectado> readByEpisodio(final Long aEpisodioId) {

        return repository.findByEpisodioId(aEpisodioId).stream().map(EndingDetectadoEntity::toDomain).toList();
    }

    @Override
    public List<Long> readEpisodiosNaoDetectados() {

        return repository.findEpisodiosNaoDetectados();
    }

    @Override
    public List<Long> readEpisodiosNaoDetectadosPorTipo(final String tipo) {

        return repository.findEpisodiosNaoDetectadosPorTipo(tipo);
    }

    @Override
    public List<Long> readProgramasComEpisodiosNaoDetectados() {

        return repository.findProgramasComEpisodiosNaoDetectados();
    }

    @Override
    public List<Long> readProgramasComEpisodiosNaoDetectadosPorTipo(final String tipo) {

        return repository.findProgramasComEpisodiosNaoDetectadosPorTipo(tipo);
    }

    @Override
    public Long countByPrograma(final Long aProgramaId) {

        return repository.countByPrograma(aProgramaId);
    }

    @Override
    @Transactional
    public void deleteByEpisodio(final Long aEpisodioId) {

        repository.deleteByEpisodioId(aEpisodioId);
    }
}
