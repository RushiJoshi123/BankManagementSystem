
import java.util.* ;
import java.sql.* ;

public class User {
    private Connection connection;
    private Scanner scanner;
LinkedList <UserDetails> userDetailsLinkedList ;
    public User(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
  
    }
    public void register() throws Exception{
        Scanner sc= new Scanner(System.in) ;
        scanner.nextLine();
        userDetailsLinkedList = new LinkedList<>() ;
        System.out.print("Full Name: ");
        String full_name = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        System.out.print("Password : ");
        String password = scanner.nextLine();
        if(user_exist(email)) {
            System.out.println("User Already Exists for this Email Address!!");
            return;
        }
        String register_query = "INSERT INTO User(full_name, email, password) VALUES(?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(register_query);
            preparedStatement.setString(1, full_name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Registration Successfull!");
            } else {
                System.out.println("Registration Failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String login() throws Exception{
        scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        String login_query = "SELECT * FROM User WHERE email = ? AND password = ?";
       
            PreparedStatement preparedStatement = connection.prepareStatement(login_query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            return email ;
            else
            return null ;
    }
    

    public boolean user_exist(String email)throws Exception {
        String query = "SELECT * FROM user WHERE email = ?";
        
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
           return resultSet.next() ;
        
      
    }
}