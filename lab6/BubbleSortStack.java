import java.util.Random;
import java.util.Stack;

public class BubbleSortStack {

    // Генерация случайного стека
    public static Stack<Integer> generateRandomStack(int size) {
        Random rand = new Random();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            stack.push(rand.nextInt(1_000_000));
        }
        return stack;
    }

    // Пузырьковая сортировка стека с использованием второго стека
    public static long runExperiment(int size) {
        Stack<Integer> stack = generateRandomStack(size);
        Stack<Integer> auxStack = new Stack<>();

        long start = System.nanoTime();

        // Bubble sort using two stacks
        for (int i = 0; i < size - 1; i++) {
            // Переносим все элементы в auxStack, "проталкивая" максимум вниз auxStack
            int unsorted = size - i;
            Integer prev = stack.pop();

            // Просматриваем unsorted - 1 пар
            for (int j = 0; j < unsorted - 1; j++) {
                Integer current = stack.pop();
                if (prev > current) {
                    // Меняем местами: сначала кладём меньший (current), потом больший (prev)
                    auxStack.push(prev);
                    prev = current;
                } else {
                    // Порядок правильный: кладём prev, current остаётся для следующего сравнения
                    auxStack.push(current);
                }
            }
            // Теперь prev — это максимальный из неотсортированной части
            stack.push(prev); // Он остаётся на своём месте (внизу отсортированной зоны)

            // Возвращаем остальные элементы из auxStack обратно в stack
            while (!auxStack.isEmpty()) {
                stack.push(auxStack.pop());
            }
        }

        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000};
        int runs = 100;

        System.out.println("=== Bubble Sort on Stack ===");
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