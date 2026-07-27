package com.hvs.webstore.back.infra.api.television;

import com.hvs.webstore.back.app.command.television.arquivo.*;
import com.hvs.webstore.back.app.usecase.television.arquivo.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/arquivo")
public class ArquivoApiController {

    private final CreateArquivoUseCase createArquivoUseCase;
    private final ReadArquivoUseCase readArquivoUseCase;
    private final ReadAllArquivoUseCase readAllArquivoUseCase;
    private final UpdateArquivoUseCase updateArquivoUseCase;
    private final PatchArquivoUseCase patchArquivoUseCase;
    private final DeleteArquivoUseCase deleteArquivoUseCase;

    public ArquivoApiController(
            final CreateArquivoUseCase createArquivoUseCase,
            final ReadArquivoUseCase readArquivoUseCase,
            final ReadAllArquivoUseCase readAllArquivoUseCase,
            final UpdateArquivoUseCase updateArquivoUseCase,
            final PatchArquivoUseCase patchArquivoUseCase,
            final DeleteArquivoUseCase deleteArquivoUseCase) {
        this.createArquivoUseCase = createArquivoUseCase;
        this.readArquivoUseCase = readArquivoUseCase;
        this.readAllArquivoUseCase = readAllArquivoUseCase;
        this.updateArquivoUseCase = updateArquivoUseCase;
        this.patchArquivoUseCase = patchArquivoUseCase;
        this.deleteArquivoUseCase = deleteArquivoUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createArquivo(
            @RequestBody CreateArquivoCommand aInput) {

        return this.createArquivoUseCase.execute(aInput)
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> readArquivoById(
            @PathVariable("id") Long aId) {

        return this.readArquivoUseCase.execute(ReadArquivoCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> readArquivoByUuid(
            @PathVariable("uuid") String aUuid) {

        return this.readArquivoUseCase.execute(ReadArquivoCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAllArquivo(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        return this.readAllArquivoUseCase.execute(new ReadAllArquivoCommand(
                        new ArquivoSearchQuery(search, page, size, sort, direction)))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<?> updateArticleById(
            @PathVariable("id") Long aId,
            @RequestBody UpdateArquivoCommand aInput) {

        return this.updateArquivoUseCase.execute(UpdateArquivoCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> updateArticleByUuid(
            @PathVariable("uuid") String aUuid,
            @RequestBody UpdateArquivoCommand aInput) {

        return this.updateArquivoUseCase.execute(UpdateArquivoCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/id/{id}")
    public ResponseEntity<?> patchArquivoById(
            @PathVariable("id") Long aId,
            @RequestBody PatchArquivoCommand aInput) {

        return this.patchArquivoUseCase.execute(PatchArquivoCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> patchArquivoByUuid(
            @PathVariable("uuid") String aUuid,
            @RequestBody PatchArquivoCommand aInput) {

        return this.patchArquivoUseCase.execute(PatchArquivoCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> deleteArquivoById(
            @PathVariable("id") Long aId) {

        return this.deleteArquivoUseCase.execute(DeleteArquivoCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> deleteArquivoByUuid(
            @PathVariable("uuid") String aUuid) {

        return this.deleteArquivoUseCase.execute(DeleteArquivoCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }
}