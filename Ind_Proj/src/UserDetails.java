
import java.sql.* ;
import java.util.* ;
//using linked list to implement to print the uer details 
//Linked list is of the Object of user details and there may have many more as such methods whihc resembles  

public class UserDetails {
    String full_name ;
    String email ;
    String password ;
    Connection connection ;
    long accountNumber ;
  
    public UserDetails() {}
   public UserDetails(String email , LinkedList<UserDetails> user) {
    printUserDetails(email, user);
   }
   
    public UserDetails(String full_name, String email, String password,long accountNumber) {
     this.full_name = full_name ;
     this.email = email ;
     this.password = password ;
     this.accountNumber = accountNumber ;

    
    }
    public void printUserDetails(String email2 , LinkedList<UserDetails> user) {
        //setting the account number : 
        if(user!=null) {
        for (UserDetails users : user) {
            if (users.getEmail().equals(email2)) {
                users.printDetails();
                return; 
            }
        }
    }
    else
        System.out.println("NO user found with email " +email);
    }

    public String getName() {
        return full_name;
    }
   public String getPassword() {
    return password ;
   }
   public long getAccountNumber() {
    return accountNumber ;
   }
    public String getEmail() {
        return email;
    }
   public String toString() {
    return 
   }
    public void printDetails() {
      
        System.out.println("Name: " + full_name);
        System.out.println("Email: " + email);
        System.out.println("Passoword : " + password);
        System.out.println("Account Number : " + accountNumber);
    }


}
