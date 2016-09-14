import java.util.LinkedList;

// http://www.lintcode.com/en/problem/implement-queue-by-two-stacks/
public class QID40 {
    LinkedList<Integer> pushStack;
    LinkedList<Integer> popStack;
    public QID40() {
        pushStack = new LinkedList<>();
        popStack = new LinkedList<>();
    }

    public void push(int element) {
        while (!popStack.isEmpty()) {
            pushStack.push(popStack.pop());
        }
        pushStack.push(element);
    }

    public int pop() {
        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
        return popStack.pop();
    }

    public int top() {
        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
        return popStack.peek();
    }
}
