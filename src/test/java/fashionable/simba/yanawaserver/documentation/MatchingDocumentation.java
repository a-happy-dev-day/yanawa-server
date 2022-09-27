package fashionable.simba.yanawaserver.documentation;

import fashionable.simba.yanawaserver.matching.application.MatchingApplicationService;
import fashionable.simba.yanawaserver.matching.application.RecruitmentResponse;
import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Level;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class MatchingDocumentation extends Documentation {

    @MockBean
    MatchingApplicationService matchingApplicationService;

    /**
     * 매칭 생성
     */
    @Test
    void createMatching() {
        Map<String, String> params = new HashMap<>();
        params.put("courtId", "1");
        params.put("hostId", "1");
        params.put("date", "2022-09-25");
        params.put("startTime", "09:30");
        params.put("endTime", "11:30");
        params.put("maximumLevel", "0.5");
        params.put("minimumLevel", "2.5");
        params.put("ageOfRecruitment", "TWENTIES");
        params.put("sexOfRecruitment", "NONE");
        params.put("preferenceGame", "RALLY");
        params.put("numberOfRecruitment", "12");
        params.put("costOfCourtPerPerson", "3000");
        params.put("annual", "TWO_YEARS_LESS");
        params.put("details", "랠리하실 분 구합니다~");

        when(matchingApplicationService.createMatchingAndRecruitment(any()))
            .thenReturn(
                new RecruitmentResponse(
                    2L,
                    3L,
                    1L,
                    12L,
                    LocalDate.of(2022, 9, 25),
                    LocalTime.of(9, 30),
                    LocalTime.of(11, 30),
                    MatchingStatusType.WAITING,
                    new Level(0.5),
                    new Level(2.5),
                    AgeGroupType.TWENTIES,
                    GenderType.NONE,
                    PreferenceType.RALLY,
                    12,
                    3000.0,
                    AnnualType.TWO_YEARS_LESS,
                    "랠리하실 분 구합니다~",
                    RecruitmentStatusType.OPENING)
            );

        givenOauth()
            .filter(document("matching/create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .body(params)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/matchings")
            .then().log().all()
            .statusCode(HttpStatus.OK.value())
            .extract();
    }

    @Test
    void findList() {


        givenOauth()
            .filter(document("matching/findList",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/matchings")
            .then().log().all()
            .extract();
    }

    @Test
    void findOne() {
        givenOauth()
            .filter(document("matching/findOne",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/matchings/{matchingId}", 2)
            .then().log().all()
            .extract();
    }
}
