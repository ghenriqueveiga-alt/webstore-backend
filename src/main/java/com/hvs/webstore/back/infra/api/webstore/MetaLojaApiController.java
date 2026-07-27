package com.hvs.webstore.back.infra.api.webstore;

import com.hvs.webstore.back.app.command.webstore.metaloja.*;
import com.hvs.webstore.back.app.usecase.webstore.metaloja.*;
import com.hvs.webstore.back.domain.pagination.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/meta-loja")
public class MetaLojaApiController {

    private final CreateMetaLojaUseCase createUseCase;
    private final ReadMetaLojaUseCase readUseCase;
    private final ReadAllMetaLojaUseCase readAllUseCase;
    private final UpdateMetaLojaUseCase updateUseCase;
    private final PatchMetaLojaUseCase patchUseCase;
    private final DeleteMetaLojaUseCase deleteUseCase;

    public MetaLojaApiController(final CreateMetaLojaUseCase aCreateUseCase,
                                 final ReadMetaLojaUseCase aReadUseCase,
                                 final ReadAllMetaLojaUseCase aReadAllUseCase,
                                 final UpdateMetaLojaUseCase aUpdateUseCase,
                                 final PatchMetaLojaUseCase aPatchUseCase,
                                 final DeleteMetaLojaUseCase aDeleteUseCase) {

        this.createUseCase = aCreateUseCase;
        this.readUseCase = aReadUseCase;
        this.readAllUseCase = aReadAllUseCase;
        this.updateUseCase = aUpdateUseCase;
        this.patchUseCase = aPatchUseCase;
        this.deleteUseCase = aDeleteUseCase;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateMetaLojaCommand aCreateMetaLojaCommand) {

        return createUseCase.execute(aCreateMetaLojaCommand)
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> readById(@PathVariable Long aId) {

        return readUseCase.execute(ReadMetaLojaCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<?> readByUuid(@PathVariable String aUuid) {

        return readUseCase.execute(ReadMetaLojaCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAll(@RequestParam(required = false) String aSearch,
                                     @RequestParam(defaultValue = "0") int aPage,
                                     @RequestParam(defaultValue = "10") int aSize,
                                     @RequestParam(defaultValue = "id") String aSort,
                                     @RequestParam(defaultValue = "asc") String aDirection) {

        return readAllUseCase.execute(new ReadAllMetaLojaCommand(new SearchQuery(aSearch, aPage, aSize, aSort, aDirection)))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long aId,
                                        @RequestBody UpdateMetaLojaCommand aUpdateMetaLojaCommand) {

        return updateUseCase.execute(UpdateMetaLojaCommand.from(aId, aUpdateMetaLojaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PutMapping("/uuid/{uuid}")
    public ResponseEntity<?> updateByUuid(@PathVariable String aUuid,
                                          @RequestBody UpdateMetaLojaCommand aUpdateMetaLojaCommand) {

        return updateUseCase.execute(UpdateMetaLojaCommand.from(aUuid, aUpdateMetaLojaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity<?> patchById(@PathVariable Long aId,
                                       @RequestBody PatchMetaLojaCommand aPatchMetaLojaCommand) {

        return patchUseCase.execute(PatchMetaLojaCommand.from(aId, aPatchMetaLojaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @PatchMapping("/uuid/{uuid}")
    public ResponseEntity<?> patchByUuid(@PathVariable String aUuid,
                                         @RequestBody PatchMetaLojaCommand aPatchMetaLojaCommand) {

        return patchUseCase.execute(PatchMetaLojaCommand.from(aUuid, aPatchMetaLojaCommand))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long aId) {

        return deleteUseCase.execute(DeleteMetaLojaCommand.from(aId))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }

    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<?> deleteByUuid(@PathVariable String aUuid) {

        return deleteUseCase.execute(DeleteMetaLojaCommand.from(aUuid))
                .fold(e -> new ResponseEntity<>(e, HttpStatus.CONFLICT),
                        s -> new ResponseEntity<>(s, HttpStatus.OK));
    }
}
