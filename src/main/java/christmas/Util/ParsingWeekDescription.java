package christmas.Util;

public class ParsingWeekDescription {

    public static String parseDescription(String description) { //ex) WeekConstant의 Description에서 "xx 할인" 만 추출
        String[] parts = description.split("\\.");
        String Description = parts[0];
        return Description;
    }

}
