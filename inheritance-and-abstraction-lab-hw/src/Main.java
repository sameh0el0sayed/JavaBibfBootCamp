import school.schoolService;// Main method
public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Enter an integer to convert to Roman numeral: ");
    int number = sc.nextInt();

    if (number <= 0) {
        System.out.println("Roman numerals only exist for positive integers.");
    } else {
        String roman = RomanConverter.intToRoman(number);
        System.out.println("Roman numeral: " + roman);
    }

    sc.close();

    schoolService hs = new schoolService();
    hs.setStudent("Alice");
    hs.setHomework("Math Assignment");
    hs.setScore(95);
    hs.setComplete(true);

    System.out.println("Student Name   : " + hs.getStudent());
    System.out.println("Homework       : " + hs.getHomework());
    System.out.println("Score          : " + hs.getScore());
    System.out.println("Completed      : " + (hs.isComplete() ? "Yes" : "No"));

}