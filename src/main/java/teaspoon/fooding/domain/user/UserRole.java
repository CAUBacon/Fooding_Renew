package teaspoon.fooding.domain.user;

public enum UserRole {
    COMMON("ROLE_COMMON"), ADMIN("ROLE_ADMIN");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
