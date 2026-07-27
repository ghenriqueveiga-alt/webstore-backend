package com.hvs.webstore.back.app.output.webstore.anexo;

import com.hvs.webstore.back.domain.entity.webstore.anexo.Anexo;
import java.time.Instant;

public record ReadAnexoOutput(Long aId,
                              String aUuid,
                              String aStatusDesc,
                              String aEntidadeNome,
                              Long aEntidadeId,
                              String aNome,
                              String aTipo,
                              Long aTamanho,
                              String aUrl,
                              Instant aDataUpload) {

    public static ReadAnexoOutput from(Anexo aAnexo) {

        return new ReadAnexoOutput(
                aAnexo.getId().getValue(),
                aAnexo.getUuid().getValue(),
                aAnexo.getStatusCode().getDesc(),
                aAnexo.getEntidadeNome(),
                aAnexo.getEntidadeId(),
                aAnexo.getNome(),
                aAnexo.getTipo(),
                aAnexo.getTamanho(),
                aAnexo.getUrl(),
                aAnexo.getDataUpload());
    }

    public static ReadAnexoOutput fromSimple(Anexo aAnexo) {

        return new ReadAnexoOutput(
                aAnexo.getId().getValue(),
                aAnexo.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }
}
