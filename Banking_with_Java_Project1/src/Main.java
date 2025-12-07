import com.Customer.CustomerModel;
import com.Customer.CustomerService;
import com.Login.LoginSys;
import com.Login.AuthModel;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    //login into the system
    Scanner scanner = new Scanner(System.in);
    AuthModel auth = new AuthModel();
    int attempts = 3;

    while (attempts > 0) {
        System.out.print("You have "+attempts+" attempts \n");
        System.out.print("Enter username: ");
        auth.setUserName(scanner.nextLine());

        System.out.print("Enter password: ");
        auth.setPassword(scanner.nextLine());

        if (LoginSys.login(auth)) {
            System.out.println("Hello and welcome!");

            CustomerService  customerService=new CustomerService();
            CustomerModel  customer=customerService.findByUsername(auth.getUserName());

            System.out.println(customer.getFirstName());

            System.out.println("1-create customer account");
            System.out.println("2-check my savings account balance");
            System.out.println("3-check my checking account balance");
            System.out.println("4-make Transfer Money to same account or another one");

            switch (scanner.nextLine()) {
                case "1":
                    Scanner sc = new Scanner(System.in);

                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter First Name: ");
                    String firstName = sc.nextLine();

                    System.out.print("Enter Middle Name (press Enter to skip): ");
                    String middleName = sc.nextLine();
                    if (middleName.isEmpty()) {
                        middleName = "";
                    }

                    System.out.print("Enter Last Name: ");
                    String lastName = sc.nextLine();

                    System.out.print("Enter Username: ");
                    String userName = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                     CustomerModel newCust = new CustomerModel(
                            id,
                            firstName,
                            middleName,
                            lastName,
                            userName,
                            password
                    );

                    customerService.save(newCust);
                    System.out.println("Customer created successfully!");

                    break;
                case "2":
                    // code
                    break;
                    case "4":
                    // code
                    break;

                default:
                    // code if nothing matches
            }

            break; // login successful, exit loop
        } else {
            attempts--;
            System.out.println("Password is not correct. Attempts left: " + attempts);
            if (attempts == 0) {
                System.out.println("Login failed. No attempts left.");
            }
        }
    }}
