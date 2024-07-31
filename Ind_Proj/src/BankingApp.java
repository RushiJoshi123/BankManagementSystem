
import java.util.* ;
import java.sql.* ;

public class BankingApp {
  static  int choice2 ;
  static String fullName ;
  static String phoneNumber ;
  static int age ;
  static String email ;
  static String pass ;
  static long accountNumber ;

    private static final String url = "jdbc:mysql://localhost:3306/banking_system";
    private static final String username = "root";
    private static final String password = "";
    public static Connection connection () throws Exception {
        Connection con = DriverManager.getConnection(url, username, pass);    
        return con ;
    }
    public static void viewAccounts ()throws Exception{
        System.out.println("Current Database : ");
        Connection con  = connection() ;
        Statement st = con.createStatement() ;
       String query = "select * from accounts" ;
       ResultSet res = st.executeQuery(query);
       System.out.println("+-----------------+---------------+-----------+----------------+----------------+");
       System.out.println("| Account Number  |   Full Name   |  Balance  |     E-mail     |  Security Pin  |");
       System.out.println("+-----------------+---------------+-----------+----------------+----------------+");
    while(res.next()) {
        System.out.println(String.format("| %-15d | %-13s | %-9d | %-14s | %-14s |",
        res.getInt("account_number"),     
        res.getString("full_name"), 
        res.getInt("balance"), 
        res.getString("email"),
        res.getString("security_pin")
       ));
 
    }
    System.out.println("+-----------------+---------------+-----------+----------------+----------------+");   
}


    public static void main(String[] args) throws Exception {
        System.out.println("*** WELCOME TO BANKING SYSTEM ***");
     LinkedList<UserDetails> userDetails = new LinkedList<>() ;
     Class.forName("com.mysql.cj.jdbc.Driver") ;
        Scanner sc = new Scanner(System.in) ;
            Connection connection = connection() ;
            Scanner scanner =  new Scanner(System.in);
            User user = new User(connection, scanner);
            Accounts accounts = new Accounts(connection, scanner);
            AccountManager accountManager = new AccountManager(connection, scanner);
           // UserDetails userDetails = new UserDetails(connection) ;

            String email;
            long account_number;

            while(true){
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. View Databases");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");
                int choice1 = scanner.nextInt();
                switch (choice1){
                    case 1:
                        user.register();
                        break;
                    case 2:
                        email = user.login();
                        if(email!=null){
                            System.out.println();
                            System.out.println("User Logged In!");
                            if(!accounts.account_exist(email)){
                                System.out.println();
                                System.out.println("1. Open a new Bank Account");
                                System.out.println("2. Exit");
                                if(scanner.nextInt() == 1) {
                                    account_number = accounts.open_account(email);
                                    System.out.println("Account Created Successfully");
                                    System.out.println("Your Account Number is: " + account_number);
                                }else
                                    break;
                                

                            }
                            account_number = accounts.getAccount_number(email);
                            
                            while (choice2 != 6) {
                                System.out.println();
                                System.out.println("1. Debit Money");
                                System.out.println("2. Credit Money");
                                System.out.println("3. Transfer Money");
                                System.out.println("4. Check Balance");
                                System.out.println("5. User details");
                                System.out.println("6. Log Out");
                                System.out.println("Enter your choice: ");
                                choice2 = scanner.nextInt();
                                switch (choice2) {
                                    case 1:
                                        accountManager.debit_money(account_number);
                                        break;
                                    case 2:
                                        accountManager.credit_money(account_number);
                                        break;
                                    case 3:
                                        accountManager.transfer_money(account_number);
                                        break;
                                    case 4:
                                        accountManager.getBalance(account_number);
                                        break;
                                    case 5:
                                   
                                    String query = "select * from accounts " ;
                                    Statement st = connection.createStatement() ;
                                    ResultSet res = st.executeQuery(query) ;
                                    while(res.next()){
                                    fullName = res.getString("full_name") ;
                                    email = res.getString("email");
                                    pass = res.getString("security_pin") ;
                                    accountNumber = res.getLong("account_number") ;
                                    userDetails.add(new UserDetails(fullName,email,pass,accountNumber)) ;
                                    }
                                 
                                    System.out.println("Enter email : ");
                                    String email2 = sc.nextLine() ;
                                    System.out.println("Here are the details ");
                                    System.out.println("+-------------+");
                                       UserDetails usd = new UserDetails() ;
                                       usd.printUserDetails(email2, userDetails);
                                       System.out.println("+-------------+");
                                        break;
                                    case 6 :
                                    System.out.println("ThankYou for Banking with us !");
                                    System.out.println("Exiting System ...");
                                    return ;
                                    default:
                                        System.out.println("Enter Valid Choice!");
                                        break;
                                }
                            }

                        }
                        else
                            System.out.println("Incorrect Email or Password!");
                        
                        break ;
                    case 3 :
                    viewAccounts() ;
                    break ;
                    case 4:
                        System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
                        System.out.println("Exiting System!");
                        return;
                    default:
                        System.out.println("Enter Valid Choice");
                        break;
                }
            }
       
    }
}