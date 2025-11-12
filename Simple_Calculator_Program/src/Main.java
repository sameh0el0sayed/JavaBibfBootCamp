//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    IO.println(String.format("Hello and welcome to my calculator!"));

    Calculator calc = new Calculator("MyCalculator");

    System.out.println("Calculator Name: " + calc.name);
    System.out.println("Addition Result: " + calc.add(10, 5));
    System.out.println("Subtraction Result: " + calc.subtract(10, 5));

    calc.showLastResult();
    Calculator.showTotalCalculations();

    calc.monthlyReset();
    calc.showLastResult();
    Calculator.showTotalCalculations();
}
