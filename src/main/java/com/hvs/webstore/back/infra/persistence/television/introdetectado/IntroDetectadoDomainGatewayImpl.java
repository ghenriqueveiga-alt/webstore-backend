package com.hvs.webstore.back.infra.persistence.television.introdetectado;

import com.hvs.webstore.back.domain.entity.television.introdetectado.IntroDetectado;
import com.hvs.webstore.back.domain.entity.television.introdetectado.IntroDetectadoDomainGateway;
import com.hvs.webstore.back.domain.entity.television.introdetectado.IntroDetectadoId;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class IntroDetectadoDomainGatewayImpl implements IntroDetectadoDomainGateway {

    private final IntroDetectadoJpaRepository repository;

    public IntroDetectadoDomainGatewayImpl(final IntroDetectadoJpaRepository repository) {

        this.repository = repository;
    }

    @Override
    public IntroDetectado create(final IntroDetectado aIntroDetectado) {

        return repository.save(IntroDetectadoEntity.from(aIntroDetectado)).toDomain();
    }

    @Override
    public Optional<IntroDetectado> read(final IntroDetectadoId aId) {

        return repository.findById(aId.getValue()).map(IntroDetectadoEntity::toDomain);
    }

    @Override
    public List<IntroDetectado> readByEpisodio(final Long aEpisodioId) {

        return repository.findByEpisodioId(aEpisodioId).stream().map(IntroDetectadoEntity::toDomain).toList();
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