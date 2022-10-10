package fashionable.simba.yanawaserver.court.domain;

import fashionable.simba.yanawaserver.court.exception.NoCourtDataException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourtServiceTest {

    @Mock
    private CourtRepository courtRepository;
    private CourtService courtService;

    @BeforeEach
    void setUp() {
        courtService = new CourtService(courtRepository);
    }

    @Test
    @DisplayName("코트장 리스트를 저장한다.")
    void test1() {
        // given
        List<Court> 코트장_리스트 = Arrays.asList(
            new Court(null, "성동구", "응봉공원", null),
            new Court(null, "양천구", "목동운동장>다목적구장", null)
        );

        // when
        when(courtRepository.saveAll(any())).thenReturn(코트장_리스트);
        courtService.saveCourts(코트장_리스트);

        // then
        verify(courtRepository, atLeast(1)).saveAll(any());
    }

    @Test
    @DisplayName("데이터 스토어에 저장이 실패되면 예외가 발생한다.")
    void test2() {
        // given
        List<Court> 코트장_리스트 = Arrays.asList(
            new Court(null, "성동구", "응봉공원", null),
            new Court(null, "양천구", "목동운동장>다목적구장", null)
        );

        // when
        when(courtRepository.saveAll(any())).thenThrow(IllegalArgumentException.class);

        // then
        Assertions.assertThatThrownBy(
            () -> courtService.saveCourts(코트장_리스트)
        ).isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("Params으로 코트장을 조회한다.")
    void test3() {
        // given
        List<Court> 코트장_리스트 = Arrays.asList(
            new Court(null, "성동구", "응봉공원", null),
            new Court(null, "양천구", "목동운동장>다목적구장", null)
        );
        String 코트_이름 = "court";

        // when
        when(courtRepository.findCourtByAreaNameContainingOrPlaceNameContainingOrderByAreaNameAsc(anyString(), anyString()))
            .thenReturn(코트장_리스트);
        courtService.findCourts(코트_이름);

        // then
        verify(courtRepository, atLeast(1))
            .findCourtByAreaNameContainingOrPlaceNameContainingOrderByAreaNameAsc(코트_이름, 코트_이름);
    }

    @Test
    @DisplayName("코트장 ID를 이용해 조회한다.")
    void test4() {
        // given
        Court 코트장 = new Court(null, "성동구", "응봉공원", null);
        long 코트장_ID = 2L;

        // when
        when(courtRepository.findById(any()))
            .thenReturn(Optional.of(코트장));
        courtService.findCourt(코트장_ID);

        // then
        verify(courtRepository, atLeast(1))
            .findById(코트장_ID);
    }


    @Test
    @DisplayName("코트장 ID에 데이터가 없으면 NoCourtDataException 예외가 발생한다.")
    void test5() {
        // given
        long 코트장_ID = 3L;

        // when
        when(courtRepository.findById(any()))
            .thenReturn(Optional.empty());

        // then
        Assertions.assertThatThrownBy(
            () -> courtService.findCourt(코트장_ID)
        ).isInstanceOf(NoCourtDataException.class);
    }

    @Test
    @DisplayName("코트장을 전체 조회한다.")
    void test6() {
        // given
        List<Court> 코트장_리스트 = Arrays.asList(
            new Court(null, "성동구", "응봉공원", null),
            new Court(null, "양천구", "목동운동장>다목적구장", null)
        );

        // when
        when(courtRepository.findAll()).thenReturn(코트장_리스트);
        courtService.findCourts();

        // then
        verify(courtRepository, atLeast(1)).findAll();
    }


    @Test
    @DisplayName("데이터가 하나 이상 존재하면 내부 데이터를 삭제하고 다시 저장한다.")
    void test7() {
        // given
        List<Court> 코트장_리스트 = Arrays.asList(
            new Court(null, "성동구", "응봉공원", null),
            new Court(null, "양천구", "목동운동장>다목적구장", null)
        );

        //when
        when(courtRepository.count()).thenReturn(3L);
        courtService.saveCourts(코트장_리스트);

        // then
        verify(courtRepository, atLeast(1)).deleteAllInBatch();
        verify(courtRepository, atLeast(1)).saveAll(코트장_리스트);
    }
}
