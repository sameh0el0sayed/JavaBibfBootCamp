import com.Account.AccountModel;
import com.Account.AccountService;
import com.Customer.CustomerModel;
import com.Customer.CustomerService;
import com.Login.LoginSys;
import com.Login.AuthModel;
import com.Transaction.TransactionModel;
import com.Transaction.TransactionService;
import org.mindrot.jbcrypt.BCrypt;

private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    //login into the system
    Scanner scanner = new Scanner(System.in);
    AuthModel auth = new AuthModel();
    int attempts = 3;
    boolean running = true;
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

            AccountService accountService=new AccountService(customer.getId());


            System.out.println(customer.getFirstName());
            while (running) {System.out.println("\n===== Main Menu =====");
                System.out.println("1-Create customer account");
                System.out.println("2-Check my savings account balance");
                System.out.println("3-Check my checking account balance");
                System.out.println("4-My Accounts List");
                System.out.println("5-Make a transfer");
                System.out.println("6-Withdraw Money");
                System.out.println("7-Deposit Money");
                System.out.println("8-Transaction Log");
                System.out.println("9-Transaction Log with date filter");
                System.out.println("0-Exit");

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
                        String password =  hashPassword(sc.nextLine());

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
                        System.out.println(accountService.findSavingsAccount().getBalance());
                        break;
                    case "3":
                        System.out.println(accountService.findCheckingAccount().getBalance());

                        break;
                    case "4":
                        ArrayList<AccountModel> myacc=accountService.findByUserId();
                        if (myacc.isEmpty()) {
                            System.out.println("You have no accounts.");
                        } else {
                            System.out.println("\n===== Your Accounts =====");

                            for (AccountModel acc : myacc) {
                                System.out.println(
                                        "Account ID: " + acc.getAccountId() +
                                                " | Type: " + acc.getAccountType() +
                                                " | Balance: " + acc.getBalance()
                                );
                            }
                        }

                        break;
                    case "5":
                        System.out.println("\n===== Transfer Money =====");

                        System.out.print("\nEnter From Account ID: ");
                        String fromId = scanner.nextLine();

                        System.out.print("\nEnter To Account ID: ");
                        String toId = scanner.nextLine();

                        System.out.print("\nEnter Amount: ");
                        double amt = Double.parseDouble(scanner.nextLine());

                        try {
                            accountService.AmountTransfer(fromId, toId, amt);
                            System.out.println("Transfer successful!");
                        } catch (Exception ex) {
                            System.out.println("Transfer failed: " + ex.getMessage());
                        }
                        break;


                    case "6":
                        System.out.println("\n===== Withdraw Money =====");

                        System.out.print("\nEnter Account ID: ");
                        String accId = scanner.nextLine();

                        System.out.print("\nEnter Amount: ");
                        double monAmt = Double.parseDouble(scanner.nextLine());

                        try {
                            accountService.withdraw(accId, monAmt);
                            System.out.println(monAmt+" Withdraw successful!");
                        } catch (Exception ex) {
                            System.out.println("Withdraw failed: " + ex.getMessage());
                        }
                        break;
                    case "7":
                        System.out.println("\n===== Deposit  Money =====");

                        System.out.print("\nEnter Account ID: ");
                        String daccId = scanner.nextLine();

                        System.out.print("\nEnter Amount: ");
                        double dmonAmt = Double.parseDouble(scanner.nextLine());

                        try {
                            accountService.deposit(daccId, dmonAmt);
                            System.out.println(dmonAmt+" Deposit successful!");
                        } catch (Exception ex) {
                            System.out.println("Deposit failed: " + ex.getMessage());
                        }
                        break;
                    case "8":
                        TransactionService transactionService =new TransactionService();
                        System.out.print("\nEnter Account ID: ");
                        String TranAccId = scanner.nextLine();
                        List<TransactionModel> myTrans=transactionService.findByAccountId(TranAccId);
                        if (myTrans.isEmpty()) {
                            System.out.println("You have no transaction log.");
                        } else {
                            System.out.println("\n===== Your Log =====");

                            for (TransactionModel acc : myTrans) {
                                System.out.println(
                                        "Transaction Id: " + acc.getTransactionId() +
                                                " | Account Id: " + acc.getAccountId() +
                                                " | Type: " + acc.getType()+
                                                " | Amount: " + acc.getAmount()+
                                                " | Date: " + acc.getTimestamp()
                                );
                            }
                        }

                        break;
                    case "9":
                        TransactionService FtransactionService =new TransactionService();
                        System.out.print("\nEnter Account ID: ");
                        String fTranAccId = scanner.nextLine();
                        System.out.print("\nEnter date range from: ");
                        String datefrom = scanner.nextLine();
                        System.out.print("\nEnter date range to: ");
                        String dateto = scanner.nextLine();

                        LocalDateTime localdatefrom;
                        LocalDateTime localdateto;

                        try {
                            localdatefrom = LocalDateTime.parse(datefrom, FORMATTER);
                            localdateto   = LocalDateTime.parse(dateto, FORMATTER);
                        } catch (Exception e) {
                            System.out.println("Invalid date format. Use: yyyy-MM-dd HH:mm:ss");
                            return;
                        }

                        List<TransactionModel> myFTrans=FtransactionService.findFilterByAccountId(fTranAccId,localdatefrom,localdateto);
                        if (myFTrans.isEmpty()) {
                            System.out.println("You have no transaction log.");
                        } else {
                            System.out.println("\n===== Your Log =====");

                            for (TransactionModel acc : myFTrans) {
                                System.out.println(
                                        "Transaction Id: " + acc.getTransactionId() +
                                                " | Account Id: " + acc.getAccountId() +
                                                " | Type: " + acc.getType()+
                                                " | Amount: " + acc.getAmount()
                                                +
                                                " | Date: " + acc.getTimestamp()
                                );
                            }
                        }
                        break;
                    case "0":
                        System.out.println("Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }}


            break; // login successful, exit loop
        } else {
            attempts--;
            System.out.println("Password is not correct. Attempts left: " + attempts);
            if (attempts == 0) {
                System.out.println("Login failed. No attempts left.");
            }
        }
    }}
        public static String hashPassword(String plainPassword) {
            return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        }