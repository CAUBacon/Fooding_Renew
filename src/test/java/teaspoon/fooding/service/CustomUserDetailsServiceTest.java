package teaspoon.fooding.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import teaspoon.fooding.api.advice.exception.CAuthEmailNotFoundException;
import teaspoon.fooding.domain.user.Gender;
import teaspoon.fooding.domain.user.LocalUser;
import teaspoon.fooding.repository.LocalUserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
class CustomUserDetailsServiceTest {

    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    LocalUserRepository localUserRepository;

    @Test
    void findById_유저가_없으면_CAuthEmailNotFoundException_던진다() {
        // given
        LocalUser user = new LocalUser("A", Gender.FEMALE, "abc@test.com", "1234");
        localUserRepository.save(user);
        // when
        Throwable throwable = catchThrowable(() -> customUserDetailsService.findById(user.getId() + 1));
        // then
        assertThat(throwable)
                .isInstanceOf(CAuthEmailNotFoundException.class);
    }

    @Test
    void findById_유저가_존재하면_해당_유저를_반환한다() {
        // given
        LocalUser user = new LocalUser("A", Gender.FEMALE, "abc@test.com", "1234");
        localUserRepository.save(user);
        // when
        UserDetails userDetails = customUserDetailsService.findById(user.getId());
        // then
        assertThat(userDetails.getUsername()).isEqualTo(user.getId().toString());
    }
}
