import java.text.SimpleDateFormat;
import java.util.Date;
/*
* User class to default an account or user
* */
public class User
{
    // The name of the user
    private String name;

    // The pin of the user
    private String pin;

    // The savings money of the user
    private double savings_money;

    // The receipt which would be used at the end

    private String logs;

    // The current money of the user
    private double current_money;

    // The card ID of the user
    private String card_id;

    // If the user exists or not
    private boolean exist;

    // If the user is active or not
    private boolean active=false;
    public User(String name,String pin,double savings_money,double current_money,String card_id,boolean exist) throws Exception {
        // Gives all the local variables some value
        this.name=name;
        this.card_id=this.check_id(card_id);
        this.pin=this.check_pin(pin);
        this.savings_money=savings_money;
        this.current_money=current_money;
        this.exist=exist;

        this.logs= "ABARABANKO\n"
                +"TIME: "+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date())+"\n"
                +"ACCOUNT NAME: "+this.name+"\n"
                +"CURRENT ACCOUNT: "+this.get_current()+"\n"
                +"SAVINGS ACCOUNT: "+this.get_savings()+"\n"
                +"---LOGS---\n";
    }
    // Setters and getters
    public boolean is_exist(){
        return this.exist;
    }
    public void enable(){
        this.active=true;
    }
    public void disable(){
        this.active=false;
    }
    public boolean is_active(){
        return this.active;
    }
    public void set_exist(boolean new_exist){
        this.exist=new_exist;
    }
    public String get_name(){
        return this.name;
    }
    public String get_id(){
        return this.card_id;
    }
    public void set_pin(String pin) throws Exception {
        this.pin=this.check_pin(pin);
    }
    public String get_pin(){
        return this.pin;   
    }

    // Deposits based on the mode and logs every time it does it
    public void deposit(double money,int mode) throws Exception {
        if (mode==1){
            this.log("DEPOSITED "+money+" AT CURRENT ACCOUNT");
            this.current_money+=money;
        } else if(mode==2){
            this.log("DEPOSITED "+money+" AT SAVINGS ACCOUNT");
            this.savings_money+=money;
        } else {
            throw new Exception("Mode of deposit for accounts is not allowed");
        }
    }
    public double get_savings(){
        return this.savings_money;
    }
    public double get_current(){
        return this.current_money;
    }

    // Withdraws based on the mode and logs every time it does it
    public void withdraw(double money,int mode) throws Exception {
        if (mode==1){
            this.log("WITHDRAWED "+money+" AT CURRENT ACCOUNT");
            this.current_money-=money;
        } else if(mode==2){
            this.log("WITHDRAWED "+money+" AT SAVINGS ACCOUNT");
            this.savings_money-=money;
        }
        else {
            throw new Exception("Mode of withdraw for accounts is not allowed");
        }
    }

    // Returns if you can withdraw according to your savings money
    public boolean can_withdraw(double money){
        return this.savings_money>money;
    }

    // Checks if the pin is equals to 6
    private String check_pin(String pin) throws Exception {
        if(!(pin.length()==6)){
            throw new Exception("Pin cannot be 6 less or more!");
        }
        return pin;
    }

    // Logs everything from the banl
    public void log(String log){
        this.logs+=log+"\n";
    }

    // Checks if the ID is equals to 16
    private String check_id(String id) throws Exception {
        if(!(id.length()==16)){
            throw new Exception("ID cannot be 16 less or more!");
        }
        return id;
    }

    // Returns a User that doesn't exist
    public static User default_user() throws Exception {
        return new User("John Doe","7087",5000,500,"1234567891011124",false);
    }

    // Returns the final receipt
    public String print_receipt(){
        this.log("----------");
        this.log("ACCOUNT NAME: "+this.name);
        this.log("CURRENT ACCOUNT: "+this.current_money);
        this.log("SAVINGS ACCOUNT: "+this.savings_money);
        this.log("ENDED USE AT "+new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
        return this.logs;
    }
}
