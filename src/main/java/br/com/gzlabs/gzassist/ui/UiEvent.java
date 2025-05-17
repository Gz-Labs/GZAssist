package br.com.gzlabs.gzassist.ui;

public final class UiEvent {

    public enum Type { LOADING, SUCCESS, ERROR }

    private final Type type;
    private final String message;

    private UiEvent(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public static UiEvent loading() {
        return new UiEvent(Type.LOADING, null);
    }

    public static UiEvent success(String message) {
        return new UiEvent(Type.SUCCESS, message);
    }

    public static UiEvent error(String message) {
        return new UiEvent(Type.ERROR, message);
    }

    public Type type() {
        return type;
    }

    public String message() {
        return message;
    }
}
