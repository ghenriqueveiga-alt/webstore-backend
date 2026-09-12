package com.hvs.webstore.back.infra.api.television;

import com.hvs.webstore.back.app.command.television.programa.*;
import com.hvs.webstore.back.app.service.MediaPathResolver;
import com.hvs.webstore.back.app.usecase.television.programa.*;
import com.hvs.webstore.back.infra.persistence.television.episodio.EpisodioEntity;
import com.hvs.webstore.back.infra.persistence.television.episodio.EpisodioJpaRepository;
import com.hvs.webstore.back.infra.persistence.television.programa.ProgramaEntity;
import com.hvs.webstore.back.infra.persistence.television.programa.ProgramaJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;

@RestController
@RequestMapping("/api/v1/programa")
public class ProgramaApiController {

    private final CreateProgramaUseCase createProgramaUseCase;
    private final ReadProgramaUseCase readProgramaUseCase;
    private final ReadAllProgramaUseCase readAllProgramaUseCase;
    private final UpdateProgramaUseCase updateProgramaUseCase;
    private final PatchProgramaUseCase patchProgramaUseCase;
    private final DeleteProgramaUseCase deleteProgramaUseCase;
    private final ProgramaJpaRepository programaJpaRepository;
    private final EpisodioJpaRepository episodioJpaRepository;
    private final MediaPathResolver mediaPathResolver;

    private final File capaCacheDir = new File("/tmp/programa-capa-cache");

    public ProgramaApiController(final CreateProgramaUseCase createProgramaUseCase,
                                 final ReadProgramaUseCase readProgramaUseCase,
                                 final ReadAllProgramaUseCase readAllProgramaUseCase,
                                 final UpdateProgramaUseCase updateProgramaUseCase,
                                 final PatchProgramaUseCase patchProgramaUseCase,
                                 final DeleteProgramaUseCase deleteProgramaUseCase,
                                 final ProgramaJpaRepository programaJpaRepository,
                                 final EpisodioJpaRepository episodioJpaRepository,
                                 final MediaPathResolver mediaPathResolver) {

        this.createProgramaUseCase = createProgramaUseCase;
        this.readProgramaUseCase = readProgramaUseCase;
        this.readAllProgramaUseCase = readAllProgramaUseCase;
        this.updateProgramaUseCase = updateProgramaUseCase;
        this.patchProgramaUseCase = patchProgramaUseCase;
        this.deleteProgramaUseCase = deleteProgramaUseCase;
        this.programaJpaRepository = programaJpaRepository;
        this.episodioJpaRepository = episodioJpaRepository;
        this.mediaPathResolver = mediaPathResolver;
        this.capaCacheDir.mkdirs();
    }

    @PostMapping
    public ResponseEntity<?> createPrograma(@RequestBody CreateProgramaCommand aInput) {

        return this.createProgramaUseCase.execute(aInput)
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<?> readProgramaById(@PathVariable("id") Long aId) {

        return this.readProgramaUseCase.execute(ReadProgramaCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> readProgramaByUuid(@PathVariable("uuid") String aUuid) {

        return this.readProgramaUseCase.execute(ReadProgramaCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<?> readAllPrograma(@RequestParam(required = false) String search,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size,
                                             @RequestParam(defaultValue = "id") String sort,
                                             @RequestParam(defaultValue = "asc") String direction) {

        return this.readAllProgramaUseCase.execute(new ReadAllProgramaCommand(
                        new ProgramaSearchQuery(search, page, size, sort, direction)))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<?> updateProgramaById(@PathVariable("id") Long aId,
                                                @RequestBody UpdateProgramaCommand aInput) {

        return this.updateProgramaUseCase.execute(UpdateProgramaCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> updateProgramaByUuid(
            @PathVariable("uuid") String aUuid,
            @RequestBody UpdateProgramaCommand aInput) {

        return this.updateProgramaUseCase.execute(UpdateProgramaCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/id/{id}")
    public ResponseEntity<?> patchProgramaById(@PathVariable("id") Long aId,
                                               @RequestBody PatchProgramaCommand aInput) {

        return this.patchProgramaUseCase.execute(PatchProgramaCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> patchProgramaByUuid(@PathVariable("uuid") String aUuid,
                                                 @RequestBody PatchProgramaCommand aInput) {

        return this.patchProgramaUseCase.execute(PatchProgramaCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> deleteProgramaById(@PathVariable("id") Long aId) {

        return this.deleteProgramaUseCase.execute(DeleteProgramaCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> deleteProgramaByUuid(@PathVariable("uuid") String aUuid) {

        return this.deleteProgramaUseCase.execute(DeleteProgramaCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/{id}/capa")
    public ResponseEntity<byte[]> getCapa(@PathVariable("id") Long aId) {

        final var opt = this.programaJpaRepository.findById(aId);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        final var entity = opt.get();
        final String capaUrl = entity.getCapaUrl();
        if (capaUrl == null || capaUrl.isBlank()) {
            return serveFirstEpisodeCapa(aId);
        }

        final File cacheFile = new File(capaCacheDir, aId + ".jpg");

        if (cacheFile.exists() && cacheFile.length() > 0) {
            return serveFile(cacheFile);
        }

        try {
            final var httpClient = HttpClient.newHttpClient();
            final var request = HttpRequest.newBuilder()
                    .uri(URI.create(capaUrl))
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                    .header("Accept", "image/webp,image/apng,image/*,*/*;q=0.8")
                    .header("Referer", "https://myanimelist.net/")
                    .GET()
                    .build();

            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofByteArray());

            if (response.statusCode() == 200 && response.body().length > 0) {
                java.nio.file.Files.write(cacheFile.toPath(), response.body());
                return serveFile(cacheFile);
            }
        } catch (Exception ignored) {
        }

        return serveFirstEpisodeCapa(aId);
    }

    private ResponseEntity<byte[]> serveFirstEpisodeCapa(Long programaId) {
        final var eps = this.episodioJpaRepository.findFirstByProgramaIds(java.util.List.of(programaId));
        if (eps.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        final var ep = eps.get(0);
        final String capaUrl = ep.getCapaUrl();

        if (capaUrl != null && !capaUrl.isBlank()) {
            final String resolved = this.mediaPathResolver.resolve(capaUrl);
            final File file = new File(resolved);
            if (file.exists()) {
                return serveFile(file);
            }
        }

        if (ep.getArquivo() != null && ep.getArquivo().getCaminho() != null) {
            final String caminho = ep.getArquivo().getCaminho();
            final String derived = caminho.replaceFirst("\\.(mp4|mkv|avi|flv|mov|wmv|webm)$", "_imagem.jpg");
            final String resolved = this.mediaPathResolver.resolve(derived);
            final File file = new File(resolved);
            if (file.exists()) {
                return serveFile(file);
            }

            final File dir = file.getParentFile();
            if (dir != null && dir.isDirectory()) {
                final String fname = file.getName();
                final var m = java.util.regex.Pattern.compile("^(\\d+)_").matcher(fname);
                if (m.find()) {
                    final String numPrefix = m.group(1);
                    final File[] matches = dir.listFiles((d, n) ->
                            n.startsWith(numPrefix + "_") && n.endsWith("_imagem.jpg"));
                    if (matches != null && matches.length > 0) {
                        return serveFile(matches[0]);
                    }
                }
            }
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<byte[]> serveFile(final File file) {
        try {
            final byte[] bytes = java.nio.file.Files.readAllBytes(file.toPath());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentLength(bytes.length)
                    .body(bytes);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
