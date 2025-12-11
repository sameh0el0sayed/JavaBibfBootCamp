import com.Account.AccountModel;
import com.Account.AccountService;
import com.Account.AccountType;
import com.Customer.CustomerModel;
import com.Customer.CustomerService;
import com.Login.LoginSys;
import com.Login.AuthModel;
import com.Transaction.TransactionModel;
import com.Transaction.TransactionService;
import org.mindrot.jbcrypt.BCrypt;

private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
public static final String BLUE = "\u001B[34m";
public static final String RED = "\u001B[31m";
public static final String GREEN = "\u001B[32m";//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    //login into the system
    Scanner scanner = new Scanner(System.in);
    AuthModel auth = new AuthModel();
    int attempts = 3;
    boolean running = true;
    while (attempts > 0) {
        System.out.print(RED+"You have "+attempts+" attempts \n");
        System.out.print(GREEN+"Enter username: ");
        auth.setUserName(scanner.nextLine());

        System.out.print(GREEN+"Enter password: ");
        auth.setPassword(scanner.nextLine());

        if (LoginSys.login(auth)) {

            System.out.println(BLUE+"Hello and welcome! to your bank");

            CustomerService  customerService=new CustomerService();
            CustomerModel  customer=customerService.findByUsername(auth.getUserName());

            AccountService accountService=new AccountService(customer.getId());


            System.out.println(BLUE+customer.getFirstName());
            while (running) {System.out.println(BLUE+"\n===== Main Menu =====");
                System.out.println(BLUE+"1-Create customer account");
                System.out.println(BLUE+"2-Check my savings account balance");
                System.out.println(BLUE+"3-Check my checking account balance");
                System.out.println(BLUE+"4-My Accounts List");
                System.out.println(BLUE+"5-Make a transfer");
                System.out.println(BLUE+"6-Withdraw Money");
                System.out.println(BLUE+"7-Deposit Money");
                System.out.println(BLUE+"8-Transaction Log");
                System.out.println(BLUE+"9-Transaction Log with date filter");
                System.out.println(BLUE+"10-Check Customer Accounts by username");
                System.out.println(BLUE+"11-Create Account For Me");
                System.out.println(BLUE+"0-Exit");

                switch (scanner.nextLine()) {
                    case "1":
                        Scanner sc = new Scanner(System.in);

                        System.out.print(BLUE+"Enter ID: ");
                        String id = sc.nextLine();

                        System.out.print(BLUE+"Enter First Name: ");
                        String firstName = sc.nextLine();

                        System.out.print(BLUE+"Enter Middle Name (press Enter to skip): ");
                        String middleName = sc.nextLine();
                        if (middleName.isEmpty()) {
                            middleName = "";
                        }

                        System.out.print(BLUE+"Enter Last Name: ");
                        String lastName = sc.nextLine();

                        System.out.print(BLUE+"Enter Username: ");
                        String userName = sc.nextLine();

                        System.out.print(BLUE+"Enter Password: ");
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
                        System.out.println(RED+"Customer created successfully!");

                        break;
                    case "2":
                        System.out.println(RED+accountService.findSavingsAccount().getBalance());
                        break;
                    case "3":
                        System.out.println(RED+accountService.findCheckingAccount().getBalance());

                        break;
                    case "4":
                        ArrayList<AccountModel> myacc=accountService.findByUserId();
                        if (myacc.isEmpty()) {
                            System.out.println(RED+"You have no accounts.");
                        } else {
                            System.out.println(BLUE+"\n===== Your Accounts =====");

                            for (AccountModel acc : myacc) {
                                System.out.println(BLUE+
                                        "Account ID: " + acc.getAccountId() +
                                                " | Type: " + acc.getAccountType() +
                                                " | Balance: " + acc.getBalance()
                                );
                            }
                        }

                        break;
                    case "5":
                        System.out.println(BLUE+"\n===== Transfer Money =====");

                        System.out.print(BLUE+"\nEnter From Account ID: ");
                        String fromId = scanner.nextLine();

                        System.out.print(BLUE+"\nEnter To Account ID: ");
                        String toId = scanner.nextLine();

                        System.out.print(BLUE+"\nEnter Amount: ");
                        double amt = Double.parseDouble(scanner.nextLine());

                        try {
                            accountService.AmountTransfer(fromId, toId, amt);
                            System.out.println(GREEN+"Transfer successful!");
                        } catch (Exception ex) {
                            System.out.println(RED+"Transfer failed: " + ex.getMessage());
                        }
                        break;


                    case "6":
                        System.out.println(RED+"\n===== Withdraw Money =====");

                        System.out.print(BLUE+"\nEnter Account ID: ");
                        String accId = scanner.nextLine();

                        System.out.print(BLUE+"\nEnter Amount: ");
                        double monAmt = Double.parseDouble(scanner.nextLine());

                        try {
                            accountService.withdraw(accId, monAmt);
                            System.out.println(GREEN+monAmt+" Withdraw successful!");
                        } catch (Exception ex) {
                            System.out.println(RED+"Withdraw failed: " + ex.getMessage());
                        }
                        break;
                    case "7":
                        System.out.println(RED+"\n===== Deposit  Money =====");

                        System.out.print(BLUE+"\nEnter Account ID: ");
                        String daccId = scanner.nextLine();

                        System.out.print(BLUE+"\nEnter Amount: ");
                        double dmonAmt = Double.parseDouble(scanner.nextLine());

                        try {
                            accountService.deposit(daccId, dmonAmt);
                            System.out.println(GREEN+dmonAmt+" Deposit successful!");
                        } catch (Exception ex) {
                            System.out.println(RED+"Deposit failed: " + ex.getMessage());
                        }
                        break;
                    case "8":
                        TransactionService transactionService =new TransactionService();
                        System.out.print(BLUE+"\nEnter Account ID: ");
                        String TranAccId = scanner.nextLine();
                        List<TransactionModel> myTrans=transactionService.findByAccountId(TranAccId);
                        if (myTrans.isEmpty()) {
                            System.out.println(RED+"You have no transaction log.");
                        } else {
                            System.out.println(RED+"\n===== Your Log =====");

                            for (TransactionModel acc : myTrans) {
                                System.out.println(RED+
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
                        System.out.print(BLUE+"\nEnter Account ID: ");
                        String fTranAccId = scanner.nextLine();
                        System.out.print(BLUE+"\nEnter date range from: ");
                        String datefrom = scanner.nextLine();
                        System.out.print(BLUE+"\nEnter date range to: ");
                        String dateto = scanner.nextLine();

                        LocalDateTime localdatefrom;
                        LocalDateTime localdateto;

                        try {
                            localdatefrom = LocalDateTime.parse(datefrom, FORMATTER);
                            localdateto   = LocalDateTime.parse(dateto, FORMATTER);
                        } catch (Exception e) {
                            System.out.println(RED+"Invalid date format. Use: yyyy-MM-dd HH:mm:ss");
                            return;
                        }

                        List<TransactionModel> myFTrans=FtransactionService.findFilterByAccountId(fTranAccId,localdatefrom,localdateto);
                        if (myFTrans.isEmpty()) {
                            System.out.println(RED+"You have no transaction log.");
                        } else {
                            System.out.println(RED+"\n===== Your Log =====");

                            for (TransactionModel acc : myFTrans) {
                                System.out.println(BLUE+
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
                    case "10":
                        System.out.print(BLUE+"\nEnter User Name: ");
                        String accuserName = scanner.nextLine();
                        CustomerModel  findAcccustomer=customerService.findByUsername(accuserName);

                        ArrayList<AccountModel> custacc=accountService.findByUserId(findAcccustomer.getId());
                        if (custacc.isEmpty()) {
                            System.out.println(RED+"You have no accounts.");
                        } else {
                            System.out.println(BLUE+"\n===== "+findAcccustomer.getUserName()+" Accounts =====");

                            for (AccountModel acc : custacc) {
                                System.out.println(BLUE+
                                        "Account ID: " + acc.getAccountId() +
                                                " | User Full Name: " + findAcccustomer.getFirstName()+" "+findAcccustomer.getLastName() +
                                                " | Type: " + acc.getAccountType()
                                );
                            }
                        }
                        break;
                    case "11":

                        System.out.print(BLUE+"\nChoose Account Type:");
                        System.out.println("\n1-"+AccountType.SAVINGS.name());
                        System.out.println("\n2-"+AccountType.CHECKING.name());
                        AccountType acctype=AccountType.SAVINGS;

                        switch (scanner.nextLine()){
                            case "1":
                                acctype=AccountType.SAVINGS;

                                break;
                            case "2":
                                acctype=AccountType.CHECKING;


                                break;
                        }
                        AccountModel accountModel=new AccountModel(
                                "A"+1000 + new java.util.Random().nextInt(1000),
                                customer.getId(),
                                acctype,
                                0
                        );
                        accountService.create(accountModel);
                        System.out.println(GREEN+"Account Creation successful!");


                        break;
                    case "0":
                        System.out.println(BLUE+"Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println(RED+"Invalid choice. Try again.");
                }}


            break; // login successful, exit loop
        } else {
            attempts--;
            System.out.println(RED+"Password is not correct. Attempts left: " + attempts);
            if (attempts == 0) {
                System.out.println(RED+"Login failed. Account is locked for 1 min");
            }
        }
    }}
        public static String hashPassword(String plainPassword) {
            return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        }