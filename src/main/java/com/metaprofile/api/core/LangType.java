package com.metaprofile.api.core;

public class LangType {

    public static final LangType ru = new LangType("ru");
    public static final LangType en = new LangType("en");

    public static LangType raw(String value){
        return new LangType(value);
    }

    private final String raw;

    protected LangType(String raw){
        this.raw = raw;
    }

    public String getRaw() {
        return raw;
    }

    @Override
    public String toString() {
        return this.getRaw();
    }
}
