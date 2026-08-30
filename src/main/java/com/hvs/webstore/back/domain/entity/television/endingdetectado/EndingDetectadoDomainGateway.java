package com.hvs.webstore.back.domain.entity.television.endingdetectado;

import java.util.List;
import java.util.Optional;

public interface EndingDetectadoDomainGateway {

    EndingDetectado create(EndingDetectado aEndingDetectado);

    Optional<EndingDetectado> read(EndingDetectadoId aId);

    List<EndingDetectado> readByEpisodio(Long aEpisodioId);

    List<Long> readEpisodiosNaoDetectados();

    List<Long> readEpisodiosNaoDetectadosPorTipo(String tipo);

    List<Long> readProgramasComEpisodiosNaoDetectados();

    List<Long> readProgramasComEpisodiosNaoDetectadosPorTipo(String tipo);

    void deleteByEpisodio(Long aEpisodioId);

    Long countByPrograma(Long aProgramaId);
}
