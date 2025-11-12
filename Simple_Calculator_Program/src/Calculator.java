public class Calculator {
    private static int totalCalculations = 0;


    protected int lastResult = 0;


    public String name;


    public Calculator(String name) {
        this.name = name;
    }


    public int add(int a, int b) {
        int result = a + b;
        lastResult = result;
        totalCalculations++;
        return result;
    }


    protected int subtract(int a, int b) {
        int result = a - b;
        lastResult = result;
        totalCalculations++;
        return result;
    }


    private void resetCalculator() {
        lastResult = 0;
    }


    public void monthlyReset() {
        System.out.println("Resetting calculator...");
        resetCalculator();
    }


    public static void showTotalCalculations() {
        System.out.println("Total calculations so far: " + totalCalculations);
    }


    public void showLastResult() {
        int lastResult = 999; // Local variable shadows the instance variable
        System.out.println("Local lastResult (shadowed): " + lastResult);
        System.out.println("Instance lastResult: " + this.lastResult);
    }

}
