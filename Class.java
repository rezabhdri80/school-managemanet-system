import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
public class Class {
   private String name;
   private String Schoolname;
    ArrayList<Lesson>lessons= new ArrayList<>();
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setSchoolname(String schoolname){
        this.Schoolname=schoolname;
    }
    public String getSchoolname(){
        return this.Schoolname;
    }
    public void createClassTable(){
        String sql = "CREATE TABLE IF NOT EXISTS class (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
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
            String query = "INSERT INTO class(name,schoolName) VALUES(?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, this.getName());
            pst.setString(2, this.getSchoolname());
            pst.execute();
            System.out.println("data inserted successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
