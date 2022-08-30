package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.*;
import fashionable.simba.yanawaserver.matching.error.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Matching {
    private Long matchingId;
    private Long courtId;
    private Long hostId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private AnnualType annual;
    private Level maximumLevel;
    private Level minimumLevel;
    private AgeGroupType ageOfRecruitment;
    private GenderType sexOfRecruitment;
    private PreferenceType preferenceGame;
    private Integer numberOfRecruitment;
    private Double costOfCourtPerPerson;
    private String details;
    private MatchingStatusType status;

    public Long getMatchingId() {
        return matchingId;
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

    public AnnualType getAnnual() {
        return annual;
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

    public String getDetails() {
        return details;
    }

    public MatchingStatusType getStatus() {
        return status;
    }

    public void setStatus(MatchingStatusType status) {
        this.status = status;
    }

    public Matching(Builder builder) {
        if (builder.matchingId == null) {
            throw new NoMatchingDataException();
        }
        if (builder.courtId == null) {
            throw new NoCourtDataException();
        }
        if (builder.startTime.isAfter(builder.endTime)) {
            throw new MatchingTimeException();
        }
        if (builder.numberOfRecruitment < 1 || builder.numberOfRecruitment > 8) {
            throw new InvalidNumberException();
        }
        if (builder.costOfCourtPerPerson <= 0) {
            throw new InvalidCostException();
        }
        if (builder.maximumLevel.getLevel() < builder.minimumLevel.getLevel()) {
            throw new IllegalArgumentException();
        }

        this.matchingId = builder.matchingId;
        this.courtId = builder.courtId;
        this.hostId = builder.hostId;
        this.date = builder.date;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.annual = builder.annual;
        this.maximumLevel = builder.maximumLevel;
        this.minimumLevel = builder.minimumLevel;
        this.ageOfRecruitment = builder.ageOfRecruitment;
        this.sexOfRecruitment = builder.sexOfRecruitment;
        this.preferenceGame = builder.preferenceGame;
        this.numberOfRecruitment = builder.numberOfRecruitment;
        this.costOfCourtPerPerson = builder.costOfCourtPerPerson;
        this.details = builder.details;
        this.status = builder.status;
    }

    public static class Builder {
        private Long matchingId;
        private Long courtId;
        private Long hostId;
        private LocalDate date;
        private LocalTime startTime;
        private LocalTime endTime;
        private AnnualType annual;
        private Level maximumLevel;
        private Level minimumLevel;
        private AgeGroupType ageOfRecruitment;
        private GenderType sexOfRecruitment;
        private PreferenceType preferenceGame;
        private Integer numberOfRecruitment;
        private Double costOfCourtPerPerson;
        private String details;
        private MatchingStatusType status;

        public Builder() {
        }

        public Builder setMatchingId(Long matchingId) {
            this.matchingId = matchingId;
            return this;
        }

        public Builder setCourtId(Long courtId) {
            this.courtId = courtId;
            return this;
        }

        public Builder setHostId(Long hostId) {
            this.hostId = hostId;
            return this;
        }

        public Builder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder setStartTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setEndTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder setAnnual(AnnualType annual) {
            this.annual = annual;
            return this;
        }

        public Builder setMaximumLevel(Level maximumLevel) {
            this.maximumLevel = maximumLevel;
            return this;
        }

        public Builder setMinimumLevel(Level minimumLevel) {
            this.minimumLevel = minimumLevel;
            return this;
        }

        public Builder setAgeOfRecruitment(AgeGroupType ageOfRecruitment) {
            this.ageOfRecruitment = ageOfRecruitment;
            return this;
        }

        public Builder setSexOfRecruitment(GenderType sexOfRecruitment) {
            this.sexOfRecruitment = sexOfRecruitment;
            return this;
        }

        public Builder setPreferenceGame(PreferenceType preferenceGame) {
            this.preferenceGame = preferenceGame;
            return this;
        }

        public Builder setNumberOfRecruitment(Integer numberOfRecruitment) {
            this.numberOfRecruitment = numberOfRecruitment;
            return this;
        }

        public Builder setCostOfCourtPerPerson(Double costOfCourtPerPerson) {
            this.costOfCourtPerPerson = costOfCourtPerPerson;
            return this;
        }

        public Builder setDetails(String details) {
            this.details = details;
            return this;
        }

        public Builder setStatus(MatchingStatusType status) {
            this.status = status;
            return this;
        }

        public Matching build() {
            return new Matching(this);
        }
    }
}
