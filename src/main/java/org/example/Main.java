package org.example;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while(true) {
            try {
                System.out.println(calc(sc.nextLine()));
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static String calc(String args) throws Exception{
        int a=0;
        int b=0;
        int result=0;
        boolean arab=true;

        String[] ops = args.split(" ");
        if(ops.length!=3) throw new Exception("Неверное выражение");
        if(ops[1].length() !=1) throw new Exception("Неверный оператор");

        try{
            a=Integer.parseInt(ops[0]); // Пробуем сначала первое число как арабское
            isValid(a);
        }
        catch(NumberFormatException e ){
            arab = false; // Не является интом; если не является числом вообще, то ниже не сработает проверка на римское
        }
        if(arab){
            try{
                b = Integer.parseInt(ops[2]);
                isValid(b);
            }
            catch(NumberFormatException e ){
                throw new Exception("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно");
            }
        }
        else{ // Если первое число не парсится как инт, пробуем достать из него римское
            try{
                a = getInt(ops[0]);
                isValid(a);
            }
            catch(Exception e){
                throw e; // Не является римским числом или числом вообще
            }
            try{
                b = getInt(ops[2]); // Пробуем то же самое со вторым числом
                isValid(b);
            }
            catch(Exception e){
                throw e;
            }
        }
        switch(ops[1]){
            case "+":
                result = a+b;
                break;
            case "-":
                result = a-b;
                break;
            case "*":
                result = a*b;
                break;
            case "/":
                result = a/b;
                break;
            default:
                throw new Exception("Неверный оператор");
        }
        if(!arab && result <1) throw new Exception("В римской системе нет 0 и отрицательных чисел");
        if(!arab) return getRoman(result);
        return Integer.toString(result);
    }
    private static void isValid(int number) throws Exception{
        if (number <1 || number>10) throw new Exception("Числа должно быть в диапазоне от 1 до 10");
    }
    private static int getInt(String roman) throws Exception {
        int number;
        String[] romanNumbers = {"I", "II","III","IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int index=1;
        for(String s: romanNumbers) {
            if(s.equals(roman)) return index;
            index++;
        }
        throw new Exception("Не является римским числом от 1 до 10");
    }
    private static String getRoman(int number) {
        int[] values = {100,90,50,40,10,9,5,4,1};
        String[] romanLetters = {"C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder roman = new StringBuilder();
        for(int i=0;i<values.length;i++)
        {
            while(number >= values[i])
            {
                number = number - values[i];
                roman.append(romanLetters[i]);
            }
        }
        return roman.toString();
    }
}


