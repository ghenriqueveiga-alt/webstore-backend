package com.hvs.webstore.back.app.output.webstore.video;

import com.hvs.webstore.back.domain.entity.webstore.video.Video;
import com.hvs.webstore.back.domain.pagination.Pagination;

import java.util.ArrayList;
import java.util.List;

public record ReadAllVideoOutput(int aCurrentPage,
                                 long aPerPage,
                                 long aTotal,
                                 List<ReadVideoOutput> aVideos) {

    public static ReadAllVideoOutput from(Pagination<Video> aPagination) {

        var list = new ArrayList<ReadVideoOutput>();

        for (var i : aPagination.aContent())
            list.add(ReadVideoOutput.from(i));

        return new ReadAllVideoOutput(
                aPagination.aPageNumber(),
                aPagination.aTotalElements(),
                aPagination.aTotalPages(),
                list);
    }
}
