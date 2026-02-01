import java.util.Stack;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    // Exercise 1: Rotate an Array
    // Test Case 1
    int[] arr1 = {1, 2, 3, 4, 5};
    rotate(arr1, 2, arr1.length);
    System.out.println("Rotate by 2: " + Arrays.toString(arr1));

    // Test Case 2
    int[] arr2 = {10, 20, 30, 40, 50};
    rotate(arr2, 7, arr2.length);
    System.out.println("Rotate by 7: " + Arrays.toString(arr2));

    //    Exercise 2: Expression Balancing Using Stack
     String exp1 = "{[()]}";
    String exp2 = "{[(])}";
    String exp3 = "((()))";
    String exp4 = "{[}";
    String exp5 = "";

    System.out.println(exp1 + " -> " + BalancedBracketsChecker.isBalanced(exp1));
    System.out.println(exp2 + " -> " + BalancedBracketsChecker.isBalanced(exp2));
    System.out.println(exp3 + " -> " + BalancedBracketsChecker.isBalanced(exp3));
    System.out.println(exp4 + " -> " + BalancedBracketsChecker.isBalanced(exp4));
    System.out.println("Empty string -> " + BalancedBracketsChecker.isBalanced(exp5));

    //Exercise 3: Reverse a Stack
    // Test Case 1
    Stack<Integer> stack1 = new Stack<>();
    stack1.push(1);
    stack1.push(2);
    stack1.push(3);
    stack1.push(4);

    System.out.println("Original Stack: " + stack1);
    reverseStack(stack1);
    System.out.println("Reversed Stack: " + stack1);

    // Test Case 2
    Stack<Integer> stack2 = new Stack<>();
    stack2.push(10);
    stack2.push(20);

    System.out.println("\nOriginal Stack: " + stack2);
    reverseStack(stack2);
    System.out.println("Reversed Stack: " + stack2);
}

public static void rotate(int[] arr, int d  , int n) {
    if (n == 0) return;

    d = d % n;

    reverse(arr, 0, d - 1);
    reverse(arr, d, n - 1);
    reverse(arr, 0, n - 1);
}

private static void reverse(int[] arr, int start, int end) {
    while (start < end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        start++;
        end--;
    }

}
public static void reverseStack(Stack<Integer> stack) {
    if (stack.isEmpty()) {
        return;
    }

    int top = stack.pop();
    reverseStack(stack);
    insertAtBottom(stack, top);
}

// Helper method to insert an element at the bottom of the stack
private static void insertAtBottom(Stack<Integer> stack, int value) {
    if (stack.isEmpty()) {
        stack.push(value);
        return;
    }

    int top = stack.pop();
    insertAtBottom(stack, value);
    stack.push(top);
}

