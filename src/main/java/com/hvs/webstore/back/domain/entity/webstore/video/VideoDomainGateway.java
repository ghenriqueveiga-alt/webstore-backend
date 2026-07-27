package com.hvs.webstore.back.domain.entity.webstore.video;

import com.hvs.webstore.back.domain.pagination.Pagination;
import com.hvs.webstore.back.domain.pagination.SearchQuery;

import java.util.Optional;

public interface VideoDomainGateway {

    Video create(Video aVideo);

    Optional<Video> read(VideoId aId);

    Optional<Video> readByUuid(VideoUuid aUuid);

    Pagination<Video> readAll(SearchQuery aQuery);

    Video update(Video aVideo);

    Video patch(Video aVideo);

    void delete(Video aVideo);
}
