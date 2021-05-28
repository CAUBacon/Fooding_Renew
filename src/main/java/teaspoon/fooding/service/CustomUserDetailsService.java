package teaspoon.fooding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import teaspoon.fooding.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService {
    private final UserRepository userRepository;

    public UserDetails findById(Long id) {
        return userRepository.findById(id).get();
    }
}
