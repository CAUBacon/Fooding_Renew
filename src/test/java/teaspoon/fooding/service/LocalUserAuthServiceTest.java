package teaspoon.fooding.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import teaspoon.fooding.api.advice.exception.CEmailDuplicatedException;
import teaspoon.fooding.api.advice.exception.CNicknameDuplicatedException;
import teaspoon.fooding.api.dto.LocalUserSignUpDto;
import teaspoon.fooding.domain.user.Gender;
import teaspoon.fooding.domain.user.LocalUser;
import teaspoon.fooding.domain.user.UserRole;
import teaspoon.fooding.repository.LocalUserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
@Transactional
class LocalUserAuthServiceTest {
    @Autowired
    LocalUserAuthService localUserAuthService;
    @Autowired
    LocalUserRepository localUserRepository;

    @Test
    void signUp_이미_같은_이메일로_가입한_사용자가_존재하면_CEmailDuplicatedException를_던진다() {
        // given
        LocalUserSignUpDto userDto = new LocalUserSignUpDto("test@naver.com", "1234", "nick", Gender.FEMALE);
        // when
        localUserRepository.save(new LocalUser("name", Gender.MALE, "test@naver.com", "asdf"));
        Throwable throwable = catchThrowable(() -> localUserAuthService.signUp(userDto));
        // then
        assertThat(throwable).isInstanceOf(CEmailDuplicatedException.class);
    }

    @Test
    void signUp_이미_같은_닉네임으로_가입한_사용자가_존재하면_CNicknameDuplicatedException를_던진다() {
        // given
        LocalUserSignUpDto userDto = new LocalUserSignUpDto("test@naver.com", "1234", "name", Gender.FEMALE);
        // when
        localUserRepository.save(new LocalUser("name", Gender.MALE, "another@naver.com", "asdf"));
        Throwable throwable = catchThrowable(() -> localUserAuthService.signUp(userDto));
        // then
        assertThat(throwable).isInstanceOf(CNicknameDuplicatedException.class);
    }

    @Test
    void signUp_전달받은_사용자를_저장하고_반환한다() {
        // given
        LocalUserSignUpDto userDto = new LocalUserSignUpDto("test@naver.com", "1234", "nick", Gender.FEMALE);
        // when
        LocalUser result = localUserAuthService.signUp(userDto);
        // then
        assertThat(result.getEmail()).isEqualTo(userDto.getEmail());
        assertThat(result.getGender()).isEqualTo(userDto.getGender());
        assertThat(result.getNickname()).isEqualTo(userDto.getNickname());
    }

    @Test
    void signUp_비밀번호는_평문으로_저장하지_않는다() {
        // given
        LocalUserSignUpDto userDto = new LocalUserSignUpDto("test@naver.com", "1234", "nick", Gender.FEMALE);
        // when
        LocalUser result = localUserAuthService.signUp(userDto);
        // then
        assertThat(result.getPassword()).isNotEqualTo(userDto.getPassword());
    }

    @Test
    void signUp_COMMON_권한을_부여한다() {
        // given
        LocalUserSignUpDto userDto = new LocalUserSignUpDto("test@naver.com", "1234", "nick", Gender.FEMALE);
        // when
        LocalUser result = localUserAuthService.signUp(userDto);
        // then
        assertThat(result.getRoles()).containsExactly(UserRole.COMMON);
    }
}
