package com.hvs.webstore.back.domain.entity.television.introdetectado;

import java.util.List;
import java.util.Optional;

public interface IntroDetectadoDomainGateway {

    IntroDetectado create(IntroDetectado aIntroDetectado);

    Optional<IntroDetectado> read(IntroDetectadoId aId);

    List<IntroDetectado> readByEpisodio(Long aEpisodioId);

    List<Long> readEpisodiosNaoDetectados();

    List<Long> readEpisodiosNaoDetectadosPorTipo(String tipo);

    List<Long> readProgramasComEpisodiosNaoDetectados();

    List<Long> readProgramasComEpisodiosNaoDetectadosPorTipo(String tipo);

    void deleteByEpisodio(Long aEpisodioId);

    Long countByPrograma(Long aProgramaId);
}