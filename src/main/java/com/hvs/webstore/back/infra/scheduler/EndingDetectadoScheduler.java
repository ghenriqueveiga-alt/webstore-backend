package com.hvs.webstore.back.infra.scheduler;

import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectProgramasEndingCommand;
import com.hvs.webstore.back.app.output.television.endingdetectado.ProcessDetectProgramasEndingOutput;
import com.hvs.webstore.back.app.usecase.television.endingdetectado.ProcessDetectProgramasEndingUseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class EndingDetectadoScheduler {

    private static final Logger log = LoggerFactory.getLogger(EndingDetectadoScheduler.class);

    private final ProcessDetectProgramasEndingUseCase processDetectProgramasEndingUseCase;
    private final AtomicBoolean emExecucao = new AtomicBoolean(false);

    public EndingDetectadoScheduler(final ProcessDetectProgramasEndingUseCase processDetectProgramasEndingUseCase) {

        this.processDetectProgramasEndingUseCase = processDetectProgramasEndingUseCase;
    }

    @Scheduled(
            initialDelayString = "${webstore.ending-detectado.scheduler.init-delay:180000}",
            fixedDelayString = "${webstore.ending-detectado.scheduler.interval:3600000}")
    public void processarProgramasPendentes() {

        if (!this.emExecucao.compareAndSet(false, true)) {
            log.info("Ending detection already running; skipping this step.");
            return;
        }
        try {
            final Either<?, ?> resultado =
                    this.processDetectProgramasEndingUseCase.execute(ProcessDetectProgramasEndingCommand.create());
            if (resultado.isRight()) {
                final ProcessDetectProgramasEndingOutput output = (ProcessDetectProgramasEndingOutput) resultado.get();
                log.info("Ending detection finished: {} processed, {} ignored, {} detected.",
                        output.aProgramasProcessados(), output.aProgramasIgnorados(), output.aTotalDetectados());
            } else {
                log.warn("Ending detection finished with errors: {}",
                        ((Notification) resultado.getLeft()).getErrors());
            }
        } catch (Exception e) {
            log.error("Ending detection failed.", e);
        } finally {
            this.emExecucao.set(false);
        }
    }
}
