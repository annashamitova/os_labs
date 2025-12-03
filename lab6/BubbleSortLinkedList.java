import java.util.LinkedList;
import java.util.Random;

public class BubbleSortLinkedList {

    // Пузырьковая сортировка LinkedList
    public static void bubbleSort(LinkedList<Integer> list) {
        int n = list.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                // Получаем два соседних элемента
                int current = list.get(j);
                int next = list.get(j + 1);

                if (current > next) {
                    // Меняем местами через set
                    list.set(j, next);
                    list.set(j + 1, current);
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    // Создание случайного LinkedList
    public static LinkedList<Integer> generateRandomList(int size) {
        Random r = new Random();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(r.nextInt(1_000_000));
        }
        return list;
    }

    // Один эксперимент
    public static long runExperiment(int size) {
        LinkedList<Integer> list = generateRandomList(size);
        long start = System.nanoTime();
        bubbleSort(list);
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000};
        int runs = 100;

        System.out.println("=== Bubble Sort on LinkedList ===");
        System.out.println("Size\tAverage Time (ms)");

        for (int size : sizes) {
            long total = 0;
            for (int i = 0; i < runs; i++) {
                total += runExperiment(size);
            }
            double avgMs = total / 1_000_000.0 / runs;
            System.out.printf("%d\t%.3f\n", size, avgMs);
        }
    }
}