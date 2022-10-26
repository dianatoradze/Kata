import java.util.Scanner;

public class Main {
    public static boolean inPeriod = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение без пробелов: ");
        String inputUser = scanner.nextLine();
        calc(inputUser);
    }

    private static String calc(String input) {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};

        //Определяем арифметическое действие:
        int actionIndex = -1;
        for (int i = 0; i < actions.length; i++) {
            if (input.contains(actions[i])) {
                actionIndex = i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if (actionIndex == -1) {
            System.out.println("Некорректное выражение");

        }

        String[] data = input.split(regexActions[actionIndex]);

        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)

        int a = 0, b = 0;
        int result = 0;

        if (data.length <= 2) {
            if (converter.isRoman(data[0]) == converter.isRoman(data[1])) {

                //конвертируем арабские числа из строки в число
                boolean isRoman = converter.isRoman(data[0]);

                if (isRoman) {
                    //если римские, то конвертируем их в арабские

                    a = Converter.romanToArabic(data[0]);
                    b = Converter.romanToArabic(data[1]);

                } else {

                    a = Integer.parseInt(data[0]);
                    b = Integer.parseInt(data[1]);
                    valueNumbers(a, b);
                }
                //выполняем с числами арифметическое действие
                if (!inPeriod) {
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

                    if (isRoman) {
                        //если числа были римские, возвращаем результат в римском числе
                        System.out.println(Converter.arabicToRoman(result));
                    } else {
                        //если числа были арабские, возвращаем результат в арабском числе
                        System.out.println(result);
                    }
                } else {
                    checkedNumbers();
                }
            } else {
                System.out.println("Числа должны быть в одном формате");
            }
        } else {
            System.out.println("формат математической операции не удовлетворяет заданию \n" +
                    "- два операнда и один оператор");
        }

        return String.valueOf(result);

    }

    private static void checkedNumbers() {
        if (inPeriod) {
            System.out.println("Введите числа от 1 до 10 включительно");
        }
    }

    private static void valueNumbers(int a, int b) {

        if (a < 1 || a >= 10 && b < 1 || b >= 10) {
            inPeriod = true;
        }
    }

}

