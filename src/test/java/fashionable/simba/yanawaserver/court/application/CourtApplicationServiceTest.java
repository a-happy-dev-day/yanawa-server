package fashionable.simba.yanawaserver.court.application;

import fashionable.simba.yanawaserver.court.domain.Court;
import fashionable.simba.yanawaserver.court.domain.CourtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourtApplicationServiceTest {
    private static final Court 코트장 = new Court(UUID.randomUUID(), "성동구", "응봉공원", null);

    private CourtApplicationService courtApplicationService;
    @Mock
    CourtFeignApiTranslator courtFeignApiTranslator;
    @Mock
    CourtService courtService;
    @Mock
    CourtFeignApi courtFeignApi;

    @BeforeEach
    void setUp() {
        courtApplicationService = new CourtApplicationService(courtFeignApiTranslator, courtService, courtFeignApi);
    }

    @Test
    @DisplayName("공공데이터 Open API에서 정보를 가져오고 도메인 서비스에게 데이터를 저장하도록 요청합니다.")
    void test1() {
        // given
        List<Court> 코트장_리스트 = List.of(코트장);

        // when
        when(courtFeignApiTranslator.isStatusOk(any())).thenReturn(true);
        when(courtFeignApiTranslator.getCourts(any())).thenReturn(코트장_리스트);
        courtApplicationService.saveCourts();

        //then
        verify(courtFeignApiTranslator).isStatusOk(any());
        verify(courtFeignApiTranslator).getCourts(any());
        verify(courtService).saveCourts(any());
    }

    @Test
    @DisplayName("공공데이터 Open API의 상태가 false이면 예외가 발생합니다.")
    void test2() {
        // when
        when(courtFeignApiTranslator.isStatusOk(any())).thenReturn(false);

        // then
        assertThatThrownBy(
            () -> courtApplicationService.saveCourts()
        ).isInstanceOf(IllegalStateException.class);

    }

    @Test
    @DisplayName("공공데이터 Open API에서 정보를 가져옵니다.")
    void test3() {
        List<Court> 코트장_리스트 = List.of(코트장);
        // when
        when(courtFeignApiTranslator.isStatusOk(any())).thenReturn(true);
        when(courtFeignApiTranslator.getCourts(any())).thenReturn(코트장_리스트);
        courtApplicationService.saveCourts();

        //then
        verify(courtFeignApiTranslator).getCourts(any());
    }

    @Test
    @DisplayName("도메인 서비스에게 데이터를 저장하도록 요청합니다.")
    void test5() {
        // given
        List<Court> 코트장_리스트 = List.of(코트장);

        // when
        when(courtFeignApiTranslator.isStatusOk(any())).thenReturn(true);
        when(courtFeignApiTranslator.getCourts(any())).thenReturn(코트장_리스트);
        courtApplicationService.saveCourts();

        //then
        verify(courtService).saveCourts(any());
    }

    @Test
    @DisplayName("ID 값을 가져와 데이터 스토어에서 데이터를 조회합니다.")
    void test6() {
        // given
        UUID 코트장_ID = UUID.randomUUID();

        // when
        when(courtService.findCourt(코트장_ID)).thenReturn(코트장);
        courtApplicationService.findCourt(코트장_ID);

        // then
        verify(courtService).findCourt(코트장_ID);
    }

    @Test
    @DisplayName("입력 값을 가져와 데이터 스토어에서 데이터를 조회합니다.")
    void test7() {
        // given
        String 지역 = "응봉공원";
        List<Court> 입력값 = List.of(코트장);

        // when
        when(courtService.findCourts(지역)).thenReturn(입력값);
        List<Court> 결과값 = courtApplicationService.findCourts(지역);

        // then
        verify(courtService).findCourts(지역);
        assertThat(결과값).hasSameSizeAs(입력값);
        입력값.iterator().forEachRemaining(
            court -> assertThat(결과값).containsExactly(court)
        );
    }

    @Test
    @DisplayName("입력 값은 지역과 이름으로 구성되어 있습니다.")
    void test8() {
        // given
        String 지역과이름 = "성동구 응봉공원";
        List<Court> 입력값 = List.of(코트장);

        // when
        when(courtService.findCourts(지역과이름)).thenReturn(입력값);
        List<Court> 결과값 = courtApplicationService.findCourts(지역과이름);

        // then
        verify(courtService).findCourts(지역과이름);
        assertThat(결과값).hasSameSizeAs(입력값);
        입력값.iterator().forEachRemaining(
            court -> assertThat(결과값).containsExactly(court)
        );
    }

    @Test
    @DisplayName("코트장 리스트를 가져옵니다.")
    void test9() {
        // given
        List<Court> 입력값 = List.of(코트장);

        // when
        when(courtService.findCourts()).thenReturn(입력값);
        List<Court> 결과값 = courtApplicationService.findCourts();

        // then
        assertThat(결과값).hasSameSizeAs(입력값);
        입력값.iterator().forEachRemaining(
            court -> assertThat(결과값).containsExactly(court)
        );
    }
}
