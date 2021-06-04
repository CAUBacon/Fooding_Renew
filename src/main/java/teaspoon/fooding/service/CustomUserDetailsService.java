package teaspoon.fooding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teaspoon.fooding.api.advice.exception.CAuthEmailNotFoundException;
import teaspoon.fooding.repository.UserRepository;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CustomUserDetailsService {
    private final UserRepository userRepository;

    public UserDetails findById(Long id) {
        return userRepository.findUserWithRolesById(id).orElseThrow(CAuthEmailNotFoundException::new);
    }
}
