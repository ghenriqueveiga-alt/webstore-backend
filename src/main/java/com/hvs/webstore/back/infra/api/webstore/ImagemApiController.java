package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.imagem.*;
import com.hvs.webstore.back.app.usecase.webstore.imagem.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/imagem")
public class ImagemApiController {

    private final CreateImagemUseCase createUseCase;
    private final ReadImagemUseCase readUseCase;
    private final UpdateImagemUseCase updateUseCase;
    private final PatchImagemUseCase patchUseCase;
    private final DeleteImagemUseCase deleteUseCase;

    public ImagemApiController(final CreateImagemUseCase aCreateUseCase,
                               final ReadImagemUseCase aReadUseCase,
                               final UpdateImagemUseCase aUpdateUseCase,
                               final PatchImagemUseCase aPatchUseCase,
                               final DeleteImagemUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateImagemCommand aCreateImagemCommand) {

        return createUseCase.execute(aCreateImagemCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadImagemCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadImagemCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdateImagemCommand aUpdateImagemCommand) {

        return updateUseCase.execute(UpdateImagemCommand.from(aId, aUpdateImagemCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdateImagemCommand aUpdateImagemCommand) {

        return updateUseCase.execute(UpdateImagemCommand.from(aUuid, aUpdateImagemCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchImagemCommand aPatchImagemCommand) {

        return patchUseCase.execute(PatchImagemCommand.from(aId, aPatchImagemCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchImagemCommand aPatchImagemCommand) {

        return patchUseCase.execute(PatchImagemCommand.from(aUuid, aPatchImagemCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeleteImagemCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeleteImagemCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
