package calculator.logic;

import calculator.model.Polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public Polynomial<Integer> stringToPolynom(String str) {
        Pattern pattern = Pattern.compile("([+-]?[^+-]+)");
        Matcher matcher = pattern.matcher(str);
        Polynomial<Integer> p = new Polynomial<>();
        while (matcher.find()) {
            String group = matcher.group(1);
            int powerIndex = group.indexOf('^');
            System.out.println(group);
            int xIndex = group.indexOf('x');
            if (xIndex == -1) {
                p.addCoefficient(0, Integer.parseInt(group));
            } else if (powerIndex == -1) {
                p.addCoefficient(1, getCoefficient(group));
            } else {
                p.addCoefficient(Integer.parseInt(group.substring(powerIndex + 1)), getCoefficient(group.substring(0, powerIndex)));
            }
        }
        return p;
    }

    private Integer getCoefficient(String str) {
        int xIndex = str.indexOf('x');
        int number = 0;
        if (xIndex == 0)
            number = 1;
        else {
            if (str.charAt(0) == '-' && xIndex == 1)
                number = -1;
            else if (str.charAt(0) == '+' && xIndex == 1)
                number = 1;
            else
                number = Integer.parseInt(str.substring(0, xIndex));
        }
        return number;
    }
}
