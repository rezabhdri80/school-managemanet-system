import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
public class Student extends Person{
    Scanner input = new Scanner(System.in);
    String parentName;
    long parentPhoneCall;
    String birthday = null;
    String signDate;
    String ClassName;
    final private DateTimeFormatter date=  DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    final private LocalDateTime Date= LocalDateTime.now();
    public void setSignDate(){
        this.signDate=date.format(Date);
    }
    public String getSignDate(){
        return this.signDate;
    }
    public void setBirthday(String birthday) {
        while (this.birthday==null) {
            try {
                if(birthday.matches("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$")){
                    this.birthday=birthday;
                } else {
                    System.out.println("invalid type");
                    break;
                }
            } catch (Exception e) {
                System.out.println("invalid format for date");
            }
        }
    }
    public String getBirthday(){
        return this.birthday;
    }
    public void setParentName(String name){
        this.parentName=name;
    }
    public String getParentName(){
        return this.parentName;
    }
    public void setParentPhoneCall(long number){
        this.parentPhoneCall=number;
    }
    public long getParentPhoneCall(){
        return this.parentPhoneCall;
    }
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS student (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text,\n"
                + "	lastname text NOT NULL,\n"
                + "	address text NOT NULL,\n"
                + "	gender text NOT NULL,\n"
                + "	username text NOT NULL,\n"
                + "	password text NOT NULL,\n"
                + "	birthDay text NOT NULL,\n"
                + "	dateSign text NOT NULL,\n"
                + "	parentName text NOT NULL,\n"
                + "	parentPhone integer NOT NULL,\n"
                + "	schoolName text NOT NULL\n"
                + ");";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
            Statement st = conn.createStatement();
            st.execute(sql);
            System.out.println("table created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertData() {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
            String query = "INSERT INTO student(name,lastname,address,gender,username,password,birthDay,dateSign,parentname,parentPhone,schoolName) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, this.getName());
            pst.setString(2, this.getLastname());
            pst.setString(3, this.getAddress());
            pst.setString(4, this.getGender());
            pst.setString(5, this.getUsername());
            pst.setString(6, this.getPassword());
            pst.setString(7,   this.getBirthday());
            pst.setString(8, this.getSignDate());
            pst.setString(9, this.getParentName());
            pst.setLong(10, this.getParentPhoneCall());
            pst.setString(11,this.getSchoolName());
            pst.execute();
            System.out.println("data inserted successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void UpdateName(String NewName, String name) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
            String query = "UPDATE students SET  = ?"
                    + "WHERE username = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, NewName);
            pst.setString(2, name);
            pst.executeUpdate();
            System.out.println("data updated");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
/*
    public void UpdateID(int NewId, int ID) {
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
            String query = "UPDATE Schools SET id = ?"
                    + "WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, NewId);
            pst.setInt(2, ID);
            pst.executeUpdate();
            System.out.println("data updated");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int[] selectAll() {
        int[] ids = new int[100];
        String sql = "SELECT id, name, province,city,address,grade,manager FROM school";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            int i = 0;
            while (rs.next()) {
                ids[i] = rs.getInt("id");
                i++;
            }
        } catch (SQLException e) {
            System.out.println("issue while getting data");
        }
        return ids;
    }

  */

}




