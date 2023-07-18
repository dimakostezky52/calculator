package some.dima.calc;

import java.util.Scanner;

class Main {

    public static void main(String[] args){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            System.out.println(calc(line));
        }
    }

    public static String calc(String input) {
        String[] words = input.split("\\s");
        Number[] numbers = parseNumbers(words);
        Number a = numbers[0];
        Number b = numbers[1];

        Number result = switch (words[1]) {
            case "+" -> a.plus(b);
            case "-" -> a.minus(b);
            case "/" -> a.divide(b);
            case "*" -> a.multiply(b);
            default -> throw new NumberException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        };

        return result.toString();
    }

    private static Number[] parseNumbers(String[] numbers) {
        if (numbers[0].equalsIgnoreCase("q")) {
            System.exit(0);
        }

        if (numbers.length < 3)
            throw new NumberException("строка не является математической операцией");
        else if (numbers.length > 3)
            throw new NumberException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        return new Number[] {new Number(numbers[0]),  new Number(numbers[2])};
    }
}
