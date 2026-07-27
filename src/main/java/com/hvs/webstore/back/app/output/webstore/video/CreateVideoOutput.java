package com.hvs.webstore.back.app.output.webstore.video;

import com.hvs.webstore.back.domain.entity.webstore.video.Video;

public record CreateVideoOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static CreateVideoOutput from(Video aVideo) {

        return new CreateVideoOutput(
                aVideo.getId().getValue(),
                aVideo.getUuid().getValue(),
                "The Video with id: " + aVideo.getUuid().getValue() + " has been successfully created.");
    }
}
