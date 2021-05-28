package teaspoon.fooding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teaspoon.fooding.api.dto.LocalUserSignUpDto;
import teaspoon.fooding.domain.user.LocalUser;
import teaspoon.fooding.domain.user.UserRole;
import teaspoon.fooding.repository.LocalUserRepository;

@RequiredArgsConstructor
@Service
public class LocalUserAuthService {
    private final LocalUserRepository localUserRepository;

    public LocalUser signUp(LocalUserSignUpDto dto) {
        LocalUser localUser = dto.toUser();
        localUser.assignRoles(UserRole.COMMON);
        localUserRepository.save(localUser);
        return localUser;
    }
}
