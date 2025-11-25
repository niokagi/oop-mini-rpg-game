package utils;

public enum Language {
    EN("English"),
    ID("Bahasa Indonesia");

    private final String displayName;

    Language(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Language fromInt(int i) {
        switch (i) {
            case 1:
                return EN;
            case 2:
                return ID;
            default:
                return EN;
        }
    }
}