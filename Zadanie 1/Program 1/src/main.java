import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

class Main {
    public static void main(String[] args) {

        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.add("Andrey");
        list.add("Piter");
        list.add("MAks");
        list.add("Pasha");
        list.add("Oleg");
        list.add("Kirill");
        System.out.println("Список после добавления элементов:");
        DoublyLinkedList.printList(list);
        list.remove(3);

        System.out.println("\nСписок после удаления третьего элемента:");
        DoublyLinkedList.printList(list);

        DoublyLinkedList<String> collection = new DoublyLinkedList<>();
        collection.add("Goga");
        collection.add("BMW");
        collection.add("FORD");
        // Добавляем элементы из первого списка list в коллекцию collection
        list.addAll(collection);
        System.out.println("\nСписок после добавления элементов  из 1 коллекции через :");
        DoublyLinkedList.printList(list);


        System.out.println("Список до сортировки:");
        DoublyLinkedList.printList(list);

        System.out.println("\nСписок после сортировки:");
        DoublyLinkedList.sort(list);
        DoublyLinkedList.printList(list);

        // Выполняем поиск элемента
        String key = "Piter";
        int index = list.search(key);
        if (index != -1) {
            System.out.println("\nЭлемент " + key + " найден по индексу " + index);
        } else {
            System.out.println("\nЭлемент " + key + " не найден");
        }
    }


}
