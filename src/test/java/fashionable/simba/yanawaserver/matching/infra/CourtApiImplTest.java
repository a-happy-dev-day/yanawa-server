package fashionable.simba.yanawaserver.matching.infra;

import fashionable.simba.yanawaserver.court.domain.CourtRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourtApiImplTest {
    @Mock
    private CourtRepository courtRepository;
    @InjectMocks
    private CourtApiImpl courtApi;

    @Test
    void isCourtExist_false() {
        // given
        Long 코트장_ID = 1L;
        // when
        when(courtRepository.findById(코트장_ID)).thenReturn(Optional.empty());
        // then
        Assertions.assertThat(courtApi.isCourtExist(코트장_ID)).isFalse();
    }
}
