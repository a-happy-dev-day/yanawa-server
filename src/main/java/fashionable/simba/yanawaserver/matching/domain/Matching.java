package fashionable.simba.yanawaserver.matching.domain;

import fashionable.simba.yanawaserver.matching.domain.constant.AgeGroupType;
import fashionable.simba.yanawaserver.matching.domain.constant.AnnualType;
import fashionable.simba.yanawaserver.matching.domain.constant.GenderType;
import fashionable.simba.yanawaserver.matching.domain.constant.MatchingStatusType;
import fashionable.simba.yanawaserver.matching.domain.constant.PreferenceType;
import fashionable.simba.yanawaserver.matching.domain.error.InvalidCostException;
import fashionable.simba.yanawaserver.matching.domain.error.InvalidNumberException;
import fashionable.simba.yanawaserver.matching.domain.error.MatchingTimeException;
import fashionable.simba.yanawaserver.matching.domain.error.NoCourtDataException;
import fashionable.simba.yanawaserver.matching.domain.error.NoMatchingDataException;

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


    public Matching(MatchingBuilder builder) {
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

    public static class MatchingBuilder {
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

        public MatchingBuilder(Long matchingId) {
            this.matchingId = matchingId;
        }

        public MatchingBuilder setCourtId(Long courtId) {
            this.courtId = courtId;
            return this;
        }

        public MatchingBuilder setHostId(Long hostId) {
            this.hostId = hostId;
            return this;
        }

        public MatchingBuilder setDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public MatchingBuilder setStartTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public MatchingBuilder setEndTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public MatchingBuilder setAnnual(AnnualType annual) {
            this.annual = annual;
            return this;
        }

        public MatchingBuilder setMaximumLevel(Level maximumLevel) {
            this.maximumLevel = maximumLevel;
            return this;
        }

        public MatchingBuilder setMinimumLevel(Level minimumLevel) {
            this.minimumLevel = minimumLevel;
            return this;
        }

        public MatchingBuilder setAgeOfRecruitment(AgeGroupType ageOfRecruitment) {
            this.ageOfRecruitment = ageOfRecruitment;
            return this;
        }

        public MatchingBuilder setSexOfRecruitment(GenderType sexOfRecruitment) {
            this.sexOfRecruitment = sexOfRecruitment;
            return this;
        }

        public MatchingBuilder setPreferenceGame(PreferenceType preferenceGame) {
            this.preferenceGame = preferenceGame;
            return this;
        }

        public MatchingBuilder setNumberOfRecruitment(Integer numberOfRecruitment) {
            this.numberOfRecruitment = numberOfRecruitment;
            return this;
        }

        public MatchingBuilder setCostOfCourtPerPerson(Double costOfCourtPerPerson) {
            this.costOfCourtPerPerson = costOfCourtPerPerson;
            return this;
        }

        public MatchingBuilder setDetails(String details) {
            this.details = details;
            return this;
        }

        public MatchingBuilder setStatus(MatchingStatusType status) {
            this.status = status;
            return this;
        }

        public Matching build() {
            return new Matching(this);
        }
    }
}
