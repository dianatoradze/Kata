import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите выражение без пробелов: ");
        String exp = scn.nextLine();
        //Определяем арифметическое действие:
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (exp.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if (actionIndex == -1) {
            System.out.println("Некорректное выражение");
            return;
        }

        String[] data = exp.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)

        int a = 0, b = 0;

        if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {

            //конвертируем арабские числа из строки в число
            boolean isRoman = converter.isRoman(data[0]);

                if(isRoman){
                //если римские, то конвертируем их в арабские

                a = Converter.romanToArabic(data[0]);
                b = Converter.romanToArabic(data[1]);

            }else {
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            //выполняем с числами арифметическое действие
            int result;
            switch (actions[actionIndex]) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                default:
                    result = a / b;
                    break;
            }

            if(isRoman){
                //если числа были римские, возвращаем результат в римском числе
                System.out.println(Converter.arabicToRoman(result));
            }
            else {
                //если числа были арабские, возвращаем результат в арабском числе
                System.out.println(result);
            }
        }
        else {
            System.out.println("Числа должны быть в одном формате");
        }

    }
}

