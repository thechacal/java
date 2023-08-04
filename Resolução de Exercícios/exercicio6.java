/*
A função `FindIntersection(strArr)` recebe um array de strings chamado `strArr`, que conterá 2 elementos: 
o primeiro elemento representa uma lista de números separados por vírgula, ordenados em ordem crescente; 
o segundo elemento representa outra lista de números separados por vírgula, também ordenados. 
O objetivo é retornar uma string contendo os números que ocorrem em ambos os elementos de `strArr`, 
em ordem crescente e separados por vírgula. Se não houver interseção, a função deve retornar a string "false".
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntersectionFinder {
    private String[] strArr;

    public IntersectionFinder(String[] strArr) {
        this.strArr = strArr;
    }

    public String findIntersection() {
        List<String> list1 = new ArrayList<>(Arrays.asList(strArr[0].split(", ")));
        List<String> list2 = new ArrayList<>(Arrays.asList(strArr[1].split(", ")));

        list1.retainAll(list2);
        list1.sort(null);

        return list1.isEmpty() ? "false" : String.join(",", list1);
    }

    public static void main(String[] args) {
        // Teste com exemplo ["1, 3, 4, 7, 13", "1, 2, 4, 13, 15"]
        String[] example1 = {"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"};
        IntersectionFinder finder1 = new IntersectionFinder(example1);
        System.out.println(finder1.findIntersection()); // Saída: "1,4,13"

        // Teste com exemplo ["1, 2, 4, 5, 6", "3, 7, 8, 9, 10"]
        String[] example2 = {"1, 2, 4, 5, 6", "3, 7, 8, 9, 10"};
        IntersectionFinder finder2 = new IntersectionFinder(example2);
        System.out.println(finder2.findIntersection()); // Saída: "false"
    }
}