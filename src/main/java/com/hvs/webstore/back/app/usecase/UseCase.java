package com.hvs.webstore.back.app.usecase;

public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN aIn);
}
