import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Калькулятор римских и арабских чисел \nСимволы следует вводить через пробел \nВвод: ");
        String input = console.nextLine();
        String result = Calc.analysis(input);
        System.out.println("Вывод: " + result);
    }
}

class Rome {
    static String[] romeArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static boolean checkRome (String val) {
        for (int i = 0; i < romeArray.length; i++) {
            if (val.equals(romeArray[i])) {
                return true;
            }
        }
        return false;
    }
    public static int checkIndex (String val) {
        for (int i = 0; i < romeArray.length; i++) {
            return indexOf(romeArray, val);
        }
        return -1;
    }

    private static int indexOf(String[] array, String target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
}

class Calc {
    private static boolean isValidOperator(String operator) {
        return operator.equals("+") || operator.equals("-") || operator.equals("*") ||
                operator.equals("/") || operator.equals("%");
    }
    public static String analysis(String input) {
        String[] expression = input.split(" ");
        if (expression.length > 3) {
            throw new RuntimeException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (expression.length < 3) {
            throw new RuntimeException("т.к. строка не является математической операцией");
        }
        if (isValidOperator(expression[1])) {
        } else {
            throw new IllegalArgumentException("Не допустимый оператор: " + expression[1]);
        }
        if (Rome.checkRome(expression[0])) {
            if (!(Rome.checkRome(expression[2]))){
                throw new RuntimeException("т.к. используются одновременно разные системы счисления");
            }
        } else {
            if (Rome.checkRome(expression[2])) {
                if (!(Rome.checkRome(expression[0]))){
                    throw new RuntimeException("т.к. используются одновременно разные системы счисления");
                }
            }
        }
        if (Rome.checkRome(expression[0]) && Rome.checkRome(expression[2])) {
            return romeCalc(expression);
        } else {
            return arabicCalc(expression);
        }
    }
    public static String arabicCalc(String[] arabic) {
        String operator = arabic[1];
        int a = 0;
        int b = 0;
        String error = "Введите числа от 1 до 10";
        if (Integer.parseInt(arabic[0]) >= 1 && Integer.parseInt(arabic[0]) <= 10) {
            a = Integer.parseInt(arabic[0]);
            if (Integer.parseInt(arabic[2]) >= 1 && Integer.parseInt(arabic[2]) <= 10) {
                b = Integer.parseInt(arabic[2]);
            } else {
                return error;
            }
        } else {
            return error;
        }
        int result = switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };
        return Integer.toString(result);
    }
    public static String romeCalc(String[] rome) {
        String operator = rome[1];
        int a = Rome.checkIndex(rome[0]);
        int b = Rome.checkIndex(rome[2]);
        String error = "Введите числа от I до X";
        if (a >= 1 && a <= 10) {
            if (b >= 1 && b <= 10) {
            } else {
                return error;
            }
        } else {
            return error;
        }
        int c = switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };
        if (c < 0) {
            throw new RuntimeException("т.к. в римской системе нет отрицательных чисел");
        }
        String result = Rome.romeArray[c];
        return result;
    }
}