package com.hvs.webstore.back.app.output.webstore.video;

import com.hvs.webstore.back.domain.entity.webstore.video.Video;

public record DeleteVideoOutput(Long aId,
                                String aUuid,
                                String aMessage) {

    public static DeleteVideoOutput from(Video aVideo) {

        return new DeleteVideoOutput(
                aVideo.getId().getValue(),
                aVideo.getUuid().getValue(),
                "The Video with id: " + aVideo.getUuid().getValue() + " has been successfully deleted.");
    }
}
