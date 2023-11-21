import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MyFIFO_App {
    public static <E> void stutter(Queue<E> input) {
    if(input == null){
        return;
    }
    int size = input.size();
        for (int i = 0; i < size; i++) {
            E element = input.poll();
            input.offer(element);
            input.offer(element);
        }
 }
    public static <E> void mirror(Queue<E> input) {
        Stack<E> stack = new Stack<>();
        Queue<E> input1 = new LinkedList<>(input);
        for (int i = 0; i < input.size(); i++) {
            stack.push(input1.poll());

        }
        for (int i =stack.size()-1 ; i >= 0 ; i--) {
            input.offer(stack.pop());

        }
        System.out.println(input);
        }


    public static void main(String[] args) {
        Queue<Character> myQueue = new LinkedList<>();
        myQueue.offer('a');
        myQueue.offer('b');
        myQueue.offer('c');

        System.out.println("Original queue: " + myQueue);

        mirror(myQueue);

        System.out.println("Stuttered queue: " + myQueue);

    }
}
