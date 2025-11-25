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
}
