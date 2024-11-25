package java15.Enum;

public enum language {
    AMERICA("AMERICA"),
    FRENCH("FRENCH"),
    DENMARK("DENMARK"),
    GERMANY("GERMANY"),
    RUSSIA("RUSSIA"),
    ITALY("ITALY"),
    INDIA("INDIA"),
    KAZAKHSTAN("KAZAKHSTAN"),
    UZBEKISTAN("UZBEKISTAN"),
    ;

    private String value;

    language(String language) {
        this.value = language;
    }

    public String getValue() {
        return value;
    }
}
