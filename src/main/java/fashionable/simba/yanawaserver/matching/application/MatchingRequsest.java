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

public class MatchingRequsest {
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

    public MatchingRequsest(Builder builder) {
        this.courtId = builder.courtId;
        this.hostId = builder.hostId;
        this.date = builder.date;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.matchingStatus = builder.matchingStatus;
        this.maximumLevel = builder.maximumLevel;
        this.minimumLevel = builder.minimumLevel;
        this.ageOfRecruitment = builder.ageOfRecruitment;
        this.sexOfRecruitment = builder.sexOfRecruitment;
        this.preferenceGame = builder.preferenceGame;
        this.numberOfRecruitment = builder.numberOfRecruitment;
        this.costOfCourtPerPerson = builder.costOfCourtPerPerson;
        this.annual = builder.annual;
        this.details = builder.details;
        this.recruitmentStatus = builder.recruitmentStatus;
    }

    public static class Builder {
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

        public Builder() {
        }

        public Builder courtId(Long courtId) {
            this.courtId = courtId;
            return this;
        }

        public Builder hostId(Long hostId) {
            this.hostId = hostId;
            return this;
        }

        public Builder date(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder startTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder endTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder matchingStatus(MatchingStatusType matchingStatus) {
            this.matchingStatus = matchingStatus;
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

        public Builder recruitmentStatus(RecruitmentStatusType recruitmentStatus) {
            this.recruitmentStatus = recruitmentStatus;
            return this;
        }

        public MatchingRequsest build() {
            return new MatchingRequsest(this);
        }
    }
}