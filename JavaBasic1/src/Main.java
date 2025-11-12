//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

     String symbol="*";


    for (int i = 0; i < 5; i++) {
        System.out.println(symbol);
        symbol += "*";  // add one more star each loop
    }
    System.out.println(); // move to next line
    String symbol2="*";

    for (int i = 5; i >= 1; i--) {
        for (int j = 1; j <= i; j++) {
            System.out.print(symbol2);
        }
        System.out.println(); // move to next line
    }

}
