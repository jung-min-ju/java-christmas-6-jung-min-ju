package christmas.Discount;

public enum Badge { //상태와 행위를 한곳에서 관리
    없음(0),
    별(5000),
    트리(10000),
    산타(20000);
    private final int minDiscount;

    Badge(int minDiscount) {
        this.minDiscount = minDiscount;
    }

    public static Badge getBadge(int discount) {
        Badge[] badges = Badge.values();
        for (int i = badges.length - 1; i >= 0; i--) {
            if (discount >= badges[i].minDiscount) {
                return badges[i];
            }
        }
        return 없음;
    }

}
