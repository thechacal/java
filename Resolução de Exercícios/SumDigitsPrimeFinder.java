/*
Vamos definir D(n) como o enésimo inteiro positivo cuja soma dos dígitos é um número primo.
Por exemplo, D(61) = 157 e D(10^8) = 403539364.

Encontre D(10^16).
*/
import java.util.Arrays;

public class SumDigitsPrimeFinder {
    private int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};
    
    public long findD(long n) {
        int count = 0;
        long number = 1;
        
        while (count < n) {
            if (isPrimeSumOfDigits(number)) {
                count++;
            }
            number++;
        }
        
        return number - 1;
    }

    private boolean isPrimeSumOfDigits(long number) {
        long sum = 0;
        long num = number;
        
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        
        for (int prime : primes) {
            if (sum == prime) {
               
