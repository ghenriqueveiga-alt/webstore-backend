package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.avaliacao.*;
import com.hvs.webstore.back.app.usecase.webstore.avaliacao.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/avaliacao")
public class AvaliacaoApiController {

    private final CreateAvaliacaoUseCase createUseCase;
    private final ReadAvaliacaoUseCase readUseCase;
    private final UpdateAvaliacaoUseCase updateUseCase;
    private final PatchAvaliacaoUseCase patchUseCase;
    private final DeleteAvaliacaoUseCase deleteUseCase;

    public AvaliacaoApiController(final CreateAvaliacaoUseCase aCreateUseCase,
                                  final ReadAvaliacaoUseCase aReadUseCase,
                                  final UpdateAvaliacaoUseCase aUpdateUseCase,
                                  final PatchAvaliacaoUseCase aPatchUseCase,
                                  final DeleteAvaliacaoUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateAvaliacaoCommand aCreateAvaliacaoCommand) {

        return createUseCase.execute(aCreateAvaliacaoCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadAvaliacaoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadAvaliacaoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdateAvaliacaoCommand aUpdateAvaliacaoCommand) {

        return updateUseCase.execute(UpdateAvaliacaoCommand.from(aId, aUpdateAvaliacaoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdateAvaliacaoCommand aUpdateAvaliacaoCommand) {

        return updateUseCase.execute(UpdateAvaliacaoCommand.from(aUuid, aUpdateAvaliacaoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchAvaliacaoCommand aPatchAvaliacaoCommand) {

        return patchUseCase.execute(PatchAvaliacaoCommand.from(aId, aPatchAvaliacaoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchAvaliacaoCommand aPatchAvaliacaoCommand) {

        return patchUseCase.execute(PatchAvaliacaoCommand.from(aUuid, aPatchAvaliacaoCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeleteAvaliacaoCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeleteAvaliacaoCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
