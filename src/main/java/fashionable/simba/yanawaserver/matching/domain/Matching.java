package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.constant.*;
import fashionable.simba.yanawaserver.matching.error.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class Matching {
    private Long id;
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

    public Long getId() {
        return id;
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
        if (builder.id == null) {
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

        this.id = builder.id;
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
        private Long id;
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

        public Builder id(Long id) {
            this.id = id;
            return this;
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

        public Builder annual(AnnualType annual) {
            this.annual = annual;
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

        public Builder details(String details) {
            this.details = details;
            return this;
        }

        public Builder status(MatchingStatusType status) {
            this.status = status;
            return this;
        }

        public Matching build() {
            return new Matching(this);
        }
    }
}
