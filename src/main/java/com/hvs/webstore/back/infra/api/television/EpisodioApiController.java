package com.hvs.webstore.back.infra.api.television;

import com.hvs.webstore.back.app.command.television.episodio.*;
import com.hvs.webstore.back.app.output.television.episodio.ReadEpisodioCortesDetectadosOutput;
import com.hvs.webstore.back.app.output.television.episodio.ReadEpisodioOutput;
import com.hvs.webstore.back.app.service.MediaPathResolver;
import com.hvs.webstore.back.app.usecase.television.episodio.*;
import com.hvs.webstore.back.infra.persistence.television.episodio.EpisodioJpaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/api/v1/episodio")
@Tag(name = "Episódios", description = "Operações de CRUD, cortes de tempo e detecção de comerciais dos episódios")
public class EpisodioApiController {

private final CreateEpisodioUseCase createEpisodioUseCase;
    private final ReadEpisodioUseCase readEpisodioUseCase;
    private final ReadEpisodioCortesTempoUseCase readEpisodioCortesTempoUseCase;
    private final ReadEpisodioCortesDetectadosUseCase readEpisodioCortesDetectadosUseCase;
    private final ReadAllEpisodioUseCase readAllEpisodioUseCase;
    private final UpdateEpisodioUseCase updateEpisodioUseCase;
    private final PatchEpisodioUseCase patchEpisodioUseCase;
    private final DeleteEpisodioUseCase deleteEpisodioUseCase;
    private final MediaPathResolver mediaPathResolver;
    private final EpisodioJpaRepository episodioJpaRepository;

    public EpisodioApiController(
            final CreateEpisodioUseCase createEpisodioUseCase,
            final ReadEpisodioUseCase readEpisodioUseCase,
            final ReadEpisodioCortesTempoUseCase readEpisodioCortesTempoUseCase,
            final ReadEpisodioCortesDetectadosUseCase readEpisodioCortesDetectadosUseCase,
            final ReadAllEpisodioUseCase readAllEpisodioUseCase,
            final UpdateEpisodioUseCase updateEpisodioUseCase,
            final PatchEpisodioUseCase patchEpisodioUseCase,
            final DeleteEpisodioUseCase deleteEpisodioUseCase,
            final MediaPathResolver mediaPathResolver,
            final EpisodioJpaRepository episodioJpaRepository) {
        this.createEpisodioUseCase = createEpisodioUseCase;
        this.readEpisodioUseCase = readEpisodioUseCase;
        this.readEpisodioCortesTempoUseCase = readEpisodioCortesTempoUseCase;
        this.readEpisodioCortesDetectadosUseCase = readEpisodioCortesDetectadosUseCase;
        this.readAllEpisodioUseCase = readAllEpisodioUseCase;
        this.updateEpisodioUseCase = updateEpisodioUseCase;
        this.patchEpisodioUseCase = patchEpisodioUseCase;
        this.deleteEpisodioUseCase = deleteEpisodioUseCase;
        this.mediaPathResolver = mediaPathResolver;
        this.episodioJpaRepository = episodioJpaRepository;
    }

    @PostMapping
    public ResponseEntity<?> createEpisodio(
            @RequestBody CreateEpisodioCommand aInput) {

        return this.createEpisodioUseCase.execute(aInput)
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/id/{id}")
    @Operation(summary = "Busca episódio por id",
            responses = @ApiResponse(responseCode = "200", description = "Episódio encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReadEpisodioOutput.class))))
    public ResponseEntity<?> readEpisodioById(
            @PathVariable("id") Long aId) {

        return this.readEpisodioUseCase.execute(ReadEpisodioCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/uuid/{uuid}")
    @Operation(summary = "Busca episódio por uuid",
            responses = @ApiResponse(responseCode = "200", description = "Episódio encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReadEpisodioOutput.class))))
    public ResponseEntity<?> readEpisodioByUuid(
            @PathVariable("uuid") String aUuid) {

        return this.readEpisodioUseCase.execute(ReadEpisodioCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/{id}/cortes-tempo")
    public ResponseEntity<?> readEpisodioCortesTempo(
            @PathVariable("id") Long aId) {

        return this.readEpisodioCortesTempoUseCase.execute(ReadEpisodioCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/{id}/cortes-detectados")
    @Operation(summary = "Cortes de tempo e detecções de um episódio",
            description = "Retorna os cortes mapeados do episódio junto com as detecções automáticas (silêncios/possíveis comerciais) e o campo processado. Não executa detecção nova.",
            responses = @ApiResponse(responseCode = "200", description = "Cortes e detecções do episódio",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReadEpisodioCortesDetectadosOutput.class))))
    public ResponseEntity<?> readEpisodioCortesDetectados(
            @PathVariable("id") Long aId) {

        return this.readEpisodioCortesDetectadosUseCase.execute(ReadEpisodioCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping
    @Operation(summary = "Lista episódios paginados",
            responses = @ApiResponse(responseCode = "200", description = "Lista paginada de episódios",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = com.hvs.webstore.back.app.output.television.episodio.ReadAllEpisodioOutput.class))))
    public ResponseEntity<?> readAllEpisodio(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long programaId,
            @RequestParam(required = false) String programaIds,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        final java.util.List<Long> ids = (programaIds != null && !programaIds.isBlank())
                ? java.util.Arrays.stream(programaIds.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Long::parseLong)
                    .toList()
                : null;

        return this.readAllEpisodioUseCase.execute(new ReadAllEpisodioCommand(
                        new EpisodioSearchQuery(search, programaId, ids, page, size, sort, direction)))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/primeiro-por-programa")
    public ResponseEntity<?> readFirstEpisodioByProgramaIds(
            @RequestParam String programaIds) {

        final java.util.List<Long> ids = java.util.Arrays.stream(programaIds.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .toList();

        final var entities = this.episodioJpaRepository.findFirstByProgramaIds(ids);
        final var result = entities.stream()
                .map(e -> {
                    final var d = e.toDomainChildren();
                    return ReadEpisodioOutput.from(d);
                })
                .toList();

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/primeiros-por-programa")
    public ResponseEntity<?> readPrimeirosEpisodiosByProgramaIds(
            @RequestParam String programaIds,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "1") int limite) {

        final java.util.List<Long> ids = java.util.Arrays.stream(programaIds.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .toList();

        final var rows = this.episodioJpaRepository.findPrimeirosPorProgramaIds(ids, offset, limite);
        final var result = rows.stream()
                .map(row -> {
                    java.util.Map<String, Object> m = new java.util.HashMap<>();
                    m.put("aId", ((Number) row[0]).longValue());
                    m.put("aNumero", row[1] != null ? ((Number) row[1]).longValue() : null);
                    m.put("aTitulo", row[2] != null ? row[2].toString() : null);
                    m.put("aProgramaId", ((Number) row[3]).longValue());
                    m.put("aTemporada", row[4] != null ? ((Number) row[4]).longValue() : null);
                    m.put("aParte", row[5] != null ? ((Number) row[5]).longValue() : 0L);
                    m.put("aDuracao", row[6] != null ? row[6].toString() : null);
                    m.put("aCapaUrl", row[7] != null ? row[7].toString() : null);
                    return m;
                })
                .toList();

        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<?> updateEpisodioById(
            @PathVariable("id") Long aId,
            @RequestBody UpdateEpisodioCommand aInput) {

        return this.updateEpisodioUseCase.execute(UpdateEpisodioCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PutMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> updateEpisodioByUuid(
            @PathVariable("uuid") String aUuid,
            @RequestBody UpdateEpisodioCommand aInput) {

        return this.updateEpisodioUseCase.execute(UpdateEpisodioCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/id/{id}")
    public ResponseEntity<?> patchEpisodioById(
            @PathVariable("id") Long aId,
            @RequestBody PatchEpisodioCommand aInput) {

        return this.patchEpisodioUseCase.execute(PatchEpisodioCommand.from(aId, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @PatchMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> patchEpisodioByUuid(
            @PathVariable("uuid") String aUuid,
            @RequestBody PatchEpisodioCommand aInput) {

        return this.patchEpisodioUseCase.execute(PatchEpisodioCommand.from(aUuid, aInput))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<?> deleteEpisodioById(
            @PathVariable("id") Long aId) {

        return this.deleteEpisodioUseCase.execute(DeleteEpisodioCommand.from(aId))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @DeleteMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> deleteCorteByUuid(
            @PathVariable("uuid") String aUuid) {

        return this.deleteEpisodioUseCase.execute(DeleteEpisodioCommand.from(aUuid))
                .fold(error -> new ResponseEntity<>(error, HttpStatus.CONFLICT),
                        success -> new ResponseEntity<>(success, HttpStatus.OK));
    }

    @GetMapping(value = "/{id}/capa")
    public ResponseEntity<byte[]> getCapa(
            @PathVariable("id") Long aId) {

        final var episodioOpt = this.episodioJpaRepository.findById(aId);
        if (episodioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        final var episodio = episodioOpt.get();
        final var capaUrl = episodio.getCapaUrl();

        if (capaUrl != null && !capaUrl.isBlank()) {
            final String resolved = this.mediaPathResolver.resolve(capaUrl);
            final File file = new File(resolved);
            if (file.exists()) {
                return serveImage(file, capaUrl);
            }
        }

        if (episodio.getArquivo() != null && episodio.getArquivo().getCaminho() != null) {
            final String caminho = episodio.getArquivo().getCaminho();
            final String derived = caminho.replaceFirst("\\.(mp4|mkv|avi|flv|mov|wmv|webm)$", "_imagem.jpg");
            final String resolved = this.mediaPathResolver.resolve(derived);
            final File file = new File(resolved);
            if (file.exists()) {
                return serveImage(file, derived);
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
                        return serveImage(matches[0], matches[0].getName());
                    }
                }
            }
        }

        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<byte[]> serveImage(final File file, final String name) {
        try {
            final byte[] bytes = java.nio.file.Files.readAllBytes(file.toPath());
            final String lower = name.toLowerCase();
            final MediaType mediaType = lower.endsWith(".png")
                    ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .contentLength(bytes.length)
                    .body(bytes);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}