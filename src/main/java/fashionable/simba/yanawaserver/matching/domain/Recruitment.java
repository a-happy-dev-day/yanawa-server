package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.constant.GenderType;
import fashionable.simba.yanawaserver.matching.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.constant.RecruitmentStatusType;
import fashionable.simba.yanawaserver.matching.error.InvalidCostException;
import fashionable.simba.yanawaserver.matching.error.InvalidNumberException;
import fashionable.simba.yanawaserver.matching.error.LevelSettingException;

public class Recruitment {
    private Long id;
    private Long matchingId;
    private Level maximumLevel;
    private Level minimumLevel;
    private AgeGroupType ageOfRecruitment;
    private GenderType sexOfRecruitment;
    private PreferenceType preferenceGame;
    private Integer numberOfRecruitment;
    private Double costOfCourtPerPerson;
    private AnnualType annual;
    private String details;
    private RecruitmentStatusType status;

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

    public void changeClosed() {
        if (!this.status.equals(RecruitmentStatusType.OPENING)) {
            throw new IllegalArgumentException();
        }
        this.status = RecruitmentStatusType.CLOSED;
    }

    public void setStatus(RecruitmentStatusType status) {
        this.status = status;
    }

    public RecruitmentStatusType getStatus() {
        return status;
    }

    public Recruitment(Builder builder) {
        if (builder.numberOfRecruitment < 1 || builder.numberOfRecruitment > 8) {
            throw new InvalidNumberException("인원은 1명이상 8명이하여야 합니다.");
        }
        if (builder.costOfCourtPerPerson <= 0) {
            throw new InvalidCostException("비용은 0원 이상이여야 합니다.");
        }
        if (builder.maximumLevel.getLevel() < builder.minimumLevel.getLevel()) {
            throw new LevelSettingException("최소레벨이 최대레벨보다 클 수 없습니다.");
        }

        this.id = builder.id;
        this.matchingId = builder.matchingId;
        this.maximumLevel = builder.maximumLevel;
        this.minimumLevel = builder.minimumLevel;
        this.ageOfRecruitment = builder.ageOfRecruitment;
        this.sexOfRecruitment = builder.sexOfRecruitment;
        this.preferenceGame = builder.preferenceGame;
        this.numberOfRecruitment = builder.numberOfRecruitment;
        this.costOfCourtPerPerson = builder.costOfCourtPerPerson;
        this.annual = builder.annual;
        this.details = builder.details;
        this.status = builder.status;
    }

    public static class Builder {
        private Long id;
        private Long matchingId;
        private Level maximumLevel;
        private Level minimumLevel;
        private AgeGroupType ageOfRecruitment;
        private GenderType sexOfRecruitment;
        private PreferenceType preferenceGame;
        private Integer numberOfRecruitment;
        private Double costOfCourtPerPerson;
        private AnnualType annual;
        private String details;
        private RecruitmentStatusType status;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder matchingId(Long matchingId) {
            this.matchingId = matchingId;
            return this;
        }

        public Builder maximumLevel(Level maximumLevel) {
            this.maximumLevel = maximumLevel;
            return this;
        }

        public Builder minimumLevel(Level minimumLevel) {
            this.minimumLevel = minimumLevel;
            return this;
        }

        public Builder ageOfRecruitment(AgeGroupType ageOfRecruitment) {
            this.ageOfRecruitment = ageOfRecruitment;
            return this;
        }

        public Builder sexOfRecruitment(GenderType sexOfRecruitment) {
            this.sexOfRecruitment = sexOfRecruitment;
            return this;
        }

        public Builder preferenceGame(PreferenceType preferenceGame) {
            this.preferenceGame = preferenceGame;
            return this;
        }

        public Builder numberOfRecruitment(Integer numberOfRecruitment) {
            this.numberOfRecruitment = numberOfRecruitment;
            return this;
        }

        public Builder costOfCourtPerPerson(Double costOfCourtPerPerson) {
            this.costOfCourtPerPerson = costOfCourtPerPerson;
            return this;
        }

        public Builder annual(AnnualType annual) {
            this.annual = annual;
            return this;
        }

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder status(RecruitmentStatusType status) {
            this.status = status;
            return this;
        }

        public Recruitment build() {
            return new Recruitment(this);
        }
    }
}
