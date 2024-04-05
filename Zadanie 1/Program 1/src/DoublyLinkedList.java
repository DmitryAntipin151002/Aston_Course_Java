import java.util.Collection;

class Node<T> {  //Внутренний класс узла
    T data;
    Node<T> prev; //ссылка на предыдущий
    Node<T> next; // ссылка на следующий

    Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

public class DoublyLinkedList<T extends Comparable<T>> { // представляет двусвязный список.

    private Node<T> head; // голова
    private Node<T> tail; // хвост
    private int size; // Размер


    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public DoublyLinkedList(Collection<T> collection) {
        if (size == 0) { // Проверяем, является ли текущая коллекция пустой
            for (T element : collection) {
                add(element); // Добавляем каждый элемент из переданной коллекции в конец текущей коллекции
            }
        }
    }

// Метод для добавления элемента
    public void add(T element) {
        Node<T> newNode = new Node<>(element); // Создаем новый узел с переданным элементом
        if (head == null) { // Если список пустой
            head = newNode; // Новый узел становится и головой, и хвостом списка
            tail = newNode;
        } else { // Если список не пустой
            tail.next = newNode; // Устанавливаем ссылку на новый узел из хвоста
            newNode.prev = tail; // Устанавливаем ссылку на предыдущий узел нового узла
            tail = newNode; // Новый узел становится хвостом списка
        }
        size++; // Увеличиваем размер списка
    }

    public T get(int index) {
        if (index < 0 || index >= size) { // Проверяем, что индекс находится в пределах списка
            throw new IndexOutOfBoundsException("Index out of bounds"); // Бросаем исключение, если индекс недопустим
        }
        Node<T> current = head; // Начинаем с головы списка
        for (int i = 0; i < index; i++) { // Проходим по списку до нужного индекса
            current = current.next; // Переходим к следующему узлу
        }
        return current.data;
    }

    //метод удаления по индексу
    public void remove(int index) {
        if (size == 0) {
            return;
        } else if (size == 1) {
            head = null;
            tail = null;
        } else if (index == 0) {
            head = head.next;
            head.prev = null;
        } else if (index == size - 1) {
            tail = tail.prev;
            tail.next = null;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
    }


    //статическая сортировка пузырьком
    public static <T extends Comparable<T>> void sort(DoublyLinkedList<T> list) {
        if (list.size <= 1) {
            return; // Ничего не делаем, если список содержит 0 или 1 элемент
        }

        Node<T> current = list.head;
        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            while (current != null && current.next != null) {
                if (current.data.compareTo(current.next.data) > 0) {
                    // Если текущий элемент больше следующего, меняем их местами
                    T temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    sorted = false;
                }
                current = current.next;
            }
            // Переходим к началу списка для следующей итерации
            current = list.head;
        }
    }

    //метод для добавления одной элементов из одной коллекции в другую
    public void addAll(DoublyLinkedList<T> list) {
        Node<T> current = list.head; // Начинаем с головы переданного списка
        while (current != null) {
            add(current.data); // Добавляем каждый элемент из переданного списка в конец текущего списка
            current = current.next; // Переходим к следующему узлу переданного списка
        }
    }

    // простой линейный поиск
    public int search(T key) {
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            int comparison = current.data.compareTo(key);
            if (comparison == 0) {
                return index; // Нашли элемент, возвращаем его индекс
            } else if (comparison > 0) {
                return -1; // Если текущий элемент больше ключа, значит элемента в списке нет
            }
            current = current.next;
            index++;
        }

        return -1; // Если дошли до конца списка и не нашли элемент, возвращаем -1
    }

    protected static <T extends Comparable<T>> void printList(DoublyLinkedList<T> list) {
        for (int i = 0; i < list.size; i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

}

