import java.sql.*;
import java.util.ArrayList;
import java.util.Queue;

public class sqliteSchool {
     static Connection conn= null;
    public static void connect(){
        try {
           // Class.forName("jdbc:sqlite:identifier.sqlite");
            conn= DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            System.out.println("connection was successful");

        } catch (Exception e){
            System.out.println(e);
        }
    }
    public static void createTable(String name){
        String sql = "CREATE TABLE IF NOT EXISTS Schools (\n"
                + "	id integer,\n"
                + "	name text NOT NULL,\n"
                + "	province text NOT NULL,\n"
                + "	city text NOT NULL,\n"
                + "	address text NOT NULL,\n"
                + "	grade text NOT NULL,\n"
                + "	manager text NOT NULL,\n"
                + ");";
        try( Connection conn=DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")){
            Statement st= conn.createStatement();
            st.execute(sql);
            System.out.println("table created");
        }catch (Exception e){
            System.out.println("error during creation of table");
        }
    }
    public static void insertData(int id , String name, String province, String city, String address, String grade, ArrayList<Student>students,ArrayList<Teacher>teachers,Manager manager){
       try(Connection conn=DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")){
           String query="INSERT INTO Schools(id,name,province,city,address,grade,manager) VALUES(?,?,?,?,?,?,?,?,?)";
           PreparedStatement pst=conn.prepareStatement(query);
           pst.setInt(1,id);
           pst.setString(2,name);
           pst.setString(3,province);
           pst.setString(4,city);
           pst.setString(4,address);
           pst.setString(5,grade);
           pst.setObject(8,manager);
           pst.execute();
           System.out.println("data inserted successfully");

       } catch (Exception e){
           System.out.println(e);
       }
    }
    public static void UpdateName(String NewName,String name ){
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")){
            String query="UPDATE Schools SET  = ?"
                    + "WHERE username = ?";
            PreparedStatement pst= conn.prepareStatement(query);
            pst.setString(1,NewName);
            pst.setString(2,name);
            pst.executeUpdate();
            System.out.println("data updated");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static void UpdateID(String NewId,String ID ){
        try(Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")){
            String query="UPDATE Schools SET id = ?"
                    + "WHERE id = ?";
            PreparedStatement pst= conn.prepareStatement(query);
            pst.setString(1,NewId);
            pst.setString(2,ID);
            pst.executeUpdate();
            System.out.println("data updated");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}

