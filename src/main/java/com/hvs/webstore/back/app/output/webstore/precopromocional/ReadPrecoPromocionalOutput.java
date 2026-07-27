package com.hvs.webstore.back.app.output.webstore.precopromocional;

import com.hvs.webstore.back.domain.entity.webstore.precopromocional.PrecoPromocional;
import java.time.Instant;

public record ReadPrecoPromocionalOutput(Long aId,
                                         String aUuid,
                                         String aStatusDesc,
                                         Long aProdutoId,
                                         Long aPrecoPromocional,
                                         Instant aDataInicio,
                                         Instant aDataFim) {

    public static ReadPrecoPromocionalOutput from(PrecoPromocional aPrecoPromocional) {

        return new ReadPrecoPromocionalOutput(
                aPrecoPromocional.getId().getValue(),
                aPrecoPromocional.getUuid().getValue(),
                aPrecoPromocional.getStatusCode().getDesc(),
                aPrecoPromocional.getProduto() != null ? aPrecoPromocional.getProduto().getId().getValue() : null,
                aPrecoPromocional.getPrecoPromocional() != null ? aPrecoPromocional.getPrecoPromocional().getId().getValue() : null,
                aPrecoPromocional.getDataInicio(),
                aPrecoPromocional.getDataFim());
    }

    public static ReadPrecoPromocionalOutput fromSimple(PrecoPromocional aPrecoPromocional) {

        return new ReadPrecoPromocionalOutput(
                aPrecoPromocional.getId().getValue(),
                aPrecoPromocional.getUuid().getValue(),
                null,
                null,
                null,
                null,
                null);
    }
}
