public class CalculatorException extends Exception {
    public CalculatorException() {
        System.out.println("Не правильно задано выражение");
    }

    public CalculatorException(String message){
        //this();
        System.out.println(message);
    }
}
