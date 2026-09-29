import java.io.*;
import java.util.*;

public class Queue2Stacks {

    static class MyQueue<T> {
        private Stack<T> stack1 = new Stack<>();
        private Stack<T> stack2 = new Stack<>();


        public void enqueue(T value) {
            stack1.push(value);
        }


        public void dequeue() {
            if(stack2.isEmpty()){
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            if(!stack2.isEmpty()){stack2.pop();}
        }

        public T peek() {
            if(stack2.isEmpty()){
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            if(!stack2.isEmpty()){return stack2.peek();}
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        if (!scanner.hasNextInt()) return;
        int q = scanner.nextInt();

        MyQueue<Integer> queue = new MyQueue<>();

        for (int i = 0; i < q; i++) {
            int type = scanner.nextInt();

            if (type == 1) {
                int x = scanner.nextInt();
                queue.enqueue(x);
            } else if (type == 2) {
                queue.dequeue();
            } else if (type == 3) {
                System.out.println(queue.peek());
            }
        }

        scanner.close();
    }
}