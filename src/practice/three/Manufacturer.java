package practice.three;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Manufacturer {
    HONDA("Honda"),
    YAMAHA("Yamaha"),
    TOYOTA("Toyota"),
    SUZUKI("Suzuki");

    private final String label;

    Manufacturer(String label) {
        this.label = label;
    }

    public static Manufacturer from(String text) {
        return Arrays.stream(values())
                .filter(m -> m.label.equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(text + " must be one of the following values: "
                        + Arrays.stream(values()).map(Manufacturer::toString).collect(Collectors.joining(", "))));
    }

    @Override
    public String toString() {
        return label;
    }
}
