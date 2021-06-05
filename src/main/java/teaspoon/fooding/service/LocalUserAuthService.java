package teaspoon.fooding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teaspoon.fooding.api.advice.exception.CAuthEmailNotFoundException;
import teaspoon.fooding.api.advice.exception.CEmailDuplicatedException;
import teaspoon.fooding.api.advice.exception.CPasswordNotMatchException;
import teaspoon.fooding.api.dto.LocalUserSignInDto;
import teaspoon.fooding.api.dto.LocalUserSignUpDto;
import teaspoon.fooding.domain.user.LocalUser;
import teaspoon.fooding.domain.user.UserRole;
import teaspoon.fooding.repository.LocalUserRepository;
import teaspoon.fooding.repository.UserRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LocalUserAuthService {
    private final LocalUserRepository localUserRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public LocalUser signUp(LocalUserSignUpDto dto) {
        LocalUser localUser = dto.toUser();

        if (localUserRepository.existsByEmail(localUser.getEmail())) {
            throw new CEmailDuplicatedException();
        }

        localUser.encodePassword(passwordEncoder);
        localUser.assignRoles(UserRole.COMMON);
        localUserRepository.save(localUser);

        return localUser;
    }

    public LocalUser signIn(LocalUserSignInDto dto) {
        LocalUser user = localUserRepository.findByEmail(dto.getEmail()).orElseThrow(CAuthEmailNotFoundException::new);
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new CPasswordNotMatchException();
        }
        return user;
    }
}
