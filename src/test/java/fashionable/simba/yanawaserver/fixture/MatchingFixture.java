package fashionable.simba.yanawaserver.fixture;

import fashionable.simba.yanawaserver.matching.constant.*;
import fashionable.simba.yanawaserver.matching.domain.Level;
import fashionable.simba.yanawaserver.matching.domain.Matching;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchingFixture {
    public static final Long FIXTURE_COURT_ID = 1L;
    public static final Long FIXTURE_MATCHING_ID = 1L;
    public static final Long FIXTURE_HOST_ID = 1L;

    public static final LocalDate FIXTURE_DATE = LocalDate.of(2022, 7, 29);
    public static final LocalTime FIXTURE_START_TIME = LocalTime.of(19, 0);
    public static final LocalTime FIXTURE_END_TIME = LocalTime.of(21, 0);
    public static final AnnualType FIXTURE_ANNUAL_TYPE = AnnualType.TWO_YEARS_LESS;
    public static final AgeGroupType FIXTURE_AGE_GROUP_TYPE = AgeGroupType.TWENTIES;
    public static final GenderType FIXTURE_GENDER_TYPE = GenderType.MALE;
    public static final PreferenceType FIXTURE_PREFERENCE_TYPE = PreferenceType.MATCHING;
    public static final Integer FIXTURE_NUMBER_OF_RECRUITMENT = 4;
    public static final Double FIXTURE_COST_OF_COURT_PER_PERSON = 2.5;
    public static final MatchingStatusType FIXTURE_MATCHING_STATUS_TYPE = MatchingStatusType.OPENING;

    public static final Level FIXTURE_MAXIMUM_LEVEL = new Level(5.0);
    public static final Level FIXTURE_MINIMUM_LEVEL = new Level(2.5);
    public static final String FIXTURE_DETAILS = "안녕하세요 부산테니스장에서 매치하실분 3명 구합니다.";

    public static final Matching 모집중인_매칭 = new Matching.Builder()
            .id(FIXTURE_MATCHING_ID)
            .courtId(FIXTURE_COURT_ID)
            .hostId(FIXTURE_HOST_ID)
            .date(FIXTURE_DATE)
            .startTime(FIXTURE_START_TIME)
            .endTime(FIXTURE_END_TIME)
            .annual(FIXTURE_ANNUAL_TYPE)
            .maximumLevel(FIXTURE_MAXIMUM_LEVEL)
            .minimumLevel(FIXTURE_MINIMUM_LEVEL)
            .ageOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
            .sexOfRecruitment(FIXTURE_GENDER_TYPE)
            .preferenceGame(FIXTURE_PREFERENCE_TYPE)
            .numberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
            .costOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
            .details(FIXTURE_DETAILS)
            .status(FIXTURE_MATCHING_STATUS_TYPE)
            .build();

    public static final Matching 모집완료_매칭 = new Matching.Builder()
            .id(FIXTURE_MATCHING_ID)
            .courtId(FIXTURE_COURT_ID)
            .hostId(FIXTURE_HOST_ID)
            .date(FIXTURE_DATE)
            .startTime(FIXTURE_START_TIME)
            .endTime(FIXTURE_END_TIME)
            .annual(FIXTURE_ANNUAL_TYPE)
            .maximumLevel(FIXTURE_MAXIMUM_LEVEL)
            .minimumLevel(FIXTURE_MINIMUM_LEVEL)
            .ageOfRecruitment(FIXTURE_AGE_GROUP_TYPE)
            .sexOfRecruitment(FIXTURE_GENDER_TYPE)
            .preferenceGame(FIXTURE_PREFERENCE_TYPE)
            .numberOfRecruitment(FIXTURE_NUMBER_OF_RECRUITMENT)
            .costOfCourtPerPerson(FIXTURE_COST_OF_COURT_PER_PERSON)
            .details(FIXTURE_DETAILS)
            .status(MatchingStatusType.CLOSING)
            .build();
}
