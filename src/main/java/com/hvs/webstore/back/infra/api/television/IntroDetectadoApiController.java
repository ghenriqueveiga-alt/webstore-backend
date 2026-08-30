package com.hvs.webstore.back.infra.api.television;

import com.hvs.webstore.back.app.command.television.introdetectado.ProcessDetectAllIntroCommand;
import com.hvs.webstore.back.app.command.television.introdetectado.ProcessDetectIntroCommand;
import com.hvs.webstore.back.app.command.television.introdetectado.ProcessDetectNaoDetectadosIntroCommand;
import com.hvs.webstore.back.app.command.television.introdetectado.ProcessDetectProgramasIntroCommand;
import com.hvs.webstore.back.app.usecase.television.introdetectado.ProcessDetectAllIntroUseCase;
import com.hvs.webstore.back.app.usecase.television.introdetectado.ProcessDetectIntroUseCase;
import com.hvs.webstore.back.app.usecase.television.introdetectado.ProcessDetectNaoDetectadosIntroUseCase;
import com.hvs.webstore.back.app.usecase.television.introdetectado.ProcessDetectProgramasIntroUseCase;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/intro-detectado")
public class IntroDetectadoApiController {

    private final ProcessDetectIntroUseCase processDetectIntroUseCase;
    private final ProcessDetectAllIntroUseCase processDetectAllIntroUseCase;
    private final ProcessDetectProgramasIntroUseCase processDetectProgramasIntroUseCase;
    private final ProcessDetectNaoDetectadosIntroUseCase processDetectNaoDetectadosIntroUseCase;

    public IntroDetectadoApiController(final ProcessDetectIntroUseCase processDetectIntroUseCase,
                                       final ProcessDetectAllIntroUseCase processDetectAllIntroUseCase,
                                       final ProcessDetectProgramasIntroUseCase processDetectProgramasIntroUseCase,
                                       final ProcessDetectNaoDetectadosIntroUseCase processDetectNaoDetectadosIntroUseCase) {

        this.processDetectIntroUseCase = processDetectIntroUseCase;
        this.processDetectAllIntroUseCase = processDetectAllIntroUseCase;
        this.processDetectProgramasIntroUseCase = processDetectProgramasIntroUseCase;
        this.processDetectNaoDetectadosIntroUseCase = processDetectNaoDetectadosIntroUseCase;
    }

    @PostMapping("/episodio/{episodioId}")
    public ResponseEntity<?> processIntroByEpisodio(@PathVariable("episodioId") Long aEpisodioId) {

        return processDetectIntroUseCase.execute(ProcessDetectIntroCommand.from(aEpisodioId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PostMapping("/programa/{programaId}")
    public ResponseEntity<?> processIntroByPrograma(@PathVariable("programaId") Long aProgramaId) {

        return processDetectAllIntroUseCase.execute(ProcessDetectAllIntroCommand.from(aProgramaId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PostMapping("/nao-detectados")
    public ResponseEntity<?> processIntroNaoDetectados(
            @RequestParam(value = "tipo", required = false) String aTipo) {

        return processDetectNaoDetectadosIntroUseCase.execute(aTipo != null && !aTipo.isBlank()
                        ? ProcessDetectNaoDetectadosIntroCommand.from(aTipo)
                        : ProcessDetectNaoDetectadosIntroCommand.create())
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PostMapping("/todos")
    public ResponseEntity<?> processIntroTodosProgramas() {

        return processDetectProgramasIntroUseCase.execute(ProcessDetectProgramasIntroCommand.create())
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }
}