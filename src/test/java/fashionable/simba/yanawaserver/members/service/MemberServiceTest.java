package fashionable.simba.yanawaserver.members.service;

import fashionable.simba.yanawaserver.members.domain.DefaultMember;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import fashionable.simba.yanawaserver.members.domain.MemberSex;
import fashionable.simba.yanawaserver.members.domain.RoleType;
import fashionable.simba.yanawaserver.members.exception.NoMemberDateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {
    private static final DefaultMember 이미_정보를_등록한_사용자 = new DefaultMember("tis", 1L, "email", List.of(RoleType.ROLE_MEMBER.name()), false);
    private static final DefaultMember 처음_방문한_사용자 = new DefaultMember("tis", 1L, "email", List.of(RoleType.ROLE_MEMBER.name()), true);

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    MemberService memberService;

    @Test
    @DisplayName("사용자의 정보를 업데이트한다.")
    void updateMember() {
        when(memberRepository.findById(1L)).thenReturn(Optional.of(처음_방문한_사용자));

        assertDoesNotThrow(
            () -> memberService.updateMember(
                "1",
                "tis",
                MemberSex.MALE,
                LocalDate.of(1996, 9, 1),
                BigDecimal.ONE)
        );
    }

    @Test
    @DisplayName("사용자의 정보가 존재하지 않으면 NoMemberDateException 예외가 발생한다.")
    void updateMember_invalidUser() {
        when(memberRepository.findById(1L))
            .thenReturn(Optional.empty());

        assertThatThrownBy(
            () -> memberService.updateMember("1", "tis", MemberSex.MALE,
                LocalDate.of(1996, 9, 1), BigDecimal.ONE)
        ).isInstanceOf(NoMemberDateException.class);
    }

    @Test
    @DisplayName("이미 업데이트한 사용자이면 예외가 발생한다.")
    void updateMember_alreadyUpdatedUser() {
        when(memberRepository.findById(1L))
            .thenReturn(Optional.of(이미_정보를_등록한_사용자));

        assertThatThrownBy(
            () -> memberService.updateMember("1", "tis", MemberSex.MALE,
                LocalDate.of(1996, 9, 1), BigDecimal.ONE)
        ).isInstanceOf(AlreadyUpdatedException.class);
    }
}
