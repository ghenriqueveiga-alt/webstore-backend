package com.hvs.webstore.back.app.usecase.television.endingdetectado;

import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectAllEndingCommand;
import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectProgramasEndingCommand;
import com.hvs.webstore.back.app.command.television.programa.ProgramaSearchQuery;
import com.hvs.webstore.back.app.output.television.endingdetectado.ProcessDetectProgramasEndingOutput;
import com.hvs.webstore.back.domain.entity.television.endingdetectado.EndingDetectadoDomainGateway;
import com.hvs.webstore.back.domain.entity.television.episodio.Episodio;
import com.hvs.webstore.back.domain.entity.television.episodio.EpisodioDomainGateway;
import com.hvs.webstore.back.domain.entity.television.programa.Programa;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaDomainGateway;
import com.hvs.webstore.back.domain.entity.television.programa.ProgramaId;
import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;

import java.util.ArrayList;
import java.util.List;

public class ProcessDetectProgramasEndingUseCaseImpl extends ProcessDetectProgramasEndingUseCase {

    private static final int PAGE_SIZE = 50;

    private final ProgramaDomainGateway programaGateway;
    private final EpisodioDomainGateway episodioGateway;
    private final EndingDetectadoDomainGateway endingDetectadoGateway;
    private final ProcessDetectAllEndingUseCase processDetectAllEndingUseCase;

    public ProcessDetectProgramasEndingUseCaseImpl(final ProgramaDomainGateway programaGateway,
                                                   final EpisodioDomainGateway episodioGateway,
                                                   final EndingDetectadoDomainGateway endingDetectadoGateway,
                                                   final ProcessDetectAllEndingUseCase processDetectAllEndingUseCase) {

        this.programaGateway = programaGateway;
        this.episodioGateway = episodioGateway;
        this.endingDetectadoGateway = endingDetectadoGateway;
        this.processDetectAllEndingUseCase = processDetectAllEndingUseCase;
    }

    @Override
    public Either<Notification, ProcessDetectProgramasEndingOutput> execute(final ProcessDetectProgramasEndingCommand aIn) {

        int processados = 0;
        int ignorados = 0;
        int totalDetectados = 0;
        final List<String> detalhes = new ArrayList<>();
        int pagina = 0;
        long totalPaginas;

        do {
            final Pagination<Programa> resultados = this.programaGateway.readAll(
                    ProgramaSearchQuery.from(null, pagina, PAGE_SIZE, "id", "asc"));

            for (Programa programa : resultados.aContent()) {
                final Long programaId = ((ProgramaId) programa.getId()).getValue();
                final List<Episodio> episodios = this.episodioGateway.readByPrograma(programaId);
                if (episodios.isEmpty()) {
                    continue;
                }
                final long processadosNoPrograma = this.endingDetectadoGateway.countByPrograma(programaId);
                if (processadosNoPrograma >= episodios.size()) {
                    ignorados++;
                    detalhes.add(programa.getNome() + " (" + programaId + "): ja processado (" + episodios.size() + " eps)");
                    continue;
                }
                final Either resultado;
                try {
                    resultado = this.processDetectAllEndingUseCase.execute(ProcessDetectAllEndingCommand.from(programaId));
                } catch (Exception e) {
                    detalhes.add(programa.getNome() + " (" + programaId + "): erro - " + e.getMessage());
                    continue;
                }
                if (resultado.isRight()) {
                    processados++;
                    totalDetectados += this.endingDetectadoGateway.countByPrograma(programaId);
                    detalhes.add(programa.getNome() + " (" + programaId + "): processado (" + episodios.size() + " eps)");
                } else {
                    detalhes.add(programa.getNome() + " (" + programaId + "): falhou - " + String.valueOf(resultado.getLeft()));
                }
            }
            totalPaginas = resultados.aTotalPages();
            pagina++;
        } while (pagina < totalPaginas);

        return Either.right(ProcessDetectProgramasEndingOutput.from(processados, ignorados, totalDetectados, detalhes));
    }
}
