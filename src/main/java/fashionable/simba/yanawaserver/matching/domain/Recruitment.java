package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.error.InvalidCostException;
import fashionable.simba.yanawaserver.matching.error.InvalidNumberException;
import fashionable.simba.yanawaserver.matching.error.LevelSettingException;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long matchingId;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "level", column = @Column(name = "MAXIMUM_LEVEL"))})
    private Level maximumLevel;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "level", column = @Column(name = "MINIMUM_LEVEL"))})
    private Level minimumLevel;
    private AgeGroupType ageOfRecruitment;
    private GenderType sexOfRecruitment;
    private PreferenceType preferenceGame;
    private Integer numberOfRecruitment;
    private Double costOfCourtPerPerson;
    private AnnualType annual;
    private String details;
    private RecruitmentStatusType status;

    protected Recruitment() {
        /*no-op*/
    }

    public Long getId() {
        return id;
    }

    public Long getMatchingId() {
        return matchingId;
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

    public RecruitmentStatusType getStatus() {
        return status;
    }

    public void changeClosed() {
        if (!this.status.equals(RecruitmentStatusType.OPENING)) {
            throw new IllegalArgumentException();
        }
        this.status = RecruitmentStatusType.CLOSED;
    }

    public boolean isClosed() {
        return this.status.equals(RecruitmentStatusType.CLOSED);
    }

    public Recruitment(Long id, Long matchingId, Level maximumLevel, Level minimumLevel, AgeGroupType ageOfRecruitment, GenderType sexOfRecruitment, PreferenceType preferenceGame, Integer numberOfRecruitment, Double costOfCourtPerPerson, AnnualType annual, String details, RecruitmentStatusType status) {
        if (numberOfRecruitment < 1 || numberOfRecruitment > 8) {
            throw new InvalidNumberException("인원은 1명이상 8명이하여야 합니다.");
        }
        if (costOfCourtPerPerson <= 0) {
            throw new InvalidCostException("비용은 0원 이상이여야 합니다.");
        }
        if (maximumLevel.getLevel() < minimumLevel.getLevel()) {
            throw new LevelSettingException("최소레벨이 최대레벨보다 클 수 없습니다.");
        }
        this.id = id;
        this.matchingId = matchingId;
        this.maximumLevel = maximumLevel;
        this.minimumLevel = minimumLevel;
        this.ageOfRecruitment = ageOfRecruitment;
        this.sexOfRecruitment = sexOfRecruitment;
        this.preferenceGame = preferenceGame;
        this.numberOfRecruitment = numberOfRecruitment;
        this.costOfCourtPerPerson = costOfCourtPerPerson;
        this.annual = annual;
        this.details = details;
        this.status = status;
    }

    public Recruitment(Long matchingId, Level maximumLevel, Level minimumLevel, AgeGroupType ageOfRecruitment, GenderType sexOfRecruitment, PreferenceType preferenceGame, Integer numberOfRecruitment, Double costOfCourtPerPerson, AnnualType annual, String details, RecruitmentStatusType status) {
        if (numberOfRecruitment < 1 || numberOfRecruitment > 8) {
            throw new InvalidNumberException("인원은 1명이상 8명이하여야 합니다.");
        }
        if (costOfCourtPerPerson <= 0) {
            throw new InvalidCostException("비용은 0원 이상이여야 합니다.");
        }
        if (maximumLevel.getLevel() < minimumLevel.getLevel()) {
            throw new LevelSettingException("최소레벨이 최대레벨보다 클 수 없습니다.");
        }
        this.matchingId = matchingId;
        this.maximumLevel = maximumLevel;
        this.minimumLevel = minimumLevel;
        this.ageOfRecruitment = ageOfRecruitment;
        this.sexOfRecruitment = sexOfRecruitment;
        this.preferenceGame = preferenceGame;
        this.numberOfRecruitment = numberOfRecruitment;
        this.costOfCourtPerPerson = costOfCourtPerPerson;
        this.annual = annual;
        this.details = details;
        this.status = status;
    }
}
