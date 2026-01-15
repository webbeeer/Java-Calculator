public class Operation {
    public static double calculate(String op, double a, double b) {

       return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "x" -> a * b;
            case "÷" -> {
                if (b == 0) throw new IllegalArgumentException("Division by zero");
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Unknown op: " + op);

        };
    }

    public static double calculateOne(String op, double value) {

        return switch (op) {
            case "x²" -> value * value;
            case "1/x" -> 1 / value;
            case "√x" -> Math.sqrt(value);
            case "+/-" -> value * -1;
            case "%" -> value / 100;
            default -> value;
        };

    }
}
