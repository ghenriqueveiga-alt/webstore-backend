package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.carrinhofrete.*;
import com.hvs.webstore.back.app.usecase.webstore.carrinhofrete.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carrinho-frete")
public class CarrinhoFreteApiController {

    private final CreateCarrinhoFreteUseCase createUseCase;
    private final ReadCarrinhoFreteUseCase readUseCase;
    private final UpdateCarrinhoFreteUseCase updateUseCase;
    private final PatchCarrinhoFreteUseCase patchUseCase;
    private final DeleteCarrinhoFreteUseCase deleteUseCase;

    public CarrinhoFreteApiController(final CreateCarrinhoFreteUseCase aCreateUseCase,
                                      final ReadCarrinhoFreteUseCase aReadUseCase,
                                      final UpdateCarrinhoFreteUseCase aUpdateUseCase,
                                      final PatchCarrinhoFreteUseCase aPatchUseCase,
                                      final DeleteCarrinhoFreteUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCarrinhoFreteCommand aCreateCarrinhoFreteCommand) {

        return createUseCase.execute(aCreateCarrinhoFreteCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadCarrinhoFreteCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadCarrinhoFreteCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdateCarrinhoFreteCommand aUpdateCarrinhoFreteCommand) {

        return updateUseCase.execute(UpdateCarrinhoFreteCommand.from(aId,
                aUpdateCarrinhoFreteCommand.aUuid(),
                aUpdateCarrinhoFreteCommand.aStatusCode(),
                aUpdateCarrinhoFreteCommand.aCarrinhoId(),
                aUpdateCarrinhoFreteCommand.aFreteId(),
                aUpdateCarrinhoFreteCommand.aValor(),
                aUpdateCarrinhoFreteCommand.aPrazo(),
                aUpdateCarrinhoFreteCommand.aTransportadoraId()))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdateCarrinhoFreteCommand aUpdateCarrinhoFreteCommand) {

        return updateUseCase.execute(UpdateCarrinhoFreteCommand.from(null,
                aUuid,
                aUpdateCarrinhoFreteCommand.aStatusCode(),
                aUpdateCarrinhoFreteCommand.aCarrinhoId(),
                aUpdateCarrinhoFreteCommand.aFreteId(),
                aUpdateCarrinhoFreteCommand.aValor(),
                aUpdateCarrinhoFreteCommand.aPrazo(),
                aUpdateCarrinhoFreteCommand.aTransportadoraId()))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchCarrinhoFreteCommand aPatchCarrinhoFreteCommand) {

        return patchUseCase.execute(PatchCarrinhoFreteCommand.from(aUuid,
                aPatchCarrinhoFreteCommand.aStatusCode(),
                aPatchCarrinhoFreteCommand.aCarrinhoId(),
                aPatchCarrinhoFreteCommand.aFreteId(),
                aPatchCarrinhoFreteCommand.aValor(),
                aPatchCarrinhoFreteCommand.aPrazo(),
                aPatchCarrinhoFreteCommand.aTransportadoraId()))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeleteCarrinhoFreteCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeleteCarrinhoFreteCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
