package practice.three;

public record Owner(String idCard, String fullName, String email) {
    public Owner {
        if (idCard == null || !idCard.matches("\\d{12}")) throw new IllegalArgumentException("IdCard must be 12 numbers");
        if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("Full name must not be blank");
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[\\w]+$")) throw new IllegalArgumentException("Email wrong format");
    }
}
