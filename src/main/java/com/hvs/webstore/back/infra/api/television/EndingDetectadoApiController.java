package com.hvs.webstore.back.infra.api.television;

import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectAllEndingCommand;
import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectEndingCommand;
import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectNaoDetectadosEndingCommand;
import com.hvs.webstore.back.app.command.television.endingdetectado.ProcessDetectProgramasEndingCommand;
import com.hvs.webstore.back.app.usecase.television.endingdetectado.ProcessDetectAllEndingUseCase;
import com.hvs.webstore.back.app.usecase.television.endingdetectado.ProcessDetectEndingUseCase;
import com.hvs.webstore.back.app.usecase.television.endingdetectado.ProcessDetectNaoDetectadosEndingUseCase;
import com.hvs.webstore.back.app.usecase.television.endingdetectado.ProcessDetectProgramasEndingUseCase;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ending-detectado")
public class EndingDetectadoApiController {

    private final ProcessDetectEndingUseCase processDetectEndingUseCase;
    private final ProcessDetectAllEndingUseCase processDetectAllEndingUseCase;
    private final ProcessDetectProgramasEndingUseCase processDetectProgramasEndingUseCase;
    private final ProcessDetectNaoDetectadosEndingUseCase processDetectNaoDetectadosEndingUseCase;

    public EndingDetectadoApiController(final ProcessDetectEndingUseCase processDetectEndingUseCase,
                                        final ProcessDetectAllEndingUseCase processDetectAllEndingUseCase,
                                        final ProcessDetectProgramasEndingUseCase processDetectProgramasEndingUseCase,
                                        final ProcessDetectNaoDetectadosEndingUseCase processDetectNaoDetectadosEndingUseCase) {

        this.processDetectEndingUseCase = processDetectEndingUseCase;
        this.processDetectAllEndingUseCase = processDetectAllEndingUseCase;
        this.processDetectProgramasEndingUseCase = processDetectProgramasEndingUseCase;
        this.processDetectNaoDetectadosEndingUseCase = processDetectNaoDetectadosEndingUseCase;
    }

    @PostMapping("/episodio/{episodioId}")
    public ResponseEntity<?> processEndingByEpisodio(@PathVariable("episodioId") Long aEpisodioId) {

        return processDetectEndingUseCase.execute(ProcessDetectEndingCommand.from(aEpisodioId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PostMapping("/programa/{programaId}")
    public ResponseEntity<?> processEndingByPrograma(@PathVariable("programaId") Long aProgramaId) {

        return processDetectAllEndingUseCase.execute(ProcessDetectAllEndingCommand.from(aProgramaId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PostMapping("/nao-detectados")
    public ResponseEntity<?> processEndingNaoDetectados(
            @RequestParam(value = "tipo", required = false) String aTipo) {

        return processDetectNaoDetectadosEndingUseCase.execute(aTipo != null && !aTipo.isBlank()
                        ? ProcessDetectNaoDetectadosEndingCommand.from(aTipo)
                        : ProcessDetectNaoDetectadosEndingCommand.create())
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PostMapping("/todos")
    public ResponseEntity<?> processEndingTodosProgramas() {

        return processDetectProgramasEndingUseCase.execute(ProcessDetectProgramasEndingCommand.create())
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }
}
