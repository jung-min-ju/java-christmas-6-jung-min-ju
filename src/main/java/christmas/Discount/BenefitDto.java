package christmas.Discount;

public class BenefitDto {
    private int ChristDiscount;
    private StarStatus starStatus;
    private WeekType weekType;

    public BenefitDto(int christDiscount, StarStatus starStatus, WeekType weekType) {
        ChristDiscount = christDiscount;
        this.starStatus = starStatus;
        this.weekType = weekType;
    }

    public int getChristDiscount() {
        return ChristDiscount;
    }

    public StarStatus getStarStatus() {
        return starStatus;
    }

    public WeekType getWeekType() {
        return weekType;
    }
}
