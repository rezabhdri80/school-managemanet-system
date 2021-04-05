import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    //Hint:
    //this project uses sqlite as interface for databases
   static ArrayList<School>Allschools= new ArrayList<>();
   static ArrayList<Manager>Allmanagers= new ArrayList<>();
   static ArrayList<Student>AllStudents= new ArrayList<>();
   static ArrayList<Teacher>AllTeachers= new ArrayList<>();
   static ArrayList<Class>AllClasses= new ArrayList<>();
   static ArrayList<Lesson>AllLessons= new ArrayList<>();
   static ArrayList<scores>AllScores= new ArrayList<>();
   static ArrayList<Staff>AllStaff= new ArrayList<>();
   static School logedinSchool;
   static Manager logedinManager;
   static Teacher logedinTeacher;
   static Student logedinStudent;
   static Staff logedinStaff;
   static Scanner input = new Scanner(System.in);
   public static void getAllschoolsFromDatabase(){
       int id=0;
       long telePhone=0;
       String name=null,province=null,city=null,address=null,grade=null;
       String ManagerName=null;
       String sql = "SELECT * "
               + "FROM school;";

       try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

           // loop through the result set
           while (rs.next()) {
               id = rs.getInt("id");
               name = rs.getString("name");
               province = rs.getString("province");
               city = rs.getString("city");
               address = rs.getString("address");
               grade = rs.getString("grade");
              School school = new School(id,name,province,city,address,grade);
              Allschools.add(school);
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }
   public static void getAllmanagersFromDatabase(){
       int id=0;
       long telePhone=0;
       double salary=0;
       String name=null,lastname=null,address=null,gender=null,username1=null,password=null,staffNumber=null,dateSign=null,education=null,level=null,schoolName=null;
       String ManagerName=null;
       String sql = "SELECT * "
               + "FROM manager";

       try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

           // loop through the result set
           while (rs.next()) {
               id = rs.getInt("id");
               name = rs.getString("name");
               lastname = rs.getString("lastname");
               address = rs.getString("address");
               gender = rs.getString("gender");
               username1 = rs.getString("username");
               password = rs.getString("password");
               staffNumber= rs.getString("staffNumber");
               dateSign = rs.getString("dateSign");
               telePhone = rs.getLong("telePhone");
               education = rs.getString("education");
               level = rs.getString("level");
               schoolName = rs.getString("schoolName");
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
               manager.setSchoolName(schoolName);
               Allmanagers.add(manager);
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }
   public static void getAllTeachersFromDatabase(){
       int id=0;
       int salary=0;
       long telePhone=0;
       String name=null,lastname=null,address=null,gender=null,username1=null,password=null,staffNumber=null,dateSign=null,education=null,level=null,schoolName=null;
       String ManagerName=null;
       String sql = "SELECT * "
               + "FROM teacher";

       try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

           // loop through the result set
           while (rs.next()) {
               id = rs.getInt("id");
               name = rs.getString("name");
               lastname = rs.getString("lastname");
               address = rs.getString("address");
               gender = rs.getString("gender");
               username1 = rs.getString("username");
               password = rs.getString("password");
               staffNumber= rs.getString("staffNumber");
               telePhone = rs.getLong("phoneNumber");
               education = rs.getString("education");
               level = rs.getString("level");
               schoolName = rs.getString("schoolName");
               salary=rs.getInt("salary");
               Teacher teacher = new Manager();
               teacher.setName(name);
               teacher.setLastname(lastname);
               teacher.setAddress(address);
               teacher.setDate();
               teacher.setUsername(username1);
               teacher.setPassword(password);
               teacher.setStaffNumber(staffNumber);
               teacher.setTelephone(telePhone);
               teacher.setEducation(education);
               teacher.setGender(gender);
               teacher.setLevel(level);
               teacher.setSchoolName(schoolName);
               AllTeachers.add(teacher);
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }
   public static void getClassesFromDatabase(){
       int id=0;
       String name=null,schoolName=null;
       String ManagerName=null;
       String sql = "SELECT * "
               + "FROM class;";

       try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

           // loop through the result set
           while (rs.next()) {
               id = rs.getInt("id");
               name = rs.getString("name");
               schoolName=rs.getString("schoolName");
               Class class1 = new Class();
               class1.setName(name);
               class1.setSchoolname(schoolName);
               AllClasses.add(class1);
           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }
   }


   public static void setTeachersFroSchools(){
       for(int i=0;i<=Allschools.size()-1;i++){
           for(int j=0;j<AllTeachers.size();j++){
               if(Allschools.get(i).getName().equals(AllTeachers.get(j).getSchoolName())){
                   Allschools.get(i).teachers.add(AllTeachers.get(j));
               }
           }
       }

   }
   public static void setClassesforSchools(){
       for(int i=0;i<=AllClasses.size()-1;i++){
           for(int j=0;j<=Allschools.size()-1;j++){
               if(AllClasses.get(i).getSchoolname().equals(Allschools.get(j).getName())){
                   Allschools.get(j).classes.add(AllClasses.get(i));
               }
           }
       }
   }
   public static void setStuForScl(){
       for(int i=0;i<=Allschools.size()-1;i++){
           for(int j=0;j<=AllStudents.size()-1;j++){
               if(AllStudents.get(j).getSchoolName().equals(Allschools.get(i).getName())){
                   Allschools.get(i).students.add(AllStudents.get(j));
               }
           }
       }
   }
   public static void setMngForScl() {
       for (int i = 0; i <= Allschools.size() - 1; i++) {
           for (int j = 0; j <= Allmanagers.size() - 1; j++) {
               if (Allschools.get(i).getName().equals(Allmanagers.get(j).getSchoolName())) {
                   Allschools.get(i).manager = Allmanagers.get(j);
               }
           }
       }
   }
   //public void setTeacherforscl();
   //public void setStaffForscl();
   public static void getAllstudentsFromDatabase(){
       int id=0;
       long parentPhone=0;
       String name=null,lastname=null,address=null,gender=null,username1=null,password=null,birthDay=null,dateSign=null,parentName=null,schoolName=null;
       String ManagerName=null;
       String sql = "SELECT * "
               + "FROM student";

       try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
           while (rs.next()) {
               id = rs.getInt("id");
               name = rs.getString("name");
               lastname = rs.getString("lastname");
               address = rs.getString("address");
               gender = rs.getString("gender");
               username1 = rs.getString("username");
               password = rs.getString("password");
               birthDay = rs.getString("birthDay");
               dateSign = rs.getString("dateSign");
               parentName = rs.getString("parentName");
               parentPhone = rs.getInt("parentPhone");
               schoolName = rs.getString("schoolName");
               Student student = new Student();
               student.setName(name);
               student.setLastname(lastname);
               student.setAddress(address);
               student.setGender(gender);
               student.setUsername(username1);
               student.setPassword(password);
               student.setBirthday(birthDay);
               student.setSignDate();
               student.setParentName(parentName);
               student.setParentPhoneCall(parentPhone);
               student.setSchoolName(schoolName);
               AllStudents.add(student);

           }
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       }

   }
   public static void createClas(){
       ArrayList<scores>scores=new ArrayList<>();
       int index=0;
       Lesson lesson= new Lesson();
       lesson.teacher=logedinTeacher;
       String lessonsName=null;
       String className=null;
       while(true) {
           try {
               System.out.println("enter the lesson name");
               lessonsName = input.nextLine();
               if (lessonsName.matches("\\D+")) {
                   break;
               } else {
                   System.out.println("invalid input type");
               }

           } catch (Exception e) {
               System.out.println("invalid input");
           }
       }
       lesson.setTimeHeld();
       while(true){
           int number=0;
           boolean found =false;
           System.out.println("enter the class name");
           className=input.nextLine();
           for(int i=0;i<=logedinSchool.classes.size()-1;i++){
               if(className.equals(logedinSchool.classes.get(i).getName())){
                   lesson.schoolName=logedinTeacher.getSchoolName();
                   lesson.className=className;
                   lesson.setLessonName(lessonsName);
                   logedinSchool.classes.get(i).lessons.add(lesson);
                   index=i;
                   found=true;
               }
           }
           if(found){
               break;
           } else {
               System.out.println("this class has not been created");
               logedinSchool.classes.get(number).lessons.remove(lesson);
           }
       }
       while (true) {
           System.out.println("enter the factor of the lesson");
           String factor1= input.nextLine();
           int factor=Integer.parseInt(factor1);
           if(factor>0 && factor <=4){
               lesson.setFactor(factor);
               break;
           } else {
               System.out.println("invalid input");
               logedinSchool.classes.get(index).lessons.remove(lesson);
           }
       }
       ;
       while(true){
           int number=0;
           System.out.println("how many students does your class have?");
           String num=input.nextLine();
           int n=Integer.parseInt(num);
           if(n>0 && n<=100){
               boolean found=false;
               System.out.println("your class has "+ n +" students, enter their usernames");
               for(int i=0;i<n;i++){
                   System.out.println("enter the student username");
                   String stuUsername=input.nextLine();
                   for(int j=0;j<=logedinSchool.students.size()-1;j++){
                       if(logedinSchool.students.get(j).getUsername().equals(stuUsername)&& logedinSchool.students.get(j).getSchoolName().equals(logedinTeacher.getSchoolName())){
                           found=true;
                           lesson.students.add(logedinSchool.students.get(j));
                           scores score= new scores();
                           score.studentUserName=stuUsername;
                           scores.add(score);
                           number=j;
                           break;
                       }
                   }
                   if(!found){
                       System.out.println("student dosent exists");
                       lesson.students.remove(logedinSchool.students.get(number));
                       logedinSchool.classes.get(index).lessons.remove(lesson);
                       createClas();
                       break;
                   } else {
                       if(i==n-1){
                           break;
                       }

                   }
               }
           } break;
       }
       while(true) {
           boolean prob=false;
           try {
               System.out.println("enter scores for entered students");
               for (int i = 0; i <= lesson.students.size() - 1; i++) {
                   System.out.println("enter score of" + lesson.students.get(i).getName() + " " + lesson.students.get(i).getLastname());
                   while(true) {
                       double score = input.nextDouble();
                       if (score > 0 && score <= 20) {
                           lesson.scores.add(i, score);
                           scores.get(i).score=score;
                           break;
                       } else {
                           System.out.println("score must be between 0 to 20");
                       }
                   }
                   if(i==lesson.students.size()-1){
                       lesson.createTableForLesson();
                       lesson.insertLesson();
                       System.out.println("lesson has been created");
                       break;
                   }
               }
           } catch (Exception e){
               System.out.println("invalid input");
               prob=true;
           } break;
       }
       if(scores.size()!=0) {
           for (int i = 0; i <= scores.size() - 1; i++) {
               scores.get(i).className=className;
               scores.get(i).lessonName=lessonsName;
               scores.get(i).schoolName=logedinTeacher.getSchoolName();
               scores.get(i).createScoreTable();
               scores.get(i).setScore();
           }
       }
       TeacherLoged();
   }
   public static void TeacherLoged(){
       String lessonsName=null;
       while(true){
           System.out.println("1-create a lesson");
           System.out.println("2-edit scores of a lesson");
           System.out.println("3-remove scores of a lesson");
           System.out.println("4-show the scores of a lesson");
           System.out.println("5-show average and deviation");
           System.out.println("6-break");
           String opt1=input.nextLine();
           int opt=Integer.parseInt(opt1);
           if(opt>0 && opt<=6){
               try {
                   switch (opt){
                       case 1:
                          createClas();
                          break;
                       case 2:
                           while(true){
                               boolean found =false;
                               try {
                                   System.out.println("enter the student class name");
                                   String className=input.nextLine();
                                   for(int i=0;i<=logedinSchool.classes.size()-1;i++){
                                       if(className.equals(logedinSchool.classes.get(i).getName())){
                                           System.out.println("enter the lessons name");
                                           String lessonName=input.nextLine();
                                           for(int j=0;j<=logedinSchool.classes.get(i).lessons.size()-1;j++){
                                               if(lessonName.equals(logedinSchool.classes.get(i).lessons.get(j).lessonName)){
                                                   System.out.println("enter the student username");
                                                   String stuUsername=input.nextLine();
                                                   for(int k=0;k<=logedinSchool.classes.get(i).lessons.get(j).students.size();k++){
                                                       if(stuUsername.equals(logedinSchool.classes.get(i).lessons.get(j).students.get(k).getUsername())) {
                                                           System.out.println("enter the new score");
                                                           String newScore1 = input.nextLine();
                                                           double newScore =Double.parseDouble(newScore1);
                                                           if (newScore > 0 && newScore <= 20) {
                                                               logedinSchool.classes.get(i).lessons.get(j).scores.set(k,newScore);
                                                               System.out.println("score has been replaced");
                                                               found=true;
                                                                   try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite")) {
                                                                       String query = "UPDATE scores SET score = ?"
                                                                               + "WHERE studentUsername = ?";
                                                                       PreparedStatement pst = conn.prepareStatement(query);
                                                                       pst.setDouble(1, newScore);
                                                                       pst.setString(2, stuUsername);
                                                                       pst.executeUpdate();
                                                                       System.out.println("data updated");
                                                                   } catch (Exception e) {
                                                                       System.out.println(e.getMessage());
                                                                   }
                                                               break;
                                                           }
                                                       }
                                                   }
                                               }
                                           }
                                       }
                                   }
                                   if(!found){
                                       System.out.println("no matching data for inserted info");
                                   }
                                   else {
                                       break;
                                   }

                               } catch (Exception e){
                                   System.out.println("invalid input");
                               }
                           }
                           TeacherLoged();
                           break;
                       case 3:
                           while(true){
                               boolean found=false;
                               try {
                                   System.out.println("enter the class name");
                                   String className=input.nextLine();
                                   for(int i=0;i<=logedinSchool.classes.size()-1;i++){
                                       if(logedinSchool.classes.get(i).getName().equals(className)){
                                           System.out.println("enter the lesson name");
                                           String lessonName= input.nextLine();
                                           for(int j=0;j<=logedinSchool.classes.get(i).lessons.size()-1;j++){
                                               if(logedinSchool.classes.get(i).lessons.get(j).getLessonName().equals(lessonName)){
                                                   System.out.println("enter the student username");
                                                   String stuUsername=input.nextLine();
                                                   for(int k=0;k<=logedinSchool.classes.get(i).lessons.get(j).students.size()-1;k++){
                                                       if(logedinSchool.classes.get(i).lessons.get(j).students.get(k).equals(stuUsername)){
                                                           logedinSchool.classes.get(i).lessons.get(j).scores.set(k,null);
                                                           System.out.println("progress done");
                                                           found=true;
                                                           String sql = "DELETE FROM scores WHERE studentUerName = ?";

                                                           try (Connection conn = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
                                                                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                                                               // set the corresponding param
                                                               pstmt.setString(1,stuUsername);
                                                               // execute the delete statement
                                                               pstmt.executeUpdate();

                                                           } catch (SQLException e) {
                                                               System.out.println(e.getMessage());
                                                           }
                                                       }
                                                   }
                                               }
                                               }
                                           }

                                       }
                                   if(found){
                                       break;
                                   } else {
                                       System.out.println("no matching info was found for inserted data");
                                   }
                               } catch (Exception e){
                                   System.out.println("invalid input");
                               }
                           }
                           break;
                       case 4:
                           while(true){
                               boolean found=false;
                               try {
                                   System.out.println("enter the class name");
                                   String className=input.nextLine();
                                   for(int i=0;i<=logedinSchool.classes.size()-1;i++){
                                       if(logedinSchool.classes.get(i).getName().equals(className)){
                                           System.out.println("enter the lesson name");
                                           String lessonName=input.nextLine();
                                           for(int j=0;j<=logedinSchool.classes.get(i).lessons.size()-1;j++){
                                               if(logedinSchool.classes.get(i).lessons.get(j).lessonName.equals(lessonName)){
                                                   System.out.println(String.format("|%1$-15s|%2$-20s|%3$-6s|","name", "lastname","score"));
                                                   System.out.println(String.format("%s", "---------------------------------------------------------"));
                                                   for(int k=0;k<=logedinSchool.classes.get(i).lessons.get(j).students.size()-1;k++){
                                                       System.out.println(String.format("|%1$-15s|%2$-20s|%3$-6s|", logedinSchool.classes.get(i).lessons.get(j).students.get(k).getName(), logedinSchool.classes.get(i).lessons.get(j).students.get(k).getLastname(), logedinSchool.classes.get(i).lessons.get(j).scores.get(k)));
                                                       System.out.println(String.format("%s", "-------------------------------------------------------"));
                                                       found=true;

                                                   }
                                                   logedinSchool.classes.get(i).lessons.get(j).setAverage();
                                                   System.out.println("average of scores: "+logedinSchool.classes.get(i).lessons.get(j).getAverage());
                                               }
                                           }
                                       }
                                       }
                                   if(!found){
                                       System.out.println("no mathcing info was found");
                                   }
                                   if(found) {
                                       break;
                                   }
                               } catch (Exception e){
                                   System.out.println("invalid input");
                               }
                           }
                           TeacherLoged();
                           break;
                       case 5:
                           if(logedinSchool.teachers.size()==0){
                               System.out.println("there are no teachers inserted");
                               break;
                           }
                           if(logedinSchool.classes.size()==0){
                               System.out.println("no class has been created");
                               break;
                           }
                           while(true){
                               try {
                                   System.out.println(String.format("|%1$-15s|%2$-20s|%3$-25s|%3$-30s|", "name","average", "deviation","class name"));
                                   System.out.println(String.format("%s", "--------------------------------------------------------------------------------------------------------------------"));
                                   for(int i=0;i<=logedinSchool.classes.size()-1;i++) {
                                       for (int j = 0; j <= logedinSchool.classes.get(i).lessons.size() - 1; j++) {
                                           if (logedinTeacher == logedinSchool.classes.get(i).lessons.get(j).teacher){
                                               logedinSchool.classes.get(i).lessons.get(j).setAverage();
                                               logedinSchool.classes.get(i).lessons.get(j).setStandardDeviation();
                                               DecimalFormat dec= new DecimalFormat("#.###");
                                               System.out.println(String.format("|%1$-15s|%2$-20s|%3$-25s|%4$-30s|",logedinSchool.classes.get(i).lessons.get(j).getLessonName(),dec.format(logedinSchool.classes.get(i).lessons.get(j).getAverage()), dec.format(logedinSchool.classes.get(i).lessons.get(j).getStandardDeviation()),logedinSchool.classes.get(i).getName()));
                                               System.out.println(String.format("%s", "--------------------------------------------------------------------------------------------------------------------"));
                                           }
                                       }
                                   }
                                   break;

                               } catch (Exception e){
                                   System.out.println("invalid input");
                               }
                           }
                           TeacherLoged();
                           break;
                       case 6:
                           break;
                   } break;

               } catch (Exception e){
                   System.out.println("invalid input");
               }
           } else {
               System.out.println("invalid input");
           }
       }

   }
   public static void StudentLoged(){
       while(true){
           System.out.println("1-score of a specific lesson");
           System.out.println("2-average of selected scores scores");
           System.out.println("3-back");
           String opt1=input.nextLine();
           int opt=Integer.parseInt(opt1);
           if(opt>0 && opt<=3){
               try {
                   switch (opt){
                       case 1:
                           if(logedinSchool.classes.size()==0){
                               System.out.println("no class has been added");
                               break;
                           }
                           while(true){
                               boolean found=false;
                               try {
                                   System.out.println("enter the class name");
                                   String className =input.nextLine();
                                   System.out.println("enter the lesson name");
                                   String lessonName=input.nextLine();
                                  for(int i=0;i<=logedinSchool.classes.size()-1;i++){
                                      if(className.equals(logedinSchool.classes.get(i).getName())){
                                          for(int j=0;j<=logedinSchool.classes.get(i).lessons.size()-1;j++){
                                              if(lessonName.equals(logedinSchool.classes.get(i).lessons.get(j).getLessonName())){
                                                  for(int k=0;k<=logedinSchool.classes.get(i).lessons.get(j).students.size()-1;k++){
                                                      if(logedinStudent.getUsername().equals(logedinSchool.classes.get(i).lessons.get(j).students.get(k).getUsername())){
                                                          System.out.println(String.format("|%1$-15s|%2$-20s|%3$-25s|",logedinSchool.classes.get(i).lessons.get(j).getLessonName(), logedinSchool.classes.get(i).lessons.get(j).scores.get(k),logedinSchool.classes.get(i).getName()));
                                                          System.out.println(String.format("%s", "---------------------------------------------------------------------------"));
                                                      }
                                                  }
                                              }
                                          }
                                      }
                                  }
                                   break;
                               } catch (Exception e){
                                   System.out.println("invalid input");
                               }
                               if(!found){
                                   System.out.println("nothing was found");
                               }
                           }
                           StudentLoged();
                           break;
                       case 2:
                           if(logedinSchool.classes.size()==0){
                               System.out.println("no class has been added to school yet");
                           }
                           if(logedinSchool.students.size()==0){
                               System.out.println("no student has been added to thi school yet");
                           }
                           while(true){
                               boolean found =false;
                               double summation=0;
                               double factors=0;
                               try {
                                   System.out.println("number of the lessons you wanna get an average of:");
                                   String number=input.nextLine();
                                   int n=Integer.parseInt(number);
                                   for(int i=0;i<n;i++){
                                       System.out.println("enter the name of the class");
                                       String className=input.nextLine();
                                       System.out.println("enter the name of the lesson "+i+1);
                                       String lessonName=input.nextLine();
                                       for(int j=0;j<=logedinSchool.classes.size()-1;j++){
                                           if(className.equals(logedinSchool.classes.get(i).getName())){
                                               for(int k=0;k<=logedinSchool.classes.get(j).lessons.size()-1;k++){
                                                   if(logedinSchool.classes.get(j).lessons.get(k).getLessonName().equals(lessonName)){
                                                       for(int p=0;p<=logedinSchool.classes.get(j).lessons.get(k).students.size()-1;p++){
                                                           if(logedinSchool.classes.get(j).lessons.get(k).students.get(p).getUsername().equals(logedinStudent.getUsername())){
                                                               summation+=(logedinSchool.classes.get(i).lessons.get(j).scores.get(p)*logedinSchool.classes.get(i).lessons.get(j).factor);
                                                               factors+=logedinSchool.classes.get(i).lessons.get(j).factor;
                                                               found=true;
                                                           }
                                                       }
                                                   }
                                               }
                                           }
                                       }
                                   }
                                   if(found){
                                       double average=summation/factors;
                                       System.out.println("average :"+average);
                                       break;
                                   } else {
                                       System.out.println("no matching data");
                                       break;
                                   }

                               } catch (Exception e){
                                   System.out.println("no matching data");

                               }
                           }
                           StudentLoged();
                           break;
                       case 3:
                           break;
                   }

               } catch (Exception e){
                   System.out.println("invalid input");
               }
           } else {
               System.out.println("invalid input");
           } break;
       }
   }

   public static void managerLoged(){
       while(true){
               try {
                   System.out.println("1-add student");
                   System.out.println("2-edit student");
                   System.out.println("3-remove student");
                   System.out.println("4-add teacher");
                   System.out.println("5-edit teacher");
                   System.out.println("6-remove teacher");
                   System.out.println("7-change teacher salary");
                   System.out.println("8-lessons of a teacher");
                   System.out.println("9-students of a lesson");
                   System.out.println("10-students of a class");
                   System.out.println("11-a student current classes");
                   System.out.println("12-a student current lessons");
                   System.out.println("13- a student scores in a lessons");
                   System.out.println("14-average and deviation of a lesson");
                   System.out.println("15-average and deviation of a class in a lesson");
                   System.out.println("16-all scores and details inserted by teacher of a class");
                   System.out.println("17-add a class");
                   System.out.println("18-edit a class");
                   System.out.println("19-remove a class");
                   System.out.println("20-back");
                   int inpt=input.nextInt();
                   if(inpt>0 && inpt<=20){
                       switch (inpt){
                           case 1:
                               logedinSchool.setStudent();
                               managerLoged();
                               break;
                           case 2:
                               logedinSchool.editStudent();
                               managerLoged();
                               break;
                           case 3:
                               logedinSchool.removeStudent();
                               managerLoged();
                               break;
                           case 4:
                               logedinSchool.addTeacher();
                               managerLoged();
                               break;
                           case 5:
                               logedinSchool.editTeacher();
                               managerLoged();
                               break;
                           case 6:
                               logedinSchool.removeTeacher();
                               managerLoged();
                               break;
                           case 7:
                               logedinSchool.editSalary();
                               managerLoged();
                               break;
                           case 8:
                               logedinSchool.lessonsOfTeacher();
                               managerLoged();
                               break;
                           case 9:
                               logedinSchool.studentsOFLesson();
                               managerLoged();
                               break;
                           case 10:
                               logedinSchool.studentsOfClass();
                               managerLoged();
                               break;
                           case 11:
                               logedinSchool.studentCurrentClasses();
                               managerLoged();
                               break;
                           case 12:
                               logedinSchool.studentCurrentLessons();
                               managerLoged();
                               break;
                           case 13:
                               logedinSchool.studentScoreInLesson();
                               managerLoged();
                               break;
                           case 14:
                               logedinSchool.averageAndDeviationOfLesson();
                               managerLoged();
                               break;
                           case 15:
                               logedinSchool.averageAndDeviationInClass();
                               managerLoged();
                               break;
                           case 16:
                               logedinSchool.scoresInsertedByTeacher();
                               managerLoged();
                               break;
                           case 17:
                               logedinSchool.addClass();
                               managerLoged();
                               break;
                           case 18:
                               logedinSchool.editClass();
                               managerLoged();
                               break;
                           case 19:
                               logedinSchool.removeClass();
                               managerLoged();
                           case 20:
                               break;
                       }
                   }break;


               } catch (Exception e){
                   System.out.println("invalid input");
               }
       }
   }
   public static void findManagerLoged(String username,String password){
       for(int i=0;i<Allmanagers.size()-1;i++){
           if(Allmanagers.get(i).getUsername().equals(username) && Allmanagers.get(i).getPassword().equals(password)){
               logedinManager=Allmanagers.get(i);
           }
       }
   }
   public static void Login(){
       while(true) {
           try {
               System.out.println("log in as:");
               System.out.println("a-manager");
               System.out.println("b-teacher");
               System.out.println("c-student");
               System.out.println("d-exit");
               char opt = input.nextLine().charAt(0);
               if(opt=='a'||opt=='b'||opt=='c'||opt=='d'||opt=='e') {
                   boolean found = false;

                   switch (opt) {
                       case 'a':
                           while (!found) {
                               System.out.println("enter your username");
                               String username = input.nextLine();
                               Manager manager = Manager.selectManager(username);
                               System.out.println("enter your password");
                               String password = input.nextLine();
                               for (int i = 0; i <= Allmanagers.size()-1; i++) {
                                   if (Allmanagers.get(i).getUsername().equals(username) && Allmanagers.get(i).getPassword().equals(password)) {
                                               System.out.println("you successfully logged in");
                                               logedinManager=Allmanagers.get(i);
                                               found = true;
                                               if (found) {
                                                   for(int j=0;j<=Allschools.size()-1;j++){
                                                       if(Allschools.get(j).getName().equals(logedinManager.getSchoolName())){
                                                           logedinSchool=Allschools.get(j);
                                                           logedinSchool.manager=logedinManager;
                                                           managerLoged();
                                                           break;
                                                       }
                                                   }
                                                   break;
                                               }
                                   }
                               }
                           }
                           break;
                       case 'b':
                           while(!found) {
                               System.out.println("enter your username");
                               String username = input.nextLine();
                               System.out.println("enter your password");
                               String password = input.nextLine();
                               for (int i = 0; i <= AllTeachers.size() - 1; i++) {
                                   if (AllTeachers.get(i).getUsername().equals(username) && AllTeachers.get(i).getPassword().equals(password)) {
                                       logedinTeacher = AllTeachers.get(i);
                                       found = true;
                                       for (int j = 0; j <= Allschools.size()-1; j++) {
                                           if (Allschools.get(j).getName().equals(logedinTeacher.getSchoolName())) {
                                               logedinSchool = Allschools.get(j);
                                           }
                                       }
                                   }
                               }
                               if (found) {
                                   System.out.println("you successfully logged in");
                                   TeacherLoged();
                                   break;
                               } else {
                                   System.out.println("teacher not found");
                               }
                           }
                           break;
                       case 'c':
                           while(!found) {
                               System.out.println("enter username");
                               String username = input.nextLine();
                               System.out.println("enter password");
                               String password = input.nextLine();
                               for (int i = 0; i <= AllStudents.size() - 1; i++) {
                                   if (AllStudents.get(i).getUsername().equals(username) && AllStudents.get(i).getPassword().equals(password)) {
                                       logedinStudent = AllStudents.get(i);
                                       found = true;
                                       for(int j=0;j<=Allschools.size()-1;j++){
                                           if(Allschools.get(j).getName().equals(logedinStudent.getSchoolName())){
                                               logedinSchool=Allschools.get(j);
                                           }
                                       }
                                   }
                               }
                               if(found){
                                   System.out.println("you successfully logged in");
                                   StudentLoged();
                                   break;
                               } else {
                                   System.out.println("student not found");
                               }
                           }
                           break;
                       case 'd':
                           break;
                   }
                   break;
               }
           } catch (Exception e ){
               System.out.println("invalid input");
           }
               }
       }
   public static void mainMenu(){
       while(true) {
           System.out.println("select an option");
           System.out.println("a-add a new school");
           System.out.println("b-log in");
           System.out.println("c-exit ");
           try {
               char option = input.nextLine().charAt(0);
               if (option == 'a' || option == 'b' || option == 'c') {
                   switch (option) {
                       case 'a':
                           signSchool();
                           mainMenu();
                           break;
                       case 'b':
                           Login();
                           mainMenu();
                           break;
                       case 'c':
                           break;
                   } break;
               } else {
                   System.out.println("invalid input");
               }
           } catch (Exception e){
               System.out.println("invalid input");
           }
       }
   }
   public static void signSchool() {
       String name = null;
       int id = 0;
       String provinceName = null;
       String cityName = null;
       String address = null;
       String grade = null;
       while (name == null) {
           boolean found=false;
           System.out.println("enter the name of school");
           try {
                   name = input.nextLine();
                   for(int i=0;i<=Allschools.size()-1;i++){
                       if(Allschools.get(i).getName().equals(name)){
                           found=true;
                           System.out.println("this school already exists");
                           signSchool();
                           break;
                       }
                   }
           } catch (Exception e) {
               System.out.println("name must be String");
           }
       }
       while (id == 0) {
           boolean found=false;
           System.out.println("enter the institution id");
           try {
               String id2 = input.nextLine();
              int idInt= Integer.parseInt(id2);
               if(Allschools.size()!=0){
                   for(int i=0;i<=Main.Allschools.size()-1;i++){
                       if(Main.Allschools.get(i).getId()==id){
                           found=true;
                       }
                   }
                   if(!found){
                       id=idInt;
                   } else {
                       System.out.println("this id already exists");
                   }
               }
           } catch (Exception e) {
               System.out.println("id must be of Integer type");
           }
       }
       while (provinceName == null) {
           System.out.println("enter the province name ");
           try {
               provinceName = input.nextLine();
           } catch (Exception e) {
               System.out.println("province name must be of String");
           }
       }
       while (cityName == null) {
           System.out.println("enter the city name");
           try {
               cityName = input.nextLine();
           } catch (Exception e) {
               System.out.println("city name must be of String type");
           }
       }
       while (address == null) {
           System.out.println("enter the address of your school");
           try {
               address = input.nextLine();
           } catch (Exception e) {
               System.out.println("invalid address");
           }
       }
       while (grade == null) {
           System.out.println("enter the grade of school");
           System.out.println("a-elementary");
           System.out.println("b-intermediate(junior high)");
           System.out.println("c-advanced(senior high )");
           try {
               char gr = input.nextLine().charAt(0);
               if(gr=='a'||gr=='b'||gr=='c') {
                   switch (gr) {
                       case 'a':
                           grade = "elementary";
                           break;
                       case 'b':
                           grade = "junior high school";
                           break;
                       case 'c':
                           grade = "senior high school";
                           break;
                   }
               }
           } catch (Exception e) {
               System.out.println("invalid grade");
           }
       }
       Manager manager= new Manager();
       manager.setSchoolName(name);
       while(true) {
           try {
               System.out.println("enter the manager name");
               manager.setName(input.nextLine());
               System.out.println("enter the manager lastname");
               manager.setLastname(input.nextLine());
               System.out.println("enter the manager address");
               manager.setAddress(input.nextLine());
               System.out.println("enter the manager gender");
               manager.setGender(input.nextLine());
               while(true) {
                   boolean found=false;
                   System.out.println("enter the manager username");
                   String username=input.nextLine();
                   if(Main.Allmanagers.size() !=0){
                       for(int i=0;i<=Main.Allmanagers.size()-1;i++){
                           if(Allmanagers.get(i).getUsername().equals(username)){
                               found=true;
                           }
                       }
                       if(!found) {
                           manager.setUsername(username);
                           break;
                       } else {
                           System.out.println("this username already exists");
                       }
                   }
               }
               while (true) {
                   System.out.println("enter the manager password");
                   String password=input.nextLine();
                   if(password.length()>2){
                       manager.setPassword(password);
                       break;
                   } else {
                       System.out.println("password is too short");
                   }
               }
               while (true) {
                   System.out.println("enter the manager level pro/newbie");
                   String level = input.nextLine();
                   if (level.equals("pro") || level.equals("newbie")) {
                       manager.setLevel(level);
                       break;
                   } else {
                       System.out.println("invalid input");
                   }
               }
               while(true) {
                   System.out.println("enter the manager education B.ch/M.A/P.H.D");
                   String edu=input.nextLine();
                   if(edu.equals("B.ch")||edu.equals("M.A")||edu.equals("P.H.D")){
                       manager.education=edu;
                       break;
                   } else {
                       System.out.println("invalid input");
                   }
               }
              // manager.setEducation(input.nextLine());
               System.out.println("enter the manager Phone number");
               manager.setTelephone(input.nextLong());
               System.out.println("enter manager staff number ");
               manager.setStaffNumber(input.nextLine());
               while (true) {
                   System.out.println("enter the manager staff number");
                   String Staffnum = input.nextLine();
                   boolean found = false;
                   if (Main.Allmanagers.size() != 0) {
                       for (int i = 0; i <= Allmanagers.size() - 1; i++) {
                           if (Allmanagers.get(i).getStaffNumber().equals(Staffnum)) {
                               found = true;
                           }
                       }
                   }
                   if(!found){
                       manager.setStaffNumber(Staffnum);
                       break;
                   } else {
                       System.out.println("this staff number already exists");
                   }
               }
               manager.setDate();
               manager.createTable();
              // manager.setDate();
               break;
           } catch (Exception e){
               System.out.println("invalid input");
           }
       }
       School school = new School(id, name, provinceName, cityName, address, grade);
       System.out.println("school id : " + school.getId());
       System.out.println("school name : " + school.getName());
       System.out.println("school province Name : " + school.getProvince());
       System.out.println("school city name : " + school.getCity());
       System.out.println("school address : " + school.getAddress());
       System.out.println("school grade : " + school.getGrade());
       System.out.println("do you confirm the inserted data?");
       System.out.println("Y-yes");
       System.out.println("N-no");
       while (true) {
           boolean found=false;
           try {
               char choice = input.nextLine().charAt(0);
               if(choice=='Y'||choice=='N') {
                   switch (choice) {
                       case 'Y':
                           while(!found) {
                               int[] ids = school.selectAll();
                               for (int i = 0; i < ids.length; i++) {
                                   if (ids[i] == id) {
                                       found = true;
                                       break;
                                   }
                               }
                               if(found){
                                   System.out.println("this school already exists");
                                   break;
                               } else {
                                   manager.insertData();

                                   System.out.println("data inserted successfully");
                                   Allschools.add(school);
                                   Allmanagers.add(manager);
                                   school.createTable();
                                   school.connect();
                                   school.insertData();
                                   break;
                               }
                           }
                       case 'N':
                           break;
                   }
                   break;
               } else {System.out.println("invalid input");}
           } catch (Exception e){
               System.out.println("invalid input");
           }
       }
      // school.createTable();
      // school.connect();
      // school.insertData();
       System.out.println("data inserted successfully");
   }

    public static void main(String[] args) {
       getAllschoolsFromDatabase();
       getAllmanagersFromDatabase();
       getAllstudentsFromDatabase();
       getAllTeachersFromDatabase();
       getClassesFromDatabase();
       Lesson.getLesson();
       scores.getScores();
       scores.setScoresForLesson();
       setStuForScl();
       setMngForScl();
       setTeachersFroSchools();
       setClassesforSchools();
       Lesson.setLessonsForSchools();
        mainMenu();
    }
}
