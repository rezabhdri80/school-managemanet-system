import java.sql.*;

public class scores extends Lesson {
    String lessonName;
    String studentUserName;
    double score;
    public static void createScoreTable(){
        String sql = "CREATE TABLE IF NOT EXISTS scores (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	lessonName text,\n"
                + "	studentUserName text NOT NULL,\n"
                + "	className text NOT NULL,\n"
                + "	schoolName text NOT NULL,\n"
                + "	score real NOT NULL\n"
                + ");";
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
            Statement st = conn.createStatement();
            st.execute(sql);
            System.out.println("table created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setScore(){
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
            String query = "INSERT INTO scores(lessonName,studentUSerName,className,schoolName,score) VALUES(?,?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, this.lessonName);
            pst.setString(2, this.studentUserName);
            pst.setString(3, this.className);
            pst.setString(4, this.schoolName);
            pst.setDouble(5, this.score);
            pst.execute();
            System.out.println("data inserted successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void getScores(){
        String lessonName=null,studentUsername=null,className=null,schoolName=null;
        double sc=0;
        String sql = "SELECT * "
                + "FROM scores";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                lessonName = rs.getString("lessonName");
               studentUsername= rs.getString("studentUserName");
               className= rs.getString("className");
               schoolName= rs.getString("schoolName");
               sc=rs.getDouble("score");
                scores score = new scores();
                score.lessonName=lessonName;
                score.studentUserName=studentUsername;
                score.className=className;
                score.schoolName=schoolName;
                score.score=sc;
                Main.AllScores.add(score);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void setScoresForLesson(){
        try {
            if(Main.AllScores.size()==0){
                System.out.println("no score has been inserted");
            } else {
                for(int i=0;i<=Main.AllScores.size()-1;i++){
                    for(int j=0;j<=Main.AllLessons.size()-1;j++){
                        if(Main.AllScores.get(i).lessonName.equals(Main.AllLessons.get(j).lessonName) &&Main.AllScores.get(i).className.equals(Main.AllLessons.get(j).className)&& Main.AllScores.get(i).schoolName.equals(Main.AllLessons.get(j).schoolName)){
                                for(int p=0;p<=Main.AllStudents.size()-1;p++){
                                    if(Main.AllStudents.get(p).getUsername().equals(Main.AllScores.get(i).studentUserName)){
                                        Main.AllLessons.get(j).students.add(Main.AllStudents.get(p));
                                        Main.AllLessons.get(j).scores.add(Main.AllScores.get(i).score);
                                  }
                             }
                        }
                    }
                }

            }

        } catch (Exception e){
            System.out.println("no matching data");
        }
    }
}
