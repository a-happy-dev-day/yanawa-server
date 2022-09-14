package fashionable.simba.yanawaserver.matching.application;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.domain.Level;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchingResponse {
    private Long recruitmentId;
    private Long matchingId;
    private Long courtId;
    private Long hostId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private MatchingStatusType matchingStatus;
    private Level maximumLevel;
    private Level minimumLevel;
    private AgeGroupType ageOfRecruitment;
    private GenderType sexOfRecruitment;
    private PreferenceType preferenceGame;
    private Integer numberOfRecruitment;
    private Double costOfCourtPerPerson;
    private AnnualType annual;
    private String details;
    private RecruitmentStatusType recruitmentStatus;

    public Long getMatchingId() {
        return matchingId;
    }

    public Long getRecruitmentId() {
        return recruitmentId;
    }

    public Long getCourtId() {
        return courtId;
    }

    public Long getHostId() {
        return hostId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public MatchingStatusType getMatchingStatus() {
        return matchingStatus;
    }

    public Level getMaximumLevel() {
        return maximumLevel;
    }

    public Level getMinimumLevel() {
        return minimumLevel;
    }

    public AgeGroupType getAgeOfRecruitment() {
        return ageOfRecruitment;
    }

    public GenderType getSexOfRecruitment() {
        return sexOfRecruitment;
    }

    public PreferenceType getPreferenceGame() {
        return preferenceGame;
    }

    public Integer getNumberOfRecruitment() {
        return numberOfRecruitment;
    }

    public Double getCostOfCourtPerPerson() {
        return costOfCourtPerPerson;
    }

    public AnnualType getAnnual() {
        return annual;
    }

    public String getDetails() {
        return details;
    }

    public RecruitmentStatusType getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public MatchingResponse(Long recruitmentId, Long matchingId, Long courtId, Long hostId, LocalDate date, LocalTime startTime, LocalTime endTime, MatchingStatusType matchingStatus, Level maximumLevel, Level minimumLevel, AgeGroupType ageOfRecruitment, GenderType sexOfRecruitment, PreferenceType preferenceGame, Integer numberOfRecruitment, Double costOfCourtPerPerson, AnnualType annual, String details, RecruitmentStatusType recruitmentStatus) {
        this.recruitmentId = recruitmentId;
        this.matchingId = matchingId;
        this.courtId = courtId;
        this.hostId = hostId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.matchingStatus = matchingStatus;
        this.maximumLevel = maximumLevel;
        this.minimumLevel = minimumLevel;
        this.ageOfRecruitment = ageOfRecruitment;
        this.sexOfRecruitment = sexOfRecruitment;
        this.preferenceGame = preferenceGame;
        this.numberOfRecruitment = numberOfRecruitment;
        this.costOfCourtPerPerson = costOfCourtPerPerson;
        this.annual = annual;
        this.details = details;
        this.recruitmentStatus = recruitmentStatus;
    }
}
