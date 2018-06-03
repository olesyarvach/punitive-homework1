package code;

import code.examples.AVLTreeExample;
import code.examples.DequeExample;
import code.examples.DoublyLinkedListExample;
import code.examples.IExample;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Протестировать AVL дерево (AVL) или остальное (OTHER)?: ");
        String choice;
        do {
            choice = scanner.next().trim().toUpperCase();
            switch (choice) {
                case "OTHER":
                    Collection<IExample> examples = new ArrayList<IExample>() {{
                        add(new DequeExample());
                        add(new DoublyLinkedListExample());
                        // add(new AVLTreeExample());
                    }};

                    final int delayBetweenExamplesInSeconds = 2;
                    examples.forEach(example -> {
                        try {
                            example.runExample();
                            Thread.sleep(delayBetweenExamplesInSeconds * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    break;
                case "AVL":
                    System.out.println("Не совсем понятно, как нужно сравнивать комплексные числа в АВЛ-дереве, поэтому я решила сравнивать их по модулю");
                    new AVLTreeExample().runExample();
                    break;
                default:
                    System.out.println("Я не знаю такой команды");
                    break;
            }
        } while (!choice.equals("OTHER") && !choice.equals("AVL"));

        scanner.close();
        System.out.println("Спасибо за просмотр моей программы!");
    }
}
