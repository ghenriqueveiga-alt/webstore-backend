package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.notificacaotemplate.*;
import com.hvs.webstore.back.app.usecase.webstore.notificacaotemplate.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notificacao-template")
public class NotificacaoTemplateApiController {

    private final CreateNotificacaoTemplateUseCase createUseCase;
    private final ReadNotificacaoTemplateUseCase readUseCase;
    private final UpdateNotificacaoTemplateUseCase updateUseCase;
    private final PatchNotificacaoTemplateUseCase patchUseCase;
    private final DeleteNotificacaoTemplateUseCase deleteUseCase;

    public NotificacaoTemplateApiController(final CreateNotificacaoTemplateUseCase aCreateUseCase,
                                            final ReadNotificacaoTemplateUseCase aReadUseCase,
                                            final UpdateNotificacaoTemplateUseCase aUpdateUseCase,
                                            final PatchNotificacaoTemplateUseCase aPatchUseCase,
                                            final DeleteNotificacaoTemplateUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateNotificacaoTemplateCommand aCreateNotificacaoTemplateCommand) {

        return createUseCase.execute(aCreateNotificacaoTemplateCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadNotificacaoTemplateCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadNotificacaoTemplateCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdateNotificacaoTemplateCommand aUpdateNotificacaoTemplateCommand) {

        return updateUseCase.execute(UpdateNotificacaoTemplateCommand.from(aId, aUpdateNotificacaoTemplateCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdateNotificacaoTemplateCommand aUpdateNotificacaoTemplateCommand) {

        return updateUseCase.execute(UpdateNotificacaoTemplateCommand.from(aUuid, aUpdateNotificacaoTemplateCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchNotificacaoTemplateCommand aPatchNotificacaoTemplateCommand) {

        return patchUseCase.execute(PatchNotificacaoTemplateCommand.from(aId, aPatchNotificacaoTemplateCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchNotificacaoTemplateCommand aPatchNotificacaoTemplateCommand) {

        return patchUseCase.execute(PatchNotificacaoTemplateCommand.from(aUuid, aPatchNotificacaoTemplateCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeleteNotificacaoTemplateCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeleteNotificacaoTemplateCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
