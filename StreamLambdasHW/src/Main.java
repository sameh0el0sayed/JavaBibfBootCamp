import com.services.Employee;
import com.services.EmployeeService;
import com.services.Gender;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    List<Employee> employees = Arrays.asList(
            new Employee("John", 60000, Gender.MALE, LocalDate.of(2015, 5, 10)),
            new Employee("Alice", 75000, Gender.FEMALE, LocalDate.of(2010, 3, 15)),
            new Employee("Bob", 45000, Gender.MALE, LocalDate.of(2018, 7, 1)),
            new Employee("Diana", 50000, Gender.FEMALE, LocalDate.of(2012, 1, 1)),
            new Employee("Tom", 52000, Gender.MALE, LocalDate.of(2014, 11, 20))
    );

    EmployeeService service = new EmployeeService(employees);

    service.getEmployeesOver50k();
    service.getEmployeeNamesHiredAfter2012();
    service.getMaxSalary();
    service.getMinSalary();
    service.getAverageSalaries();
    service.getMaximumPaidEmployee();

    Optional<Integer> age=Optional.ofNullable(null);
    System.out.println(age.isEmpty());


    //2nd part of HM
}
        public class CommonPrefix {

            public static String longestCommonPrefix(String[] words) {
                if (words == null || words.length == 0) return "";
                int minLen = Integer.MAX_VALUE;
                for (String w : words) {
                    if (w.length() < minLen) minLen = w.length();
                }
                if (minLen == 0) return "";
                for (int i = 0; i < minLen; i++) {
                    char c = words[0].charAt(i);
                    for (int j = 1; j < words.length; j++) {
                        if (words[j].charAt(i) != c) {
                            return words[0].substring(0, i);
                        }
                    }
                }
                return words[0].substring(0, minLen);
            }

            public static void main(String[] args) {
                System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
                System.out.println(longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
            }
        }
