import java.sql.*;
import java.util.Scanner;
public class Manager extends Teacher {
    Scanner input = new Scanner(System.in);

    public void addStudent() {
        Student student = new Student();
        System.out.println("enter the student name");


    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS manager (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text,\n"
                + "	lastname text NOT NULL,\n"
                + "	address text NOT NULL,\n"
                + "	gender text NOT NULL,\n"
                + "	username text NOT NULL,\n"
                + "	password text NOT NULL,\n"
                + "	staffNumber text NOT NULL,\n"
                + "	dateSign text NOT NULL,\n"
                + "	telePhone integer NOT NULL,\n"
                + "	level text NOT NULL,\n"
                + "	education text NOT NULL,\n"
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
            String query = "INSERT INTO manager(name,lastname,address,gender,username,password,staffNumber,dateSign,telePhone,level,education,schoolName) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
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
            pst.execute();
            System.out.println("data inserted successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*   public void UpdateName(String NewName, String name) {
           try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
               String query = "UPDATE Schools SET  = ?"
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
   */
    public String[] selectUsername() {
        String[] username = new String[100];
        String sql = "SELECT username FROM manager";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            int i = 0;
            while (rs.next()) {
                username[i] = rs.getString("username");
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return username;
    }


    public String[] selectPassword() {
        String[] password = new String[100];
        String sql = "SELECT password FROM manager";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            int i = 0;
            while (rs.next()) {
                password[i] = rs.getString("password");
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return password;
    }
    public String selectschoolName(String password){
        String SchoolName=null;
        String sql = "SELECT schoolName "
                + "FROM manager WHERE password = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,password);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
              SchoolName = rs.getString("schoolName");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return SchoolName;
    }

    public static Manager selectManager(String username){
        int id=0;
        long telePhone=0;
        String name=null,lastname=null,address=null,gender=null,username1=null,password=null,staffNumber=null,dateSign=null,education=null,level=null,schoolName=null;
        String ManagerName=null;
        String sql = "SELECT * "
                + "FROM manager WHERE username = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            // set the value
            pstmt.setString(1,username);
            //
            ResultSet rs  = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
               id = rs.getInt("id");
               name = rs.getString("name");
               lastname = rs.getString("lastname");
               address = rs.getString("address");
               gender = rs.getString("gender");
               username1 = rs.getString("username");
               password = rs.getString("password");
               staffNumber = rs.getString("staffNumber");
               dateSign = rs.getString("dateSign");
               telePhone = rs.getLong("telePhone");
               education = rs.getString("education");
               level = rs.getString("level");
               schoolName = rs.getString("schoolName");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Manager manager = new Manager();
        manager.setName(name);
        manager.setLastname(lastname);
        manager.setAddress(address);
        manager.setDate();
        manager.setUsername(username1);
        manager.setPassword(password);
        manager.setStaffNumber(staffNumber);
        manager.setTelephone(telePhone);
        manager.setEducation(education);
        manager.setLevel(level);
        manager.setSchoolName(schoolName);
        return manager;
    }
}
