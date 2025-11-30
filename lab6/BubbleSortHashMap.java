import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class BubbleSortHashMap {

    // Пузырьковая сортировка ArrayList
    public static void bubbleSort(ArrayList<Integer> list) {
        int n = list.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j) > list.get(j + 1)) {

                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);

                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }

    // Генерация HashMap с ключами 0..size-1
    public static HashMap<Integer, Integer> generateRandomHashMap(int size) {
        Random rand = new Random();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            map.put(i, rand.nextInt(1_000_000));
        }

        return map;
    }

    public static long runExperiment(int size) {

        // создаём HashMap
        HashMap<Integer, Integer> map = generateRandomHashMap(size);

        // преобразуем значения в ArrayList (доступ по индексу)
        ArrayList<Integer> list = new ArrayList<>(map.values());

        long start = System.nanoTime();
        bubbleSort(list);
        long end = System.nanoTime();

        // записываем обратно в HashMap
        for (int i = 0; i < size; i++) {
            map.put(i, list.get(i));
        }

        return end - start;
    }

    public static void main(String[] args) {

        int[] sizes = {100, 1000, 5000, 10000};
        int runs = 3;

        System.out.println("=== Bubble Sort on HashMap (via ArrayList) ===");
        System.out.println("Size\tAverage Time (ms)");

        for (int size : sizes) {
            long total = 0;

            for (int i = 0; i < runs; i++) {
                total += runExperiment(size);
            }

            long avg = total / runs;
            System.out.printf("%d\t%.3f\n", size, avg / 1_000_000.0);
        }
    }
}
