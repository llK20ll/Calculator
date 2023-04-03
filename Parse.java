import java.util.HashMap;
import java.util.Map;

public class Parse {
    //Соответствие римские-арабские числа (I..X, 1..10)
    private final Map<String, Integer> romanArabMap = new HashMap<>();

    //Соответствие римские-арабские числа
    private final int[] arabDigit = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
    private final String[] romanDigit = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public Parse() {
        romanArabMap.put("I", 1);
        romanArabMap.put("II", 2);
        romanArabMap.put("III", 3);
        romanArabMap.put("IV", 4);
        romanArabMap.put("V", 5);
        romanArabMap.put("VI", 6);
        romanArabMap.put("VII", 7);
        romanArabMap.put("VIII", 8);
        romanArabMap.put("IX", 9);
        romanArabMap.put("X", 10);
    }

    //Проверка, что это арабское число
    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Проверка, что это римское число (ограничение I..X)
    public boolean isRoman(String str) {
        return romanArabMap.containsKey(str);
    }

    //Преобразование из римского в арабское число (огранич. I..X)
    public Integer romeToArabConvert(String st){
        if (romanArabMap.containsKey(st)){
            return romanArabMap.get(st);
        }
        return null;
    }

    //Преобразование из арабского в римское число (огранич. 1..399)
    public String arabToRomeConvert(Integer num){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i< arabDigit.length; i++){
            while((num - arabDigit[i])>=0){
                num -= arabDigit[i];
                result.append(romanDigit[i]);
            }
        }
        return result.toString();
    }

    //Проверка корректности оператора, должно быть: +-*/
    public boolean checkOperator(String op){
        return "*".equals(op) || "/".equals(op) || "+".equals(op) || "-".equals(op);
    }

}