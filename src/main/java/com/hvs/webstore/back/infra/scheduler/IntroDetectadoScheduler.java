package com.hvs.webstore.back.infra.scheduler;

import com.hvs.webstore.back.app.command.television.introdetectado.ProcessDetectProgramasIntroCommand;
import com.hvs.webstore.back.app.output.television.introdetectado.ProcessDetectProgramasIntroOutput;
import com.hvs.webstore.back.app.usecase.television.introdetectado.ProcessDetectProgramasIntroUseCase;
import com.hvs.webstore.back.domain.validation.notification.Notification;
import io.vavr.control.Either;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class IntroDetectadoScheduler {

    private static final Logger log = LoggerFactory.getLogger(IntroDetectadoScheduler.class);

    private final ProcessDetectProgramasIntroUseCase processDetectProgramasIntroUseCase;
    private final AtomicBoolean emExecucao = new AtomicBoolean(false);

    public IntroDetectadoScheduler(final ProcessDetectProgramasIntroUseCase processDetectProgramasIntroUseCase) {

        this.processDetectProgramasIntroUseCase = processDetectProgramasIntroUseCase;
    }

    @Scheduled(
            initialDelayString = "${webstore.intro-detectado.scheduler.init-delay:180000}",
            fixedDelayString = "${webstore.intro-detectado.scheduler.interval:3600000}")
    public void processarProgramasPendentes() {

        if (!this.emExecucao.compareAndSet(false, true)) {
            log.info("Intro detection already running; skipping this step.");
            return;
        }
        try {
            final Either<?, ?> resultado =
                    this.processDetectProgramasIntroUseCase.execute(ProcessDetectProgramasIntroCommand.create());
            if (resultado.isRight()) {
                final ProcessDetectProgramasIntroOutput output = (ProcessDetectProgramasIntroOutput) resultado.get();
                log.info("Intro detection finished: {} processed, {} ignored, {} detected.",
                        output.aProgramasProcessados(), output.aProgramasIgnorados(), output.aTotalDetectados());
            } else {
                log.warn("Intro detection finished with errors: {}",
                        ((Notification) resultado.getLeft()).getErrors());
            }
        } catch (Exception e) {
            log.error("Intro detection failed.", e);
        } finally {
            this.emExecucao.set(false);
        }
    }
}