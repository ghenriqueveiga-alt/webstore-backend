package com.hvs.webstore.back.domain.entity.webstore.video;

import com.hvs.webstore.back.domain.validation.ValidationHandler;
import com.hvs.webstore.back.domain.validation.Validator;

public class VideoValidator extends Validator {

    private final Video video;

    public VideoValidator(ValidationHandler aHandler,
                          final Video video) {

        super(aHandler);
        this.video = video;
    }

    @Override
    public void validate() {

    }

    public Video getVideo() { return video; }
}