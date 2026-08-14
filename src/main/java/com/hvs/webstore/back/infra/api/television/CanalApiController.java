package com.hvs.webstore.back.infra.api.television;

import com.hvs.webstore.back.app.command.television.canal.*;
import com.hvs.webstore.back.app.usecase.television.canal.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/canal")
public class CanalApiController {

    private final CreateCanalUseCase createCanalUseCase;
    private final ReadCanalUseCase readCanalUseCase;
    private final ReadAllCanalUseCase readAllCanalUseCase;
    private final UpdateCanalUseCase updateCanalUseCase;
    private final PatchCanalUseCase patchCanalUseCase;
    private final DeleteCanalUseCase deleteCanalUseCase;

    public CanalApiController(final CreateCanalUseCase createCanalUseCase,
                              final ReadCanalUseCase readCanalUseCase,
                              final ReadAllCanalUseCase readAllCanalUseCase,
                              final UpdateCanalUseCase updateCanalUseCase,
                              final PatchCanalUseCase patchCanalUseCase,
                              final DeleteCanalUseCase deleteCanalUseCase) {

        this.createCanalUseCase = createCanalUseCase;
        this.readCanalUseCase = readCanalUseCase;
        this.readAllCanalUseCase = readAllCanalUseCase;
        this.updateCanalUseCase = updateCanalUseCase;
        this.patchCanalUseCase = patchCanalUseCase;
        this.deleteCanalUseCase = deleteCanalUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createCanal(@RequestBody CreateCanalCommand aInput) {

        return this.createCanalUseCase.execute(aInput)
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> readCanalById(@PathVariable("id") Long aId) {

        return this.readCanalUseCase.execute(ReadCanalCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> readCanalByUuid(@PathVariable("uuid") String aUuid) {

        return this.readCanalUseCase.execute(ReadCanalCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAllCanal(@RequestParam(required = false) String search,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sort,
                                          @RequestParam(defaultValue = "asc") String direction) {

        return this.readAllCanalUseCase.execute(new ReadAllCanalCommand(
                        new CanalSearchQuery(search, page, size, sort, direction)))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<?> updateCanalById(@PathVariable("id") Long aId,
                                             @RequestBody UpdateCanalCommand aInput) {

        return this.updateCanalUseCase.execute(UpdateCanalCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> updateCanalByUuid(
            @PathVariable("uuid") String aUuid,
            @RequestBody UpdateCanalCommand aInput) {

        return this.updateCanalUseCase.execute(UpdateCanalCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/id/{id}")
    public ResponseEntity<?> patchCanalById(@PathVariable("id") Long aId,
                                            @RequestBody PatchCanalCommand aInput) {

        return this.patchCanalUseCase.execute(PatchCanalCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> patchCanalByUuid(@PathVariable("uuid") String aUuid,
                                              @RequestBody PatchCanalCommand aInput) {

        return this.patchCanalUseCase.execute(PatchCanalCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> deleteCanalById(@PathVariable("id") Long aId) {

        return this.deleteCanalUseCase.execute(DeleteCanalCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> deleteCanalByUuid(@PathVariable("uuid") String aUuid) {

        return this.deleteCanalUseCase.execute(DeleteCanalCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }
}
