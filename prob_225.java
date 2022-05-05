import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class prob_225 {
    public static void main(String[] args) {
        String[] actions = {"MyStack", "push", "push", "top", "pop", "empty"};
        Integer[] integers = {null, 1, 2, null, null};
        MyStack_1 myStack = null;
        for (int i = 0; i < actions.length; i++) {
            switch (actions[i]) {
                case "MyStack" -> myStack = new MyStack_1();
                case "push" -> myStack.push(integers[i]);
                case "top" -> System.out.println(myStack.top());
                case "pop" -> System.out.println(myStack.pop());
                case "empty" -> System.out.println(myStack.empty());
                default -> {
                    System.out.println("Invalid operation to perform on stack");
                }
            }
            System.out.println(myStack);
        }
    }
}

class MyStack {
    Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedBlockingQueue<>();
    }

    public void push(int x) {
        Queue<Integer> tmpQueue = new LinkedBlockingQueue<>();
        tmpQueue.add(x);
        queue.forEach(integer -> {
            tmpQueue.add(integer);
        });
        queue = tmpQueue;
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder stackString = new StringBuilder();
        stackString.append("MyStack: { ");
        queue.forEach(integer -> {
            stackString.append(integer);
            stackString.append(",");
        });
        stackString.deleteCharAt(stackString.length()-1);
        stackString.append(" }");
        return stackString.toString();
    }
}

class MyStack_1 {
    Queue<Integer> queue;

    public MyStack_1() {
        queue = new LinkedBlockingQueue<>();
    }

    public void push(int x) {
        queue.add(x);
        for (int i = 1; i < queue.size(); i++) {
            int tmp = queue.remove();
            queue.add(tmp);
        }
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder stackString = new StringBuilder();
        stackString.append("MyStack: { ");
        queue.forEach(integer -> {
            stackString.append(integer);
            stackString.append(",");
        });
        stackString.deleteCharAt(stackString.length()-1);
        stackString.append(" }");
        return stackString.toString();
    }
}
