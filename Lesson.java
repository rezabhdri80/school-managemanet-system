import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Lesson {
    Scanner input = new Scanner(System.in);
    String lessonName;
    String className;
    String schoolName;
    int sessions;
    int factor;
    double average=0;
    double standardDeviation=0;
    String[][]timeHeld= new String[10][10];
    ArrayList<Double>scores= new ArrayList<>();
    ArrayList<Student>students= new ArrayList<>();
    Teacher teacher = new Teacher();
    String teacherUSername=teacher.getUsername();
    public void setLessonName(String name){
        this.lessonName=name;
    }
    public String getLessonName(){
        return this.lessonName;
    }
    public void setSessions(int sessions){
        this.sessions=sessions;
    }
    public int getSessions(){
        return this.sessions;
    }
    public void setFactor(int factor){
        this.factor=factor;
    }
    public int getFactor(){
        return this.factor;
    }
    public void setAverage(){
        double sum=0;
        for(int i=0;i<=scores.size()-1;i++){
            sum+=scores.get(i);
        }
        double ave=sum/(double) scores.size();
        this.average=ave;
    }
    public double getAverage(){
        this.setAverage();
        return this.average;
    }
    public void setTimeHeld() {
        while (true) {
            System.out.println("how many sessions are held during the week(from 1 upto 3)?");
            try {
                String ses = input.nextLine();
                this.sessions=Integer.parseInt(ses);
                if(this.sessions>0 && this.sessions<=3) {
                    for (int i = 0; i < getSessions(); i++) {
                        System.out.println("what day?");
                        String day=input.nextLine();
                        while(true) {
                            if (day.equals("saturday") || day.equals("sunday") || day.equals("monday") || day.equals("tuesday") || day.equals("wednesday")) {
                                timeHeld[i][0] = day;
                                System.out.println("what time is it held?");
                                String hour = input.nextLine();
                                if (hour.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
                                    timeHeld[i][1] = hour;
                                    break;
                                } else {
                                    System.out.println("invalid day");
                                }
                            } else {
                                System.out.println("invalid day");
                                setTimeHeld();
                                break;

                            }
                        }
                    }
                } else {
                    System.out.println("invalid input");
                }
            } catch (Exception e){
                System.out.println("invalid input");
            }
            break;

        }
    }
    public String[][] getTimeHeld(){
            return this.timeHeld;
    }
    public void setStandardDeviation(){
        double sum=0;
        for(int i=0;i<=scores.size()-1;i++){
            double value=scores.get(i)-this.getAverage();
           sum+=Math.pow(value,2);
        }
       double upper= Math.pow(sum,0.5);
        this.standardDeviation=upper/this.scores.size();
    }
    public double getStandardDeviation(){
        this.setStandardDeviation();
        return this.standardDeviation;
    }
    public void createTableForLesson(){
        String sql = "CREATE TABLE IF NOT EXISTS lesson (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	lessonName text,\n"
                + "	className text NOT NULL,\n"
                + "	schoolName text NOT NULL,\n"
                + "	factor integer NOT NULL,\n"
                + "	teacherUsername text NOT NULL\n"
                + ");";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
            Statement st = conn.createStatement();
            st.execute(sql);
            System.out.println("table created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void insertLesson(){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
            String query = "INSERT INTO lesson(lessonName,className,schoolName,factor,teacherUsername) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, this.getLessonName());
            pst.setString(2, this.className);
            pst.setString(3, this.schoolName);
            pst.setInt(4, this.getFactor());
            pst.setString(5, this.teacher.getUsername());
            pst.execute();
            System.out.println("data inserted successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void getLesson(){
        int factor=0;
        int salary=0;
        long telePhone=0;
        String lessonName=null,className=null,schoolName=null,teacherUsername=null;
        String ManagerName=null;
        String sql = "SELECT * "
                + "FROM lesson";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                lessonName = rs.getString("lessonName");
                className= rs.getString("className");
                schoolName= rs.getString("schoolName");
                teacherUsername= rs.getString("teacherUsername");
                factor=rs.getInt("factor");
                Lesson lesson = new Lesson();
               lesson.setLessonName(lessonName);
               lesson.className=className;
               lesson.schoolName=schoolName;
               lesson.teacherUSername=teacherUsername;
               lesson.factor=factor;
               Main.AllLessons.add(lesson);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void setLessonsForSchools() {
        boolean found=false;
        try {
            if (Main.AllLessons.size() == 0 || Main.Allschools.size() == 0) {
                System.out.println("no lessons or school has been inserted yet");
            } else {
                for (int i = 0; i <= Main.AllLessons.size() - 1; i++) {
                    for (int j = 0; j <= Main.Allschools.size() - 1; j++) {
                        if (Main.Allschools.get(j).getName().equals(Main.AllLessons.get(i).schoolName)) {
                            for (int k = 0; k <= Main.Allschools.get(j).classes.size() - 1; k++) {
                                if(Main.Allschools.get(j).classes.get(k).getName().equals(Main.AllLessons.get(i).className)) {
                                    if(Main.Allschools.get(j).classes.get(k).lessons.size() !=0){
                                    for (int p = 0; p <= Main.Allschools.get(j).classes.get(k).lessons.size() - 1; p++) {
                                        for (int l = 0; l <= Main.AllTeachers.size() - 1; l++) {
                                            if (Main.AllLessons.get(i).teacherUSername.equals(Main.AllTeachers.get(l).getUsername())) {
                                                Main.AllLessons.get(i).teacher = Main.AllTeachers.get(l);
                                            }
                                        }
                                        if (Main.Allschools.get(j).classes.get(k).lessons.get(p).getLessonName().equals(Main.AllLessons.get(i).lessonName)) {
                                            found=true;
                                            Main.Allschools.get(j).classes.get(k).lessons.add(Main.AllLessons.get(i));
                                        }
                                    }
                                    if(!found){
                                        Main.Allschools.get(j).classes.get(k).lessons.add(Main.AllLessons.get(i));
                                    }
                                } else {
                                        for (int l = 0; l <= Main.AllTeachers.size() - 1; l++) {
                                            if (Main.AllLessons.get(i).teacherUSername.equals(Main.AllTeachers.get(l).getUsername())) {
                                                Main.AllLessons.get(i).teacher = Main.AllTeachers.get(l);
                                            }
                                        }
                                        Main.Allschools.get(j).classes.get(k).lessons.add(Main.AllLessons.get(i));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e){
            System.out.println("no data found");
        }
    }
}


