import java.util.Collections;
import java.util.List;

public class Converter {
    Numbers numbers;

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException(number + " в римской системе нет отрицательных чисел, \n" +
                    "ответ вне диапазона (0,4000]");
        }

        List romanNumerals = Numbers.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            Numbers currentSymbol = (Numbers) romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List romanNumerals = Numbers.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            Numbers symbol = (Numbers) romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input);
        }

        return result;
    }

    public boolean isRoman(String number) {

        try {
            //для римских чисел
            return Numbers.getReverseSortedValues().contains(Numbers.valueOf(number));
        } catch (IllegalArgumentException e) {
            //для арабских чисел
            return Numbers.getReverseSortedValues().contains(number);
        }

    }

}
