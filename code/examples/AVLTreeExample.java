package code.examples;

import code.collections.AVLTree;
import code.numbers.ComplexNumber;

import java.util.Scanner;

public class AVLTreeExample implements IExample {
    @Override
    public void runExample() {
        System.out.println("AVLTree Example запущен");

        AVLTree tree = new AVLTree();
        Scanner scan = new Scanner(System.in);
        char ch;

        do {
            System.out.println("\nДоступные команды\n");
            System.out.println("1. вставить ");
            System.out.println("2. искать");
            System.out.println("3. почитать количество нод");
            System.out.println("4. пустое ли");
            System.out.println("5. очистить дерево");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Введите последовательно вещественную часть и мнимую:");
                    tree.insert(new ComplexNumber(scan.nextDouble(), scan.nextDouble()));
                    break;
                case 2:
                    System.out.println("Введите последовательно вещественную часть и мнимую:");
                    System.out.println("Результаты поиска: " + tree.search(new ComplexNumber(scan.nextDouble(), scan.nextDouble())));
                    break;
                case 3:
                    System.out.println("Ноды= " + tree.countNodes());
                    break;
                case 4:
                    System.out.println("Пустое ли = " + tree.isEmpty());
                    break;
                case 5:
                    System.out.println("\nДерево очищено");
                    tree.makeEmpty();
                    break;
                default:
                    System.out.println("Я не знаю такую команду \n ");
                    break;
            }

            System.out.print("\nPost order : ");
            tree.postorder();

            System.out.print("\nPre order : ");
            tree.preorder();

            System.out.print("\nIn order : ");
            tree.inorder();

            System.out.println("\nХотите продолжить? (y или n) \n");
            ch = scan.next().toUpperCase().charAt(0);
        } while (ch == 'Y');
    }
}
