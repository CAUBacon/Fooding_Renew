package teaspoon.fooding.domain.user;

public enum UserRole {
    COMMON(ROLE.COMMON), ADMIN(ROLE.ADMIN);

    public static class ROLE {
        public static final String COMMON = "ROLE_COMMON";
        public static final String ADMIN = "ROLE_ADMIN";
    }

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
