import java.util.HashMap;
import java.util.Random;

public class BubbleSortHashMap {

    // Пузырьковая сортировка для примитивного массива
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
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
        HashMap<Integer, Integer> map = generateRandomHashMap(size);

        // Копируем значения из HashMap в int[] (без ArrayList!)
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = map.get(i); // ← единственный get — по ключу, не по индексу коллекции
        }

        long start = System.nanoTime();
        bubbleSort(arr); // сортировка на int[] — без get/set!
        long end = System.nanoTime();

        // Записываем обратно в HashMap
        for (int i = 0; i < size; i++) {
            map.put(i, arr[i]); // присваивание по индексу, но через ключ
        }

        return end - start;
    }

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000};
        int runs = 100;

        System.out.println("=== Bubble Sort on HashMap ===");
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