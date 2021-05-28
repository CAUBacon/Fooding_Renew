package teaspoon.fooding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teaspoon.fooding.api.dto.LocalUserSignInDto;
import teaspoon.fooding.api.dto.LocalUserSignUpDto;
import teaspoon.fooding.domain.user.LocalUser;
import teaspoon.fooding.domain.user.UserRole;
import teaspoon.fooding.repository.LocalUserRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LocalUserAuthService {
    private final LocalUserRepository localUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public LocalUser signUp(LocalUserSignUpDto dto) {
        LocalUser localUser = dto.toUser();
        localUser.encodePassword(passwordEncoder);
        localUser.assignRoles(UserRole.COMMON);
        localUserRepository.save(localUser);
        return localUser;
    }

    public LocalUser signIn(LocalUserSignInDto dto) {
        LocalUser user = localUserRepository.findByEmail(dto.getEmail()).orElseThrow();
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalStateException("Password Not match");
        }
        return user;
    }
}
