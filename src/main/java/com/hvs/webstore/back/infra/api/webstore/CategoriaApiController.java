package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.categoria.*;
import com.hvs.webstore.back.app.usecase.webstore.categoria.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaApiController {

    private final CreateCategoriaUseCase createUseCase;
    private final ReadCategoriaUseCase readUseCase;
    private final UpdateCategoriaUseCase updateUseCase;
    private final PatchCategoriaUseCase patchUseCase;
    private final DeleteCategoriaUseCase deleteUseCase;

    public CategoriaApiController(final CreateCategoriaUseCase aCreateUseCase,
                                  final ReadCategoriaUseCase aReadUseCase,
                                  final UpdateCategoriaUseCase aUpdateUseCase,
                                  final PatchCategoriaUseCase aPatchUseCase,
                                  final DeleteCategoriaUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCategoriaCommand aCreateCategoriaCommand) {

        return createUseCase.execute(aCreateCategoriaCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadCategoriaCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadCategoriaCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdateCategoriaCommand aUpdateCategoriaCommand) {

        return updateUseCase.execute(UpdateCategoriaCommand.from(aId, aUpdateCategoriaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdateCategoriaCommand aUpdateCategoriaCommand) {

        return updateUseCase.execute(UpdateCategoriaCommand.from(aUuid, aUpdateCategoriaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchCategoriaCommand aPatchCategoriaCommand) {

        return patchUseCase.execute(PatchCategoriaCommand.from(aId, aPatchCategoriaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchCategoriaCommand aPatchCategoriaCommand) {

        return patchUseCase.execute(PatchCategoriaCommand.from(aUuid, aPatchCategoriaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeleteCategoriaCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeleteCategoriaCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
