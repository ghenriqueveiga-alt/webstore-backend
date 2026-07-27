package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.logauditoria.*;
import com.hvs.webstore.back.app.usecase.webstore.logauditoria.*;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/log-auditoria")
public class LogAuditoriaApiController {

    private final CreateLogAuditoriaUseCase createUseCase;
    private final ReadLogAuditoriaUseCase readUseCase;
    private final ReadLogAuditoriaByUsuarioIdUseCase readByUsuarioIdUseCase;
    private final ReadLogAuditoriaByEntidadeUseCase readByEntidadeUseCase;
    private final ReadAllLogAuditoriaUseCase readAllUseCase;

    public LogAuditoriaApiController(final CreateLogAuditoriaUseCase aCreateUseCase,
                                     final ReadLogAuditoriaUseCase aReadUseCase,
                                     final ReadLogAuditoriaByUsuarioIdUseCase aReadByUsuarioIdUseCase,
                                     final ReadLogAuditoriaByEntidadeUseCase aReadByEntidadeUseCase,
                                     final ReadAllLogAuditoriaUseCase aReadAllUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.readByUsuarioIdUseCase = aReadByUsuarioIdUseCase;
        this.readByEntidadeUseCase = aReadByEntidadeUseCase;
        this.readAllUseCase = aReadAllUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateLogAuditoriaCommand aCreateCommand) {

        return createUseCase.execute(aCreateCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadLogAuditoriaCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadLogAuditoriaCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> readByUsuarioId(@PathVariable Long aUsuarioId) {

        return readByUsuarioIdUseCase.execute(ReadLogAuditoriaByUsuarioIdCommand.from(aUsuarioId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/entidade/{entidadeNome}/{entidadeId}")
    public ResponseEntity<?> readByEntidade(@PathVariable String aEntidadeNome,
                                            @PathVariable Long aEntidadeId) {

        return readByEntidadeUseCase.execute(ReadLogAuditoriaByEntidadeCommand.from(aEntidadeNome, aEntidadeId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAll(@RequestParam(required = false) String aSearch,
                                     @RequestParam(defaultValue = "0") int aPage,
                                     @RequestParam(defaultValue = "10") int aSize,
                                     @RequestParam(defaultValue = "id") String aSort,
                                     @RequestParam(defaultValue = "asc") String aDirection) {

        return readAllUseCase.execute(new ReadAllLogAuditoriaCommand(new SearchQuery(aSearch, aPage, aSize, aSort, aDirection)))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
