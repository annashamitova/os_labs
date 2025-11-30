import java.util.Random;

public class BubbleSortArray {

    // Пузырьковая сортировка массива
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

    // Создание случайного массива
    public static int[] generateRandomArray(int size) {
        Random r = new Random();
        int[] a = new int[size];
        for (int i = 0; i < size; i++) a[i] = r.nextInt(1_000_000);
        return a;
    }

    // Один эксперимент
    public static long runExperiment(int size) {
        int[] arr = generateRandomArray(size);
        long start = System.nanoTime();
        bubbleSort(arr);
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {

        // Размеры — степени 10
        int[] sizes = {100, 1000, 5000, 10000};

        int runs = 3;

        System.out.println("=== Bubble Sort on Array ===");
        System.out.println("Size\tAverage Time (ms)");

        for (int size : sizes) {
            long total = 0;

            for (int i = 0; i < runs; i++) {
                long t = runExperiment(size);
                total += t;
            }

            long avg = total / runs;
            System.out.printf("%d\t%.3f\n", size, avg / 1_000_000.0);
        }
    }
}
