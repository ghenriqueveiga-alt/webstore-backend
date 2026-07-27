package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.categoriahierarquia.*;
import com.hvs.webstore.back.app.usecase.webstore.categoriahierarquia.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categoria-hierarquia")
public class CategoriaHierarquiaApiController {

    private final CreateCategoriaHierarquiaUseCase createUseCase;
    private final ReadCategoriaHierarquiaUseCase readUseCase;
    private final UpdateCategoriaHierarquiaUseCase updateUseCase;
    private final PatchCategoriaHierarquiaUseCase patchUseCase;
    private final DeleteCategoriaHierarquiaUseCase deleteUseCase;

    public CategoriaHierarquiaApiController(final CreateCategoriaHierarquiaUseCase aCreateUseCase,
                                            final ReadCategoriaHierarquiaUseCase aReadUseCase,
                                            final UpdateCategoriaHierarquiaUseCase aUpdateUseCase,
                                            final PatchCategoriaHierarquiaUseCase aPatchUseCase,
                                            final DeleteCategoriaHierarquiaUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCategoriaHierarquiaCommand aCreateCategoriaHierarquiaCommand) {

        return createUseCase.execute(aCreateCategoriaHierarquiaCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadCategoriaHierarquiaCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadCategoriaHierarquiaCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdateCategoriaHierarquiaCommand aUpdateCategoriaHierarquiaCommand) {

        return updateUseCase.execute(UpdateCategoriaHierarquiaCommand.from(aId, aUpdateCategoriaHierarquiaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdateCategoriaHierarquiaCommand aUpdateCategoriaHierarquiaCommand) {

        return updateUseCase.execute(UpdateCategoriaHierarquiaCommand.from(aUuid, aUpdateCategoriaHierarquiaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchCategoriaHierarquiaCommand aPatchCategoriaHierarquiaCommand) {

        return patchUseCase.execute(PatchCategoriaHierarquiaCommand.from(aId, aPatchCategoriaHierarquiaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchCategoriaHierarquiaCommand aPatchCategoriaHierarquiaCommand) {

        return patchUseCase.execute(PatchCategoriaHierarquiaCommand.from(aUuid, aPatchCategoriaHierarquiaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeleteCategoriaHierarquiaCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeleteCategoriaHierarquiaCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
