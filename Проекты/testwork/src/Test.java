public class Test {
    public static void main(String[] args) {
        String roman2018 = "MMXVIII";
        String roman2 = "II";
        int result = Converter.romanToArabic(roman2018);
        int result1 = Converter.romanToArabic(roman2);
        System.out.println(result);
        System.out.println(result1);
        int answer = result/result1;
        System.out.println(answer);
        int arabic1999 = 1999;

        String result2 = Converter.arabicToRoman(arabic1999);
        System.out.println(result2);


    }
}
