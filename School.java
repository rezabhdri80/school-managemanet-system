import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
public class School {
    Scanner input = new Scanner(System.in);
    private int id;
    private String name;
    private String province;
    private String city;
    private String address;
    private String grade;
    public ArrayList<Student> students = new ArrayList<>();
    public ArrayList<Teacher> teachers = new ArrayList<>();
    public ArrayList<Class>classes= new ArrayList<>();
    Manager manager = new Manager();

    public School(int id, String name, String province, String city, String address, String grade) {
        this.setId(id);
        this.setName(name);
        this.setProvince(province);
        this.setCity(city);
        this.setAddress(address);
        this.setGrade(grade);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return this.province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return this.grade;
    }

    static Connection conn = null;

    public void connect() {
        try {
            // Class.forName("jdbc:sqlite:identifier.sqlite");
            conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            System.out.println("connection was successful");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS school (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	idnum integer,\n"
                + "	name text NOT NULL,\n"
                + "	province text NOT NULL,\n"
                + "	city text NOT NULL,\n"
                + "	address text NOT NULL,\n"
                + "	grade text NOT NULL,\n"
                + "	manager text NOT NULL\n"
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
            String query = "INSERT INTO school(id,name,province,city,address,grade,manager) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, this.id);
            pst.setString(2, this.name);
            pst.setString(3, this.province);
            pst.setString(4, this.city);
            pst.setString(5, this.address);
            pst.setString(6, this.grade);
            pst.setObject(7, this.manager);
            pst.execute();
            System.out.println("data inserted successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void UpdateName(String NewName, String name) {
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

    public static String[] getSchoolName() {
        String[] names = new String[100];
        String sql = "SELECT name FROM school";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            int i = 0;
            while (rs.next()) {
                names[i] = rs.getString("name");
                i++;
            }
        } catch (SQLException e) {
            System.out.println("issue while getting data");
        }
        return names;

    }

    public void setStudent() {
        while (true) {
            try {
                Student student = new Student();
                System.out.println("enter the student name");
                student.setName(input.nextLine());
                System.out.println("enter the student lastname");
                student.setLastname(input.nextLine());
                System.out.println("enter the student address");
                student.setAddress(input.nextLine());
                System.out.println("enter the student gender");
                student.setGender(input.nextLine());
                while (student.birthday == null) {
                    System.out.println("enter the student birthday in following format dd/mm/yyyy");
                    student.setBirthday(input.nextLine());
                }
                System.out.println("enter the student username");
                student.setUsername(null);
                boolean foundUSer=false;
                while(student.getUsername()==null) {
                    String username=input.nextLine();
                    if(students.size()==0){
                        student.setUsername(username);
                        break;
                    }
                    for(int i=0;i<=Main.AllStudents.size()-1;i++){
                        if(students.get(i).getUsername().equals(username)){
                            System.out.println("this user already exists");
                            foundUSer=true;
                        }
                        if(!foundUSer){
                            student.setUsername(username);
                            break;
                        }
                    }
                }
                System.out.println("enter the student password");
                student.setPassword(input.nextLine());
              //  System.out.println("enter the school name");
                String schoolName = Main.logedinManager.getSchoolName();
                student.setSchoolName(schoolName);
                System.out.println("enter the parent name");
                student.setParentName(input.nextLine());
                System.out.println("enter the parent phone number");
                String phone = input.nextLine();
                long PhoneNumber = Long.parseLong(phone);
                student.setParentPhoneCall(PhoneNumber);
                System.out.println("enter your password");
                String password = input.nextLine();
                student.setSignDate();
                String managerSchoolname = manager.selectschoolName(password);
                if (Main.logedinManager.getSchoolName().equals(schoolName)) {
                    student.setSchoolName(manager.getSchoolName());
                    this.students.add(student);
                    Main.AllStudents.add(student);
                    student.createTable();
                    student.insertData();
                    System.out.println("student created");
                    break;
                } else {
                    System.out.println("you dont have permission so sign this student");
                }
            } catch (Exception e) {
                System.out.println("invalid input");
            }
        }
    }

    public static School findToLog(String name) {
        int id1 = 0;
        String nameOF = null, province = null, city = null, address = null, grade = null;
        String sql = "SELECT id, name, province,city,address,grade "
                + "FROM school WHERE name = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the value
            pstmt.setString(1, name);
            //
            ResultSet rs = pstmt.executeQuery();
            Manager manager;
            // loop through the result set
            while (rs.next()) {
                id1 = (rs.getInt("id"));
                nameOF = rs.getString("name");
                province = rs.getString("province");
                city = rs.getString("province");
                address = rs.getString("province");
                grade = rs.getString("province");
                manager = (Manager) rs.getObject("manager");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new School(id1, nameOF, province, city, address, grade);
    }

    public void editStudent() {
        while (true) {
            System.out.println("what are u willing to update?");
            System.out.println("1-name");
            System.out.println("2-lastname");
            System.out.println("3-username");
            System.out.println("4-password");
            System.out.println("5-Address");
            System.out.println("6-birth day");
            System.out.println("7-parent name");
            System.out.println("8-parent phone number");
            System.out.println("9-back");
            try {
                String opt1 = input.nextLine();
                int opt = Integer.parseInt(opt1);
                if (opt > 0 && opt <= 9) {
                    switch (opt) {
                        case 1:
                            boolean found1 = false;
                            System.out.println("enter the student username");
                            String username1 = input.nextLine();
                            for (int i = 0; i <= this.students.size() - 1; i++) {
                                if (this.students.get(i).getUsername().equals(username1)) {
                                    found1 = true;
                                    break;
                                }
                            }
                            if (found1) {
                                for (int i = 0; i <= students.size() - 1; i++) {
                                    if (students.get(i).getUsername().equals(username1) && this.getName().equals(students.get(i).getSchoolName())) {
                                        System.out.println("enter the new name");
                                        String newName = input.nextLine();
                                        students.get(i).setName(newName);
                                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                            String query = "UPDATE student SET name = ?"
                                                    + "WHERE username = ?";
                                            PreparedStatement pst = conn.prepareStatement(query);
                                            pst.setString(1, newName);
                                            pst.setString(2, username1);
                                            pst.executeUpdate();
                                            System.out.println("data updated");
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            } else {
                                System.out.println("student not found");
                                editStudent();
                                break;
                            }
                            break;
                        case 2:
                            System.out.println("enter the student username");
                            String username2 = input.nextLine();
                            boolean found2 = false;
                            for (int i = 0; i <= this.students.size() - 1; i++) {
                                if (students.get(i).getUsername().equals(username2)) {
                                    found2 = true;
                                    break;
                                }
                            }
                            if (found2) {
                                for (int i = 0; i <= students.size() - 1; i++) {
                                    if (students.get(i).getUsername().equals(username2) && this.getName().equals(students.get(i).getSchoolName())) {
                                        System.out.println("enter the new lastname");
                                        String newLastname = input.nextLine();
                                        students.get(i).setLastname(newLastname);
                                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                            String query = "UPDATE student SET lastname = ?"
                                                    + "WHERE username = ?";
                                            PreparedStatement pst = conn.prepareStatement(query);
                                            pst.setString(1, newLastname);
                                            pst.setString(2, username2);
                                            pst.executeUpdate();
                                            System.out.println("data updated");
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            } else {
                                System.out.println("student not found");
                                editStudent();
                                break;
                            }

                            break;
                        case 3:
                            System.out.println("enter the student username");
                            String username3 = input.nextLine();
                            boolean found3 = false;
                            for (int i = 0; i <= this.students.size() - 1; i++) {
                                if (students.get(i).getUsername().equals(username3)) {
                                    found3 = true;
                                    break;
                                }
                            }
                            if (found3) {
                                String newUsername=null;
                                for (int i = 0; i <= students.size() - 1; i++) {
                                    if (students.get(i).getUsername().equals(username3) && this.getName().equals(students.get(i).getSchoolName())) {
                                        System.out.println("enter the new username");
                                         newUsername = input.nextLine();
                                        students.get(i).setUsername(newUsername);
                                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                            String query = "UPDATE student SET username = ?"
                                                    + "WHERE username = ?";
                                            PreparedStatement pst = conn.prepareStatement(query);
                                            pst.setString(1, newUsername);
                                            pst.setString(2, username3);
                                            pst.executeUpdate();
                                            System.out.println("data updated");
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                            String query = "UPDATE scores SET studentUserName = ?"
                                                    + "WHERE studentUsername = ?";
                                            PreparedStatement pst = conn.prepareStatement(query);
                                            pst.setString(1, newUsername);
                                            pst.setString(2, username3);
                                            pst.executeUpdate();
                                            System.out.println("data updated");
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                                for(int i=0;i<=this.classes.size()-1;i++){
                                    for(int j=0;j<=this.classes.get(i).lessons.size()-1;j++){
                                        for(int k=0;k<=this.classes.get(i).lessons.get(j).students.size()-1;k++){
                                            this.classes.get(i).lessons.get(j).students.get(k).setUsername(newUsername);
                                        }
                                    }
                                }
                            } else {
                                System.out.println("student not found");
                                editStudent();
                                break;
                            }
                            break;
                        case 4:
                            System.out.println("enter the student username");
                            String username4 = input.nextLine();
                            boolean found4 = false;
                            for (int i = 0; i <= this.students.size() - 1; i++) {
                                if (students.get(i).getUsername().equals(username4)) {
                                    found4 = true;
                                    break;
                                }
                            }
                            if (found4) {
                                for (int i = 0; i <= students.size() - 1; i++) {
                                    if (students.get(i).getUsername().equals(username4) && this.getName().equals(students.get(i).getSchoolName())) {
                                        System.out.println("enter the new password");
                                        String newPassword = input.nextLine();
                                        students.get(i).setPassword(newPassword);
                                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                            String query = "UPDATE student SET password = ?"
                                                    + "WHERE username = ?";
                                            PreparedStatement pst = conn.prepareStatement(query);
                                            pst.setString(1, newPassword);
                                            pst.setString(2, username4);
                                            pst.executeUpdate();
                                            System.out.println("data updated");
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            } else {
                                System.out.println("student not found");
                                editStudent();
                                break;
                            }
                            break;
                        case 5:
                            System.out.println("enter the student username");
                            String username5 = input.nextLine();
                            boolean found5 = false;
                            for (int i = 0; i <= this.students.size() - 1; i++) {
                                if (students.get(i).getUsername().equals(username5)) {
                                    found5 = true;
                                    break;
                                }
                            }
                            if (found5) {
                                for (int i = 0; i <= students.size() - 1; i++) {
                                    if (students.get(i).getUsername().equals(username5) && this.getName().equals(students.get(i).getSchoolName())) {
                                        System.out.println("enter the new password");
                                        String newAddress = input.nextLine();
                                        students.get(i).setAddress(address);
                                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                            String query = "UPDATE student SET address = ?"
                                                    + "WHERE username = ?";
                                            PreparedStatement pst = conn.prepareStatement(query);
                                            pst.setString(1, newAddress);
                                            pst.setString(2, username5);
                                            pst.executeUpdate();
                                            System.out.println("data updated");
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            } else {
                                System.out.println("student not found");
                                editStudent();
                                break;
                            }
                            break;
                        case 6:
                            System.out.println("enter the student username");
                            String username6 = input.nextLine();
                            boolean found6 = false;
                            for (int i = 0; i <= this.students.size() - 1; i++) {
                                if (students.get(i).getUsername().equals(username6)) {
                                    found6 = true;
                                    break;
                                }
                            }
                            String newBirthDay;
                            if (found6) {
                                for (int i = 0; i <= students.size() - 1; i++) {
                                    if (students.get(i).getUsername().equals(username6) && this.getName().equals(students.get(i).getSchoolName())) {
                                        while (true) {
                                            System.out.println("enter the new birthday");
                                            newBirthDay = input.nextLine();
                                            if (newBirthDay.matches("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$")) {
                                                students.get(i).setBirthday(newBirthDay);
                                                break;
                                            } else {
                                                System.out.println("invalid input");
                                            }
                                        }
                                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                            String query = "UPDATE student SET birthDay = ?"
                                                    + "WHERE username = ?";
                                            PreparedStatement pst = conn.prepareStatement(query);
                                            pst.setString(1, newBirthDay);
                                            pst.setString(2, username6);
                                            pst.executeUpdate();
                                            System.out.println("data updated");
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            } else {
                                System.out.println("student not found");
                                editStudent();
                                break;
                            }
                            break;
                        case 7:
                            System.out.println("enter the student username");
                            String username7 = input.nextLine();
                            boolean found7 = false;
                            for (int i = 0; i <= this.students.size() - 1; i++) {
                                if (students.get(i).getUsername().equals(username7)) {
                                    found7 = true;
                                    break;
                                }
                            }
                            if (found7) {
                                for (int i = 0; i <= students.size() - 1; i++) {
                                    if (students.get(i).getUsername().equals(username7) && this.getName().equals(students.get(i).getSchoolName())) {
                                        System.out.println("enter the new parent name");
                                        String parentName = input.nextLine();
                                        students.get(i).setParentName(parentName);
                                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                            String query = "UPDATE student SET parentName = ?"
                                                    + "WHERE username = ?";
                                            PreparedStatement pst = conn.prepareStatement(query);
                                            pst.setString(1, parentName);
                                            pst.setString(2, username7);
                                            pst.executeUpdate();
                                            System.out.println("data updated");
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            } else {
                                System.out.println("student not found");
                                editStudent();
                                break;
                            }
                            break;
                        case 8:
                            System.out.println("enter the student username");
                            String username8 = input.nextLine();
                            boolean found8 = false;
                            for (int i = 0; i <= this.students.size() - 1; i++) {
                                if (students.get(i).getUsername().equals(username8)) {
                                    found8 = true;
                                    break;
                                }
                            }
                            if (found8) {
                                for (int i = 0; i <= students.size() - 1; i++) {
                                    if (students.get(i).getUsername().equals(username8) && this.getName().equals(students.get(i).getSchoolName())) {
                                        System.out.println("enter the new parent name");
                                        long parentNumber = input.nextLong();
                                        students.get(i).setParentPhoneCall(parentNumber);
                                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                            String query = "UPDATE student SET parentName parentPhone = ?"
                                                    + "WHERE username = ?";
                                            PreparedStatement pst = conn.prepareStatement(query);
                                            pst.setLong(1, parentNumber);
                                            pst.setString(2, username8);
                                            pst.executeUpdate();
                                            System.out.println("data updated");
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            } else {
                                System.out.println("student not found");
                                editStudent();
                                break;
                            }
                            break;
                        case 9:
                            break;
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void removeStudent() {
        while (true) {
            System.out.println("enter the student usernme");
            String username = input.nextLine();
            boolean found = false;
            for (int i = 0; i <= this.students.size() - 1; i++) {
                if (this.students.get(i).getUsername().equals(username)) {
                    found = true;
                }
            }
            if (found) {
                for(int i=0;i<=this.students.size()-1;i++){
                    if(username.equals(students.get(i).getUsername())){
                        students.remove(i);
                    }
                }
                for(int i=0;i<=this.classes.size()-1;i++){
                    for(int j=0;j<=this.classes.get(i).lessons.size()-1;j++){
                        for(int k=0;k<=this.classes.get(i).lessons.get(j).students.size()-1;k++){
                            this.classes.get(i).lessons.get(j).students.remove(k);
                            this.classes.get(i).lessons.get(j).scores.remove(k);
                        }
                    }
                }
                String sql = "DELETE FROM student WHERE username = ?";
                try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, username);
                    pstmt.executeUpdate();

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                String sql2 = "DELETE FROM scores WHERE studentUserName = ?";
                try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
                     PreparedStatement pstmt = conn.prepareStatement(sql2)) {
                    pstmt.setString(1, username);
                    pstmt.executeUpdate();

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            } else {
                System.out.println("student not found");
                removeStudent();
                break;
            }

        }
    }

    public void addTeacher() {
        while (true) {
            try {
                Teacher teacher = new Teacher();
                System.out.println("enter the teacher name");
                teacher.setName(input.nextLine());
                System.out.println("enter the teacher lastname");
                teacher.setLastname(input.nextLine());
                System.out.println("enter the teacher address");
                teacher.setAddress(input.nextLine());
                System.out.println("enter the teacher gender");
                teacher.setGender(input.nextLine());
                teacher.setUsername(null);
                boolean foundUser=false;
                while(teacher.getUsername()==null) {
                    System.out.println("enter the teacher username");
                    String username=input.nextLine();
                    if(teachers.size()==0){
                        teacher.setUsername(username);
                        break;
                    }
                    for(int i=0;i<=Main.AllTeachers.size()-1;i++){
                        if(username.equals(teachers.get(i).getUsername())){
                            System.out.println("this username already exists");
                            foundUser=true;
                        }
                        if(!foundUser) {
                            teacher.setUsername(username);
                            break;
                        }
                    }
                }
                System.out.println("enter the teacher password");
                teacher.setPassword(input.nextLine());
                System.out.println("enter the teacher staffNumber");
                teacher.setStaffNumber(null);
                boolean foundStaffNum=false;
                while(teacher.getStaffNumber()==null) {
                    String staffNumber=input.nextLine();
                    for(int i=0;i<=Main.AllTeachers.size()-1;i++){
                        if (Main.AllTeachers.get(i).getStaffNumber().equals(staffNumber)) {
                            foundStaffNum = true;
                            break;
                        }
                    }
                    if(!foundStaffNum){
                        teacher.setStaffNumber(staffNumber);
                    } else {
                        System.out.println("this number already exists");

                    }
                }
                teacher.setDate();
                System.out.println("enter the teacher telePhone");
                String tel = input.nextLine();
                teacher.setTelephone(Long.parseLong(tel));
                while(true) {
                    System.out.println("enter the teacher level pro/newbie");
                    String level=input.nextLine();
                    if(level.equals("pro")||level.equals("newbie")){
                        teacher.setLevel(level);
                        break;
                    } else {
                        System.out.println("invalid input");
                    }
                }
                while(true) {
                    System.out.println("enter the teacher education");
                    String edu= input.nextLine();
                    if(edu.equals("M.A")||edu.equals("P.H.D")||edu.equals("B.CH")){
                        teacher.education=edu;
                        break;
                    } else {
                        System.out.println("invalid input");
                    }
                }
              //  System.out.println("enter the teacher schoolName");
                teacher.setSchoolName(Main.logedinManager.getSchoolName());
                System.out.println("enter the teacher salary");
                String sal = input.nextLine();
                int salary = Integer.parseInt(sal);
                teacher.setSalary(salary);
                Teacher.createTeacherTable();
                teacher.insertTeacherData();
                Main.AllTeachers.add(teacher);
                this.teachers.add(teacher);
                break;
            } catch (Exception e) {
                System.out.println("invalid input");
            }
        }

    }
    public void editTeacher(){
        int holder=0;
        while(true){
            try {
                boolean found = false;
                System.out.println("enter teacher username");
                String username = input.nextLine();
                for (int i = 0; i <= teachers.size() - 1; i++) {
                    if (username.equals(teachers.get(i).getUsername())) {
                        found = true;
                        holder=i;
                    } else {
                        continue;
                    }
                }
                    if(found){
                        System.out.println("what are u willing to change");
                        System.out.println("1-name");
                        System.out.println("2-lastname");
                        System.out.println("3-address");
                        System.out.println("4-gender");
                        System.out.println("5-username");
                        System.out.println("6-password");
                        System.out.println("7-staff number");
                        System.out.println("8-phone number");
                        System.out.println("9-level");
                        System.out.println("10-education");
                        System.out.println("11-back");
                        found=true;
                        String opt1 = input.nextLine();
                        int opt= Integer.parseInt(opt1);
                        if (opt > 0 && opt <= 11) {
                            switch (opt) {
                                case 1:
                                    System.out.println("enter the new name");
                                    String newName=input.nextLine();
                                    teachers.get(holder).setName(newName);
                                    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                        String query = "UPDATE teacher SET name = ?"
                                                + "WHERE username = ?";
                                        PreparedStatement pst = conn.prepareStatement(query);
                                        pst.setString(1, newName);
                                        pst.setString(2, username);
                                        pst.executeUpdate();
                                        System.out.println("data updated");
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 2:
                                    System.out.println("enter the new lastname");
                                    String newlastName=input.nextLine();
                                    teachers.get(holder).setLastname(newlastName);
                                    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                        String query = "UPDATE teacher SET lastname = ?"
                                                + "WHERE username = ?";
                                        PreparedStatement pst = conn.prepareStatement(query);
                                        pst.setString(1, newlastName);
                                        pst.setString(2, username);
                                        pst.executeUpdate();
                                        System.out.println("data updated");
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 3:
                                    System.out.println("enter the new address");
                                    String newaddress=input.nextLine();
                                    teachers.get(holder).setAddress(newaddress);
                                    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                        String query = "UPDATE teacher SET address = ?"
                                                + "WHERE username = ?";
                                        PreparedStatement pst = conn.prepareStatement(query);
                                        pst.setString(1, newaddress);
                                        pst.setString(2, username);
                                        pst.executeUpdate();
                                        System.out.println("data updated");
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 4:
                                    System.out.println("enter the new gender");
                                    String newuserGender=input.nextLine();
                                    teachers.get(holder).setGender(newuserGender);
                                    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                        String query = "UPDATE teacher SET gender = ?"
                                                + "WHERE username = ?";
                                        PreparedStatement pst = conn.prepareStatement(query);
                                        pst.setString(1, newuserGender);
                                        pst.setString(2, username);
                                        pst.executeUpdate();
                                        System.out.println("data updated");
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 5:
                                    boolean foundUserName=false;
                                    while(true) {
                                        System.out.println("enter the new username");
                                        String newUsername = input.nextLine();
                                        for (int j = 0; j <= teachers.size() - 1; j++) {
                                            if (teachers.get(j).equals(newUsername)) {
                                                System.out.println("this username already exists");
                                                foundUserName = true;
                                            }
                                        }
                                        if (!foundUserName) {
                                            for(int i=0;i<=this.teachers.size()-1;i++){
                                                if(this.teachers.get(i).getUsername().equals(newUsername));
                                            }
                                            for(int i=0;i<=this.classes.size()-1;i++){
                                                for(int j=0;j<=this.classes.get(i).lessons.size()-1;j++){
                                                    if(this.classes.get(i).lessons.get(j).teacher.getUsername().equals(username)){
                                                        this.classes.get(i).lessons.get(j).teacher.setUsername(newUsername);
                                                    }
                                                }
                                            }
                                            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                                String query = "UPDATE teacher SET username = ?"
                                                        + "WHERE username = ?";
                                                PreparedStatement pst = conn.prepareStatement(query);
                                                pst.setString(1, newUsername);
                                                pst.setString(2, username);
                                                pst.executeUpdate();
                                                System.out.println("data updated");
                                                break;
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                                String query = "UPDATE scores SET teacherUsername = ?"
                                                        + "WHERE studentUsername = ?";
                                                PreparedStatement pst = conn.prepareStatement(query);
                                                pst.setString(1, newUsername);
                                                pst.setString(2, username);
                                                pst.executeUpdate();
                                                System.out.println("data updated");
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                    }
                                    break;
                                case 6:
                                    System.out.println("enter the new password");
                                    String newPassword=input.nextLine();
                                    teachers.get(holder).setName(newPassword);
                                    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                        String query = "UPDATE teacher SET password = ?"
                                                + "WHERE username = ?";
                                        PreparedStatement pst = conn.prepareStatement(query);
                                        pst.setString(1, newPassword);
                                        pst.setString(2, username);
                                        pst.executeUpdate();
                                        System.out.println("data updated");
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 7:
                                    boolean foundStaffnumber=false;
                                    while(true) {
                                        System.out.println("enter the new staff number");
                                        String newStaffnumber = input.nextLine();
                                        for (int j = 0; j <= teachers.size() - 1; j++) {
                                            if (newStaffnumber.equals(newStaffnumber)) {
                                                foundStaffnumber = true;
                                                System.out.println("this staffNumber already exists");
                                            }
                                        }
                                        if (!foundStaffnumber) {
                                            teachers.get(holder).setUsername(newStaffnumber);
                                            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                                String query = "UPDATE teacher SET name = ?"
                                                        + "WHERE username = ?";
                                                PreparedStatement pst = conn.prepareStatement(query);
                                                pst.setString(1, newStaffnumber);
                                                pst.setString(2, username);
                                                pst.executeUpdate();
                                                System.out.println("data updated");
                                                break;
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                        }
                                    }
                                    break;
                                case 8:
                                    System.out.println("enter the new phone number");
                                    String phoneNumber=input.nextLine();
                                    int phoneNumber2=Integer.parseInt(phoneNumber);
                                    teachers.get(holder).setTelephone(phoneNumber2);
                                    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                        String query = "UPDATE teacher SET phoneNumber = ?"
                                                + "WHERE username = ?";
                                        PreparedStatement pst = conn.prepareStatement(query);
                                        pst.setString(1, phoneNumber);
                                        pst.setString(2, username);
                                        pst.executeUpdate();
                                        System.out.println("data updated");
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;

                                case 9:
                                    System.out.println("enter the new level");
                                    String newLevel=input.nextLine();
                                    teachers.get(holder).setName(newLevel);
                                    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                        String query = "UPDATE teacher SET level = ?"
                                                + "WHERE username = ?";
                                        PreparedStatement pst = conn.prepareStatement(query);
                                        pst.setString(1, newLevel);
                                        pst.setString(2, username);
                                        pst.executeUpdate();
                                        System.out.println("data updated");
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 10:
                                    System.out.println("enter the new name");
                                    String newEducation=input.nextLine();
                                    teachers.get(holder).setName(newEducation);
                                    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                        String query = "UPDATE teacher SET education = ?"
                                                + "WHERE username = ?";
                                        PreparedStatement pst = conn.prepareStatement(query);
                                        pst.setString(1, newEducation);
                                        pst.setString(2, username);
                                        pst.executeUpdate();
                                        System.out.println("data updated");
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 11:
                                    break;
                            }
                        }break;
                    } else {
                        System.out.println("user not found");
                    }
                } catch(Exception e){
                    System.out.println(e);
                }
        }
    }
    public void removeTeacher(){
        while(true){
            System.out.println("enter the teacher username");
            String username=input.nextLine();
            boolean foundUser=false;
            for(int i=0;i<=teachers.size()-1;i++){
                if(teachers.get(i).getUsername().equals(username)){
                    foundUser=true;
                   this.teachers.remove(i);

                }
            }

            if(foundUser){
                String sql = "DELETE FROM teacher WHERE username = ?";
                try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, username);
                    pstmt.executeUpdate();

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                for(int i=0;i<=this.classes.size()-1;i++){
                    for(int j=0;j<=this.classes.get(i).lessons.size()-1;j++){
                        if(this.classes.get(i).lessons.get(j).teacher.getUsername().equals(username)){
                            this.classes.get(i).lessons.get(j).teacher=null;
                        }
                    }
                }
            } else {
                System.out.println("user not found");
            }
        }
    }
    public void editSalary(){
        boolean found=false;
        while(true){
            System.out.println("enter the teacher username");
            String username=input.nextLine();
            for(int i=0;i<=teachers.size()-1;i++){
                if(teachers.get(i).getUsername().equals(username)){
                    System.out.println("enter the new salary");
                    String newSalary1=input.nextLine();

                    int newSalary=Integer.parseInt(newSalary1);
                    found=true;
                    teachers.get(i).setSalary(newSalary);
                    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                        String query = "UPDATE teacher SET salary = ?"
                                + "WHERE username = ?";
                        PreparedStatement pst = conn.prepareStatement(query);
                        pst.setInt(1, newSalary);
                        pst.setString(2, username);
                        pst.executeUpdate();
                        System.out.println("data updated");
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                }
            } break;
        }
    }
    public void addClass(){
        boolean found=false;
        Class class1= new Class();
        while(true){
            System.out.println("enter the class name");
            String name = input.nextLine();
            for(int i=0;i<=classes.size()-1;i++){
                if(classes.get(i).getSchoolname().equals(this.getName()) && classes.get(i).getName().equals(name)){
                    found=true;
                }
            }
            if(!found){
                class1.setName(name);
                class1.setSchoolname(Main.logedinSchool.getName());
                classes.add(class1);
                class1.createClassTable();
                class1.insertData();
                break;
            } else {
                System.out.println("this school already exists");
                break;
            }

        }
    }
    public void lessonsOfTeacher(){
        if(this.teachers.size()==0){
            System.out.println("no teacher has been signed in this school");
        }
        while(true){
            boolean found =false;
            try{
                System.out.println("enter the teacher username");
                String teachUserName=input.nextLine();
                System.out.println(String.format("|%1$-10s|%2$-10s|%3$-20s|", "teacher name", "teacher lastname", "lesson name"));
                System.out.println(String.format("%s", "----------------------------------------------------------------------------"));
                for(int i=0;i<=this.classes.size()-1;i++){
                    for(int j=0;j<=this.classes.get(i).lessons.size()-1;j++){
                        if(teachUserName.equals(this.classes.get(i).lessons.get(j).teacher.getUsername())){
                            System.out.println(String.format("%1$-10s|%2$-10s|%3$-20s|%4$-20s|", classes.get(i).lessons.get(j).teacher.getName(), classes.get(i).lessons.get(j).teacher.getLastname(), classes.get(i).lessons.get(j).lessonName,classes.get(i).getName()));
                            System.out.println(String.format("%s","----------------------------------------------------------------------------"));
                            found=true;
                        }
                    }
                }
                if(!found){
                    System.out.println("no matching info found");
                    break;
                } else {
                    break;
                }

            } catch (Exception e){
                System.out.println("no data was found");
            }
        }
    }
    public void studentsOFLesson(){
        if(this.students.size()==0){
            System.out.println("this school has no students");
        }
        if(this.classes.size()==0){
            System.out.println("this school has no classes");
        }
        while(true){
            boolean found=false;
            try {
                System.out.println("enter the lesson name");
                String LessonUsername=input.nextLine();
                System.out.println(String.format("|%1$-10s|%2$-10s|", "lesson name","score"));
                System.out.println(String.format("%s", "---------------------------------------------------------"));
                for(int i=0;i<=this.classes.size()-1;i++){
                    for(int j=0;j<=this.classes.get(i).lessons.size()-1;j++){
                        if(classes.get(i).lessons.get(j).getLessonName().equals(LessonUsername)){
                        for(int k=0;k<=this.classes.get(i).lessons.get(j).students.size()-1;k++){
                                System.out.println(String.format("|%1$-10s|%2$-10s|%3$-20s|", this.classes.get(i).lessons.get(j).students.get(k).getLastname(), this.classes.get(i).lessons.get(j).scores.get(k),this.classes.get(i).lessons.get(j).getLessonName()));
                                System.out.println(String.format("%s", "--------------------------------------------------------------"));
                                found=true;
                            }
                        }
                    }
                }
                if(found){
                    break;
                }
                else {
                    System.out.println("no matching data for inserted info");
                    Main.managerLoged();
                    break;
                }
            } catch (Exception e){
                System.out.println("no data was found");
            }
        }
    }
    public void studentsOfClass(){
        if(this.classes.size()==0){
            System.out.println("this school has no classes");
        }
        while(true){
            ArrayList<Student>listOfClass=new ArrayList<>();
            ArrayList<Double>ListOFScores=new ArrayList<Double>();
            boolean found=false;
            try {
                System.out.println("enter the class name");
                String className=input.nextLine();
                for(int i=0;i<=this.classes.size()-1;i++){
                    if(classes.get(i).getName().equals(className)){
                        for(int j=0;j<=this.classes.get(i).lessons.size()-1;j++){
                            for(int k=0;k<=this.classes.get(i).lessons.get(j).students.size()-1;k++){
                                    if(!listOfClass.contains(this.classes.get(i).lessons.get(j).students.get(k))){
                                        listOfClass.add(this.classes.get(i).lessons.get(j).students.get(k));
                                        ListOFScores.add(this.classes.get(i).lessons.get(j).scores.get(k));
                                        found=true;
                                }
                            }
                        }
                    }
                }
                if(!found){
                    System.out.println("no matching data for given class");
                    break;
                } else {
                    for(int i=0;i<listOfClass.size();i++){
                        System.out.println(String.format("|%1$-15s|%2$-20s|%3$-25s|", listOfClass.get(i).getName(),listOfClass.get(i).getLastname(), ListOFScores.get(i)));
                        System.out.println(String.format("%s", "-------------------------------------------------------------------------"));

                    }
                    break;
                }
            } catch (Exception e){
                System.out.println("no data was found");
            }
        }
    }
    public void studentCurrentLessons(){
        if(this.classes.size()==0){
            System.out.println("no class has been created ");
        }
        if(this.students.size()==0){
            System.out.println("no student has been added yet");
        }
        while(true){
            boolean found=false;
            try {
                System.out.println("enter the student username");
                String studentUerName=input.nextLine();
                for(int i=0;i<=this.classes.size()-1;i++){
                    for(int j=0;j<=this.classes.get(i).lessons.size()-1;j++){
                        for(int k=0;k<=classes.get(i).lessons.get(j).students.size()-1;k++){
                            if(this.classes.get(i).lessons.get(j).students.get(k).getUsername().equals(studentUerName)){
                                System.out.println(String.format("|%1$-10s|%2$-10s|%3$-20s|",this.classes.get(i).lessons.get(j).students.get(k).getLastname() ,this.classes.get(i).lessons.get(j).getLessonName(), this.classes.get(i).lessons.get(j).scores.get(k)));
                                System.out.println(String.format("%s", "--------------------------------------------------------------"));
                                found=true;
                            }
                        }
                    }
                }
                if(!found){
                    System.out.println("no matching data was found");
                    break;
                } else {
                    break;
                }

            } catch (Exception e ){
                System.out.println("no data was found");
            }
        }
    }
    public void studentCurrentClasses(){
        if(this.classes.size()==0){
            System.out.println("no class has been inserted yet");
        }
        if(this.students.size()==0){
            System.out.println("no student has been inserted yet");
        }
        while(true){
            ArrayList<String>classes=new ArrayList<>();
            boolean found=false;
            try {
                System.out.println("enter the student username");
                String userName=input.nextLine();
                for(int i=0;i<=this.classes.size()-1;i++){
                    for(int j=0;j<=this.classes.get(i).lessons.size()-1;j++){
                        for(int k=0;k<=this.classes.get(i).lessons.get(j).students.size()-1;k++){
                            if(this.classes.get(i).lessons.get(j).students.get(k).getUsername().equals(userName)){
                                classes.add(this.classes.get(i).getName());
                                found=true;
                            }
                        }
                    }
                }
                if(!found){
                    System.out.println("no matchin data was found");
                } else {
                    if(classes.size()!=0){
                    for(int i=0;i<=classes.size()-1;i++) {
                        if (i != 0) {
                            if (!classes.get(i).equals(classes.get(i - 1))) {
                                System.out.println(classes.get(i));
                            }
                        } else {
                            System.out.println(classes.get(i));
                        }
                    }
                    } else {
                        System.out.println("");
                    }
                    break;
                }

            } catch (Exception e){
                System.out.println("no matching data was found");
            }
        }
    }
    public void studentScoreInLesson(){
        if(this.classes.size()==0){
            System.out.println("no class has been created yet");
        }
        if(this.students.size()==0){
            System.out.println("no student has been added yet");
        }
        while(true){
            boolean found=false;
            try {
                System.out.println("enter the student username");
                String studentUsername=input.nextLine();
                System.out.println("enter the lesson name");
                String lessonName=input.nextLine();
                for(int i=0;i<=this.classes.size()-1;i++){
                    for(int j=0;j<=this.classes.get(i).lessons.size()-1;j++){
                        if(this.classes.get(i).lessons.get(j).getLessonName().equals(lessonName)){
                            for(int k=0;k<=this.classes.get(i).lessons.get(j).students.size()-1;k++){
                                if(this.classes.get(i).lessons.get(j).students.get(k).getUsername().equals(studentUsername)){
                                    System.out.println(String.format("|%1$-10s|%2$-10s|%3$-20s|", this.classes.get(i).lessons.get(j).getLessonName(), this.classes.get(i).lessons.get(j).scores.get(k),this.classes.get(i).getName()));
                                    System.out.println(String.format("%s", "--------------------------------------------------------------"));
                                    found=true;
                                }
                            }
                        }
                    }
                }
                if(found){
                    break;
                } else {
                    System.out.println("no matching data");
                    break;
                }
            } catch (Exception e){
                System.out.println("no matching data");
            }
        }
    }
    public void averageAndDeviationOfLesson(){
        if(this.classes.size()==0){
            System.out.println("no class has been created");
        }
        if(this.students.size()==0){
            System.out.println("no student has been added");
        }
        while(true){
            boolean found=false;
            try {
                System.out.println("enter the lesson name");
                DecimalFormat dec= new DecimalFormat("#.###");
                String lessonName=input.nextLine();
                System.out.println(String.format("|%1$-15s|%2$-20s|%3$-25s|","average", "deviation","class name"));
                System.out.println(String.format("%s", "------------------------------------------------------------------------"));
                for(int i=0;i<=this.classes.size()-1;i++){
                    for(int j=0;j<=this.classes.get(i).lessons.size()-1;j++){
                        if(lessonName.equals(this.classes.get(i).lessons.get(j).getLessonName())){
                            System.out.println(String.format("|%1$-15s|%2$-20s|%3$-25s|", dec.format(this.classes.get(i).lessons.get(j).getAverage()), dec.format(this.classes.get(i).lessons.get(j).getStandardDeviation()),this.classes.get(i).getName()));
                            System.out.println(String.format("%s", "------------------------------------------------------------------------"));
                            found=true;
                        }
                    }
                } if(!found){
                    System.out.println("no matching data was found");
                    break;
                } else {
                    break;
                }
            } catch (Exception e){
                System.out.println("no matching data was found");
            }
        }
    }
    public void averageAndDeviationInClass(){
        if(this.classes.size()==0){
            System.out.println("no class has been created");
        }
        if(this.students.size()==0){
            System.out.println("no student has been added");
        }
        while(true){
            boolean found=false;
            try {
                System.out.println("enter the class name");
                String className=input.nextLine();
                System.out.println("enter the lesson name");
                String lessonName=input.nextLine();
                DecimalFormat dec=new DecimalFormat("#.###");
                System.out.println(String.format("|%1$-15s|%2$-20s|", "average ", "deviation"));
                System.out.println(String.format("%s", "-------------------------------------------------"));
                for(int i=0;i<=this.classes.size()-1;i++) {
                    if (className.equals(this.classes.get(i).getName())) {
                        for (int j = 0; j <= this.classes.get(i).lessons.size() - 1; j++) {
                            if (lessonName.equals(this.classes.get(i).lessons.get(j).getLessonName())) {
                                System.out.println(String.format("|%1$-15s|%2$-20s|", dec.format(this.classes.get(i).lessons.get(j).getAverage()), dec.format(this.classes.get(i).lessons.get(j).getStandardDeviation())));
                                System.out.println(String.format("%s", "-------------------------------------------------"));
                                found = true;
                            }
                        }
                    }
                }if(!found){
                    System.out.println("no matching data was found");
                    break;
                } else {
                    break;
                }
            } catch (Exception e){
                System.out.println("no matching data was found");
            }
        }
    }
    public void scoresInsertedByTeacher(){
        if(this.classes.size()==0){
            System.out.println("no class has been inserted");
        }
        if(this.students.size()==0){
            System.out.println("no student has been inserted");
        }
        while(true){
            boolean found=false;
            try {
                System.out.println("enter the teacher username");
                String teacherUserName=input.nextLine();
                System.out.println("enter the class name");
                String className=input.nextLine();
                System.out.println(String.format("|%1$-15s|%2$-20s|%3$-25s|%4$-30s|", "student name", "student lastname", "lesson name", "score"));
                System.out.println(String.format("%s", "-----------------------------------------------------------------------------------------------"));
                for(int i=0;i<=this.classes.size()-1;i++) {
                    for (int j = 0; j <= this.classes.get(i).lessons.size() - 1; j++) {
                        if(this.classes.get(i).getName().equals(className)){
                        for (int k = 0; k <= classes.get(i).lessons.get(j).scores.size() - 1; k++) {
                            if (this.classes.get(i).lessons.get(j).teacher.getUsername().equals(teacherUserName)) {
                                System.out.println(String.format("|%1$-15s|%2$-20s|%3$-25s|%4$-30s|", this.classes.get(i).lessons.get(j).students.get(k).getName(), this.classes.get(i).lessons.get(j).students.get(k).getLastname(), this.classes.get(i).lessons.get(j).getLessonName(), this.classes.get(i).lessons.get(j).scores.get(k)));
                                System.out.println(String.format("%s", "-----------------------------------------------------------------------------------------------"));
                                found = true;

                            }
                        }
                    }
                }
                }
                if(found){
                    break;
                } else {
                    System.out.println("no matching data ");
                    break;
                }

            } catch (Exception e){
                System.out.println("no matching data");
            }
        }

    }
    public void editClass(){
        if(this.classes.size()==0){
            System.out.println("no class was found");
        }
        while(true){
            try {
                System.out.println("enter the class name");
                String className=input.nextLine();
                System.out.println("enter the new class name");
                String newClassName=input.nextLine();
                for(int i=0;i<=this.classes.size()-1;i++){
                    if(this.classes.get(i).getName().equals(className)){
                        this.classes.get(i).setName(newClassName);
                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                            String query = "UPDATE class SET name= ?"
                                    + "WHERE name = ?";
                            PreparedStatement pst = conn.prepareStatement(query);
                            pst.setString(1, newClassName);
                            pst.setString(2, className);
                            pst.executeUpdate();
                            System.out.println("data updated");
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                            String query = "UPDATE lesson SET className= ?"
                                    + "WHERE className = ?";
                            PreparedStatement pst = conn.prepareStatement(query);
                            pst.setString(1, newClassName);
                            pst.setString(2, className);
                            pst.executeUpdate();
                            System.out.println("data updated");
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                            String query = "UPDATE scores SET className= ?"
                                    + "WHERE className = ?";
                            PreparedStatement pst = conn.prepareStatement(query);
                            pst.setString(1, newClassName);
                            pst.setString(2, className);
                            pst.executeUpdate();
                            System.out.println("data updated");
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    } else {
                        System.out.println("class not found");
                        break;
                    }
                } break;

            } catch (Exception e){
                System.out.println("invalid input");
                break;
            }
        }
    }
    public void removeClass(){
        if(this.classes.size()==0){
            System.out.println("no class was found");
        }
        while (true){
            try {
                System.out.println("enter the class name");
                String className=input.nextLine();
                for(int i=0;i<=this.classes.size()-1;i++){
                    if(className.equals(this.classes.get(i).getName())){
                        this.classes.remove(i);
                        String sql = "DELETE FROM class WHERE name = ?";
                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
                             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                            pstmt.setString(1, className);
                            pstmt.executeUpdate();

                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        String sql2 = "DELETE FROM lesson WHERE className = ?";
                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
                             PreparedStatement pstmt = conn.prepareStatement(sql2)) {
                            pstmt.setString(1, className);
                            pstmt.executeUpdate();

                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        String sql3 = "DELETE FROM scores WHERE className = ?";
                        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
                             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                            pstmt.setString(1, className);
                            pstmt.executeUpdate();
                            break;
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }


                    } else {
                        System.out.println("class not found");
                        break;
                    }
                    break;
                }

            } catch (Exception e){
                System.out.println("invalid input");
            }
        }
    }

}
