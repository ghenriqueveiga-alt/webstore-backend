package com.hvs.webstore.back.app.output.webstore.video;

import com.hvs.webstore.back.domain.entity.webstore.video.Video;

public record UpdateVideoOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static UpdateVideoOutput from(Video aVideo) {

        return new UpdateVideoOutput(
                aVideo.getId().getValue(),
                aVideo.getUuid().getValue(),
                "The Video with id: " + aVideo.getUuid().getValue() + " has been successfully updated.");
    }
}
