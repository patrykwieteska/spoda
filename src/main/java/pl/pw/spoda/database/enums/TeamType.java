package pl.pw.spoda.database.enums;

public enum TeamType {

    CLUB (0 ),
    MEN_NATIONAL(1 ),
    WOMEN_NATIONAL(2 );

    private final Integer type;

    TeamType(Integer type) {
        this.type = type;
    }

    public Integer getType(TeamType teamType) {
        return teamType.type;
    }
}
