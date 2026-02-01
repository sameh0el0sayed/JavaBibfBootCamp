public class Stack {
    private char[] data;
    private int top;

    public Stack(int size) {
        data = new char[size];
        top = -1;
    }

    public void push(char value) {
        data[++top] = value;
    }

    public char pop() {
        return data[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}

