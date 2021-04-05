import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
public class Staff extends  Person{
  private String StaffNumber;
  private long telephone;
   final private DateTimeFormatter date=  DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
   final private LocalDateTime signDate= LocalDateTime.now();
   String Date;
   int salary;
   public void setDate(){
      this.Date=date.format(signDate);
   }
   public String getDate(){
      return this.Date;
   }
   public void setStaffNumber(String staffNumber){
      this.StaffNumber=staffNumber;
   }
   public String getStaffNumber(){
      return this.StaffNumber;
   }
   public void setTelephone(long telephone){
      this.telephone=telephone;
   }
   public long getTelephone(){
      return this.telephone;
   }
   public void setSalary(int salary){
      this.salary=salary;
   }
   public int getSalary(){
      return this.salary;
   }
}
