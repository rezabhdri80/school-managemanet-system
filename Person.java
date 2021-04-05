import java.util.Scanner;

public class Person {
    Scanner input = new Scanner(System.in);
   private String name;
   private String lastname;
   private String address;
   private String gender;
   private String username;
   private String schoolName;
   private String password;
   public void setName(String name){
       this.name=name;
   }
   public String getName(){
       return this.name;
   }
   public void setLastname(String lastname){
       this.lastname=lastname;
   }
   public String getLastname(){
       return this.lastname;
   }
   public void setAddress(String address){
       this.address=address;
   }
   public String getAddress(){
       return this.address;
   }
   public void setGender(String gender) {
       while (true) {
           if (gender.equals("male")) {
               this.gender = "male";
               break;
           } else if (gender.equals("female")) {
               this.gender = "female";
               break;
           }else {
               System.out.println("invalid input");
             String  gender2=input.nextLine();
             setGender(gender2);
               break;
           }
       }
   }
   public String getGender(){
       return this.gender;
   }
   public void setUsername(String username){
       this.username=username;
   }
   public String getUsername(){
       return this.username;
   }
   public void setPassword(String password){
       this.password=password;
   }
   public String getPassword(){return this.password;}
   public void setSchoolName(String schoolName){this.schoolName=schoolName;}
   public String getSchoolName(){return this.schoolName;}
}
