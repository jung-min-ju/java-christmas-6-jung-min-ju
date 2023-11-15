    package christmas.Discount;

    import static christmas.Constant.DateConstant.WEEKEND;
    import static christmas.Constant.WeekConstant.*;
    import static christmas.Util.Judge.ReturnJudgeWeek;

    public enum WeekType { //상태와 행위를 한곳에서 관리
        WEEKDAYTYPE (WEEKDAYTARGET, WEEKDAYDESCRIPTION, WEEKDISCOUNT),
        WEEKENDTYPE (WEEKENDTARGET, WEEKENDDESCRIPTION, WEEKENDDISCOUNT);

        private final String Target;
        private final String Description;
        private final int Discount;

        WeekType(String target, String description, int discount) {
            this.Target = target;
            this.Description = description;
            this.Discount = discount;
        }

        public static WeekType determineWeekStatus(int DateInput) {
            String weekType = ReturnJudgeWeek(DateInput);
            if(weekType == WEEKEND)  {
                return WEEKENDTYPE;
            }
            return WEEKDAYTYPE;
        }

        public String getDescription() {
            return Description;
        }

        public int getDiscount() {
            return Discount;
        }

        public String getTarget() {
            return Target;
        }
    }
