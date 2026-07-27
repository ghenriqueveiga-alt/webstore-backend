package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.formapagamento.*;
import com.hvs.webstore.back.app.usecase.webstore.formapagamento.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/formapagamento")
public class FormaPagamentoApiController {

    private final CreateFormaPagamentoUseCase createUseCase;
    private final ReadFormaPagamentoUseCase readUseCase;
    private final UpdateFormaPagamentoUseCase updateUseCase;
    private final PatchFormaPagamentoUseCase patchUseCase;
    private final DeleteFormaPagamentoUseCase deleteUseCase;

    public FormaPagamentoApiController(final CreateFormaPagamentoUseCase aCreateUseCase,
                                       final ReadFormaPagamentoUseCase aReadUseCase,
                                       final UpdateFormaPagamentoUseCase aUpdateUseCase,
                                       final PatchFormaPagamentoUseCase aPatchUseCase,
                                       final DeleteFormaPagamentoUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateFormaPagamentoCommand aCreateFormaPagamentoCommand) {

        return createUseCase.execute(aCreateFormaPagamentoCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadFormaPagamentoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadFormaPagamentoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdateFormaPagamentoCommand aUpdateFormaPagamentoCommand) {

        return updateUseCase.execute(UpdateFormaPagamentoCommand.from(aId, aUpdateFormaPagamentoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdateFormaPagamentoCommand aUpdateFormaPagamentoCommand) {

        return updateUseCase.execute(UpdateFormaPagamentoCommand.from(aUuid, aUpdateFormaPagamentoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchFormaPagamentoCommand aPatchFormaPagamentoCommand) {

        return patchUseCase.execute(PatchFormaPagamentoCommand.from(aId, aPatchFormaPagamentoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchFormaPagamentoCommand aPatchFormaPagamentoCommand) {

        return patchUseCase.execute(PatchFormaPagamentoCommand.from(aUuid, aPatchFormaPagamentoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeleteFormaPagamentoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeleteFormaPagamentoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
