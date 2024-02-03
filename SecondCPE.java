/**
 * Checkpoint Exam in FEU
 *
 * @author Gerro Abarabar
 * @version 2.0
 */
public class SecondCPE extends utils
{
    public static UserManager user_manager;

    // Makes a local variable user
    private static User user;

    // Makes a user that does not exist
    static {
        try {
            user = User.default_user();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Main function
    public static void main(String[] args) throws Exception {
        // Initializes the Account Manager
        user_manager=new UserManager();

        // Variable used for looping the program
        boolean using=true;

        // A loop that never ends until a user that exist is found
        while (!user.is_exist()) {
            cls();
            title();
            typewriter("Welcome to Abarabanko,",50);
            sleep(100);
            // Utilizes card id for finding the account
            String id= string_input("Please input your card id.",50);

            try {
                // Uses the user manager to find the user with id
                user = user_manager.find_user_with_id(id);
            } catch (Exception e) {
                // User manager throws an error if the id is not found which would trigger this.
                typewriter("User not found. Please try again", 10);
                sleep(1000);
            }
        }
        // When successful this would appear
        typewriter("User found!",50);
        sleep(500);

        // Loop that checks if a user is active locally and asks for pin to activate it
        while(!user.is_active()){
            cls();
            title();
            // Uses Scanner inside the if statement if it is equals to the user's pin
            if(string_input("Please input your pin",50).equals(user.get_pin())){
                typewriter("You may have access to your account, "+user.get_name(),50);
                // Enables the account to be used
                user.enable();
            } else {
                typewriter("Incorrect pin! Please try again.",50);
            }
            sleep(1000);
        }

        // Loop for the user to continue using the bank for as long as they want until they exit.
        while(using){
            cls();
            title();
            typewriter("Welcome to Abarabanko, "+user.get_name()+"!",50);
            sleep(1000);

            // Default mode for loop
            int mode=0;

            // Loops until user has given a valid input
            while(mode==0){
                cls();
                title();
                // Uses int_input to get the input from the user
                mode= int_input("What can I do for you today?\n1. Withdraw\n2. Deposit\n3. View Balance\n4. Exit",50);
                cls();
                if(mode ==1){
                    // Handles the withdraw
                    withdraw();
                } else if (mode==2){
                    // Handles all the deposit
                    deposit();
                } else if (mode==3) {
                    // Views the balance like: CURRENT SAVINGS ACCOUNT: 1000
                    view_balance();
                } else if (mode==4) {
                    // Exit
                    using=false;
                } else {
                    // When the user has made an invalid input
                    typewriter("Number is out of range! Please try again.",50);

                    // Puts the mode back to its default so it can loop one more time
                    mode=0;
                }
            }

            // When the program is done, it will ask if you want it to loop again. If not, then it will make `using` into false
            if (string_input("Would you like to continue this transaction?\n(y/n)",50).equals("n") && using){
                using=false;
            }
            cls();
        }
        // Asks if you want to print a receipt
        if (string_input("Would you like to print out a receipt?\n(y/n)",50).startsWith("y")){
            cls();
            typewriter(user.print_receipt(),1);
        }
    }

    // Prints out the title Abarabanko
    public static void title(){
        typewriter("           _                     _                 _         \n"
                    +"     /\\   | |                   | |               | |        \n"
                    +"    /  \\  | |__   __ _ _ __ __ _| |__   __ _ _ __ | | _____  \n"
                    +"   / /\\ \\ | '_ \\ / _` | '__/ _` | '_ \\ / _` | '_ \\| |/ / _ \\ \n"
                    +"  / ____ \\| |_) | (_| | | | (_| | |_) | (_| | | | |   | (_) |\n"
                    +" /_/    \\_|_.__/ \\__,_|_|  \\__,_|_.__/ \\__,_|_| |_|_|\\_\\___/ \n"
                    +"                                                             \n"
                    +"                                                             \n",1);
    }
    // The deposit handler
    public static void deposit() throws Exception {
        // Checks if the user if it exist instead of a fake one.
        if (!user.is_exist()){
            throw new Exception("User does not exist! User: "+user.get_name());
        }

        // Default mode for loop
        int mode = 0;

        // Loop for getting what the user wants: Current or Savings account
        while (mode==0) {
            title();
            mode = int_input("Where would you like to deposit to?\n1. Current Account\n2. Savings Account", 50);
            cls();

            // If statement handle the move
            if (mode==1){
                // Deposits to the curernt account
                title();
                typewriter("Current account balance: "+user.get_current(),50);
                user.deposit(double_input("How much would you like to deposit?",50),1);
            } else if (mode==2){
                // Deposits to the savings account
                title();
                typewriter("Savings account balance: "+user.get_savings(),50);
                user.deposit(double_input("How much would you like to deposit?",50),2);
            } else {
                // When the user puts a mode is not in the choices
                typewriter("Number "+mode+" is not in the choices.",50);
                mode=0;
            }
        }
    }
    // Shows the user their balance
    public static void view_balance(){
        title();
        typewriter("CURRENT ACCOUNT BALANCE: "+user.get_current(),50);
        typewriter("SAVINGS ACCOUNT BALANCE: "+user.get_savings(),50);
        user.log("VIEWED BALANCE");
    }
    // The withdraw handler
    public static void withdraw() throws Exception {
        if (!user.is_exist()){
            throw new Exception("User does not exist! User: "+user.get_name());
        }
        // Default mode for loop
        int mode = 0;

        // Loop for getting what the user wants: Current or Savings account
        while (mode==0) {
            title();
            mode = int_input("Where would you like to withdraw from?\n1. Current Account\n2. Savings Account", 50);
            cls();
            if (mode==1){
                // Current balance has unlimited transactions so it will not need any insufficient amount handler
                title();
                typewriter("Current account balance: "+user.get_current(),50);
                user.withdraw(double_input("How much would you like to withdraw?", 50),1);
            } else if (mode==2){
                // Savings account cannot withdraw an amount it cannot handle.

                // withdraw is big so that the loop can run once
                double withdraw=999999999;
                // Loops around a function that checks if the withdraw is something it can withdraw with savings account
                while(!(user.can_withdraw(withdraw))) {
                    cls();
                    title();
                    typewriter("Savings account balance: "+user.get_savings(),50);

                    withdraw=double_input("How much would you like to withdraw?", 50);

                    // If number cannot withdraw, it will say it is too much.
                    if (!user.can_withdraw(withdraw)){
                        typewriter("Amount too large!\nplease try again.",50);
                        sleep(1000);
                    }
                }
                cls();
                title();
                user.withdraw(withdraw,2);
            } else {
                // When the mode is not in the choices
                typewriter("Number "+mode+" is not in the choices.",50);

                // Returns mode to its default value to continue the loop
                mode=0;
            }
        }
    }
}