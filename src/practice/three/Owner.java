package practice.three;

public record Owner(String idCard, String fullName, String email) {
    public Owner {
        if (!idCard.matches("\\d{12}")) throw new IllegalArgumentException("IdCard must be 12 numbers");
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[\\w]+$")) throw new IllegalArgumentException("Email wrong format");
    }
}
