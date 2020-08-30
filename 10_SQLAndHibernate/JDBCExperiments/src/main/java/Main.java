import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "insert_password";
        Connection connection = DriverManager.getConnection(url,user,pass);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT name, COUNT(subscription_date)/MONTH(MAX(subscription_date)) FROM\n" +
                "Courses JOIN Subscriptions ON Courses.id = Subscriptions.course_id GROUP BY Subscriptions.course_id;\n");
        while (resultSet.next()){
            String courseName = resultSet.getString(1);
            String courseCount = resultSet.getString(2);
            System.out.print(courseName + ":");
            System.out.println(courseCount);
        }
        connection.close();
        resultSet.close();
        statement.close();
    }
}
