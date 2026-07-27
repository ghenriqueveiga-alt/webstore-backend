package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.preferencianotificacao.*;
import com.hvs.webstore.back.app.usecase.webstore.preferencianotificacao.*;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/preferencia-notificacao")
public class PreferenciaNotificacaoApiController {

    private final CreatePreferenciaNotificacaoUseCase createUseCase;
    private final ReadPreferenciaNotificacaoUseCase readUseCase;
    private final ReadAllPreferenciaNotificacaoUseCase readAllUseCase;
    private final UpdatePreferenciaNotificacaoUseCase updateUseCase;
    private final PatchPreferenciaNotificacaoUseCase patchUseCase;
    private final DeletePreferenciaNotificacaoUseCase deleteUseCase;

    public PreferenciaNotificacaoApiController(final CreatePreferenciaNotificacaoUseCase aCreateUseCase,
                                               final ReadPreferenciaNotificacaoUseCase aReadUseCase,
                                               final ReadAllPreferenciaNotificacaoUseCase aReadAllUseCase,
                                               final UpdatePreferenciaNotificacaoUseCase aUpdateUseCase,
                                               final PatchPreferenciaNotificacaoUseCase aPatchUseCase,
                                               final DeletePreferenciaNotificacaoUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.readAllUseCase = aReadAllUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePreferenciaNotificacaoCommand aCreatePreferenciaNotificacaoCommand) {

        return createUseCase.execute(aCreatePreferenciaNotificacaoCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadPreferenciaNotificacaoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadPreferenciaNotificacaoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAll(@RequestParam(required = false) String aSearch,
                                     @RequestParam(defaultValue = "0") int aPage,
                                     @RequestParam(defaultValue = "10") int aSize,
                                     @RequestParam(defaultValue = "id") String aSort,
                                     @RequestParam(defaultValue = "asc") String aDirection) {

        return readAllUseCase.execute(new ReadAllPreferenciaNotificacaoCommand(new SearchQuery(aSearch, aPage, aSize, aSort, aDirection)))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdatePreferenciaNotificacaoCommand aUpdatePreferenciaNotificacaoCommand) {

        return updateUseCase.execute(UpdatePreferenciaNotificacaoCommand.from(aId, aUpdatePreferenciaNotificacaoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdatePreferenciaNotificacaoCommand aUpdatePreferenciaNotificacaoCommand) {

        return updateUseCase.execute(UpdatePreferenciaNotificacaoCommand.from(aUuid, aUpdatePreferenciaNotificacaoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchPreferenciaNotificacaoCommand aPatchPreferenciaNotificacaoCommand) {

        return patchUseCase.execute(PatchPreferenciaNotificacaoCommand.from(aId, aPatchPreferenciaNotificacaoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchPreferenciaNotificacaoCommand aPatchPreferenciaNotificacaoCommand) {

        return patchUseCase.execute(PatchPreferenciaNotificacaoCommand.from(aUuid, aPatchPreferenciaNotificacaoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeletePreferenciaNotificacaoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeletePreferenciaNotificacaoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
