import java.io.FileWriter;
import java.io.IOException;

public class RomanoADecimal {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Escriba un número romano que desee convertir a decimal: ");
            return;
        }

        try (FileWriter csvWriter = new FileWriter("NumerosRomanos.csv")) {
            csvWriter.append("Número Romano,Valor Decimal\n");

            for (String roman : args) {
                int decimal = romanADecimal(roman);
                System.out.println(roman + " = " + decimal);
                csvWriter.append(roman + "," + decimal + "\n");
            }

        } catch (IOException e) {
            System.out.println("No se ha podido crear el archivo CSV.");
            e.printStackTrace();
        }
    }

    public static int romanADecimal(String roman) {
        int decimal = 0;      
        int lastNumber = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char ch = roman.charAt(i);
            int currentNumber = romanoCharADecimal(ch);

            if (currentNumber < lastNumber) {
                decimal -= currentNumber;
            } else {
                decimal += currentNumber;
            }
            lastNumber = currentNumber;
        }
        return decimal;
    }

    public static int romanoCharADecimal(char ch) {
        switch (ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: throw new IllegalArgumentException("Ese carácter no es válido: " + ch);
        }
    }
}
