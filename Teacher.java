import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Teacher extends Staff{
    String level;
    String education;
    public void setLevel(String level){
        this.level=level;
    } public String getLevel(){
        return this.level;
    }
    public void setEducation(String education){
        this.education=education;
    } public String getEducation(){
        return this.education;
    }
    public static void createTeacherTable(){
        String sql = "CREATE TABLE IF NOT EXISTS teacher (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	lastname text NOT NULL,\n"
                + "	address text NOT NULL,\n"
                + "	gender text NOT NULL,\n"
                + "	username text NOT NULL,\n"
                + "	password text NOT NULL,\n"
                + "	staffNumber text NOT NULL,\n"
                + "	phoneNumber text NOT NULL,\n"
                + "	signDate text NOT NULL,\n"
                + "	salary integer NOT NULL,\n"
                + "	level text NOT NULL,\n"
                + "	schoolName text NOT NULL,\n"
                + "	education text NOT NULL\n"
                + ");";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
            Statement st = conn.createStatement();
            st.execute(sql);
            System.out.println("table created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public  void insertTeacherData(){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
            String query = "INSERT INTO teacher(name,lastname,address,gender,username,password,staffNumber,signDate,phoneNumber,level,education,schoolName,salary) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, this.getName());
            pst.setString(2, this.getLastname());
            pst.setString(3, this.getAddress());
            pst.setString(4, this.getGender());
            pst.setString(5, this.getUsername());
            pst.setString(6, this.getPassword());
            pst.setString(7, this.getStaffNumber());
            pst.setString(8, this.getDate());
            pst.setLong(9, this.getTelephone());
            pst.setString(10, this.getLevel());
            pst.setString(11, this.getEducation());
            pst.setString(12, this.getSchoolName());
            pst.setInt(13, this.getSalary());
            pst.execute();
            System.out.println("data inserted successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void editTeacher(){
        while(true){
            try {
                System.out.println("what are u willing to change");


            } catch (Exception e){
                System.out.println("invalid input");
            }
        }
    }
}
