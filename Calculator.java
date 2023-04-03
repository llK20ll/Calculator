import java.util.Arrays;
import java.util.List;

public class Calculator {
    private int number1, number2;         //Два числа
    private String operator;        //Оператор, допускается: +-*/

    //В ответе только целые числа
    private int calcExp(int n1, String op, int n2){
        int res;
        switch (op) {
            case "+":
                res = n1+n2;
                break;
            case "-":
                res = n1-n2;
                break;
            case "*":
                res = n1*n2;
                break;
            case "/":
                res = n1/n2;
                break;
            default:
                throw new AssertionError();
        }
        return res;
    }

    //Публичный метод с проверками и с выводом результата
    public String result(String exp) throws CalculatorException {
        boolean isRomanExp;     //Флаг, что числа римские
        Parse parse = new Parse();

        //Разбираем выражение String по разделителю " "
        List<String> expItems = Arrays.asList(exp.split(" "));

        //Проверяем, что в списке появилось 3 элемента: первое число, оператор, второе число, иначе исключение
        if (expItems.size()!=3){
            throw new CalculatorException("Ошибка. Выражение должно иметь вид: \"Первое число Оператор Второе число\", разделенные пробелом...");
        }

        //Проверяем оператор, должен быть: *, /, +, -
        if (parse.checkOperator(expItems.get(1))){
            operator = expItems.get(1);
        } else {
            throw new CalculatorException("Ошибка. Оператора '" + expItems.get(1) + "' нет, должен быть: + - * / ");
        }

        //Проверяем числа, должны быть оба арабские или оба римские
        if (parse.isNumeric(expItems.get(0)) && parse.isNumeric(expItems.get(2))){      //проверка, что оба числа арабские
            number1 = Integer.parseInt(expItems.get(0));
            number2 = Integer.parseInt(expItems.get(2));
            isRomanExp = false;
        } else if (parse.isRoman(expItems.get(0)) && parse.isRoman(expItems.get(2))){   //проверка, что оба числа римские
            number1 = parse.romeToArabConvert(expItems.get(0));
            number2 = parse.romeToArabConvert(expItems.get(2));
            isRomanExp = true;
        } else {    //Числа не сходятся по типу(арабские/римские)
            throw new CalculatorException("Ошибка. Оба числа должны быть одного типа(арабские/римские)");
        }

        //Должны быть числа от 1 до 10(включительно)
        if (!(number1 >=1 && number1 <=10)){
            throw new CalculatorException("Ошибка. Первое число должно быть от 1 до 10 или от I до X включительно");
        }

        if (!(number2 >=1 && number2 <=10)){
            throw new CalculatorException("Ошибка. Второе число должно быть от 1 до 10 или от I до X включительно");
        }
        //
        //Результат

        int res = calcExp(number1, operator, number2);

        //
        if(isRomanExp == true && res <= 0)
        {
            throw new CalculatorException("Ошибка. В римской системе нет отрицательных чисел.");
        }
        //

        //Если числа римские, то конвертируем в арабские и возвращаем результат
        if (isRomanExp){
            String sign = res < 0 ? "-" : "";
            return sign + parse.arabToRomeConvert(Math.abs(res));
        }

        //Ответ(арабское число)
        return String.valueOf(res);
    }

}