import java.util.Scanner;

public class utils
{
    public static void cls(){
        System.out.print("\f");
    }
    public static void typewriter(String text,int miliseconds){
        // So it won't overlap current message
        System.out.println();
        int i;
        for(i = 0; i < text.length(); i++){
            System.out.printf("%c", text.charAt(i));
            try{
                Thread.sleep(miliseconds);
            }catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }
    }
    public static void sleep(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }
    // Scanner.nextLine() function
    public static String string_input(String text,int milliseconds){
        typewriter(text+"\n",milliseconds);
        return new Scanner(System.in).nextLine();
    }
    // Scanner.nextInt() function
    public static int int_input(String text,int milliseconds){
        typewriter(text+"\n",milliseconds);
        return new Scanner(System.in).nextInt();
    }
    // Scanner.nextDouble() function
    public static double double_input(String text,int milliseconds){
        typewriter(text+"\n",milliseconds);
        return new Scanner(System.in).nextDouble();
    }
}
