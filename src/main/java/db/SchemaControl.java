package db;

import org.json.simple.JSONValue;

import java.util.HashMap;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class SchemaControl {
    Connection connection;
    //Statement statement;
    PreparedStatement ps = null;
    ResultSet rs = null;
    final static String STUDENTS_WITH_RETAKE_QUERY = "SELECT DISTINCT students.LastName,\n" +
            "       students.FirstName," +
            "       students.GroupID\n" +
            "       students.StudentID," +
            "subjects.SubjectName," +
            "subjects.SubjectID," +
            "results.Mark" +
            "       FROM results\n" +
            "       INNER JOIN students ON students.StudentID = results.StudentID\n" +
            "       INNER JOIN subjects ON subjects.SubjectID = results.SubjectID\n" +
            "       WHERE results.Mark < 4\n" +
            "       ORDER BY GroupID,LastName;";
    final static String STUDENTS_WITH_RETAKE_QUERY2 = "SELECT \t\tstudents.LastName, \n" +
            "                  students.FirstName,\n" +
            "\t\t\t\t students.StudentID,\n" +
            "                   students.GroupID,\n" +
            "                   subjects.SubjectName,\n" +
            "                   subjects.SubjectID,\n" +
            "                  results.Mark\n" +
            "                   FROM results\n" +
            "             INNER JOIN students ON students.StudentID = results.StudentID\n" +
            "                   INNER JOIN subjects ON subjects.SubjectID = results.SubjectID\n" +
            "                   WHERE results.Mark < 4\n" +
            "                   ORDER BY StudentID,SubjectID;\n";
    final static String ALL_RESULTS_QUERY = "SELECT students.LastName,\n" +
            "       students.FirstName," +
            "\t   students.StudentID,\n" +
            "       students.GroupID,\n" +
            "       subjects.SubjectName,\n" +
            "       subjects.SubjectID,\n" +
            "       results.Mark\n" +
            "       FROM results\n" +
            "       INNER JOIN students ON students.StudentID = results.StudentID\n" +
            "       INNER JOIN subjects ON subjects.SubjectID = results.SubjectID\n" +
            "       ORDER BY StudentID,SubjectID;";
    final static String CHANGE_MARK_QUERY_TEMPLATE = "UPDATE results\n" +
            "SET\tMark = ? \n" +
            "WHERE StudentID = ? AND SubjectID = ?;\n" +
            "\t";
    final static String INSERT_FEEDBACK = "INSERT INTO feedbacks (fname,surname,country,message) VALUES (?,?,?,?)";
    final static String DELETE_FROM_STUDENTS = "DELETE FROM students WHERE StudentID = ?;";
    final static String DELETE_FROM_RESULTS = "DELETE FROM results WHERE StudentID = ?;";
    final static String GET_PRICES_BY_MEDICINE = "SELECT pharmacies.shortName,medicines.shortName,\n" +
            "store.quantity,store.price\n" +
            "FROM store\n" +
            "INNER JOIN pharmacies ON pharmacies.pharmacyID = store.pharmacyID\n" +
            "INNER JOIN medicines ON medicines.medicinID = store.medicinID\n" +
            "WHERE medicines.shortName = ? \n" +
            "ORDER BY price ASC;";
    public SchemaControl() {
        super();
        connectToDb();
    }

    void connectToDb() {
        try {
            // DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Class.forName("com.mysql.jdbc.Driver");
            //String url = "jdbc:mysql://localhost/pharmacies?serverTimezone=Europe/Moscow&useSSL=false";
            String url = "jdbc:mysql://bwub4svucr471batimpj-mysql.services.clever-cloud.com/bwub4svucr471batimpj";

        //    String url = "jdbc:mysql://bwub4svucr471batimpj-mysql.services.clever-cloud.com:3306/pharmacies&useUnicode=true&characterEncoding=UTF-8";
            //connection = DriverManager.getConnection(url, "root", "password");
            connection = DriverManager.getConnection(url, "uw9su6kpggmmsgaf", "mtRHagy7aD81xHEYJ2k6");
            //    statement = connection.createStatement();
            System.out.println("Successfull connection!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            disconnect();
        }

    }

    void disconnect() {
        try {
            connection.close();
            System.exit(0);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void insertNewFeedback(String name,String surname,String country,String message){
        try{
            ps = connection.prepareStatement(INSERT_FEEDBACK);
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, country);
            ps.setString(4, message);
            ps.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    public String getGetPricesByMedicine(String medicineName){
        try{
            ps = connection.prepareStatement(GET_PRICES_BY_MEDICINE);
            ps.setString(1, medicineName);
            rs = ps.executeQuery();

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return getJSONFromResultSet(rs);
    }
    public String getAllPharmacies() {
        String res = "";
        try {
            ps = connection.prepareStatement("SELECT * FROM pharmacies;");
            rs = ps.executeQuery();
        } catch (Exception ex) {

        }
        return getJSONFromResultSet(rs);
    }

    public String getJSONFromResultSet(ResultSet rs) {
        List list = new ArrayList();
        if (rs != null) {
            try {
                ResultSetMetaData metaData = rs.getMetaData();
                while (rs.next()) {
                    Map<String, Object> columnMap = new HashMap<String, Object>();
                    for (int columnIndex = 1; columnIndex <= metaData.getColumnCount(); columnIndex++) {
                        if (rs.getString(metaData.getColumnName(columnIndex)) != null)
                            columnMap.put(metaData.getColumnLabel(columnIndex), rs.getString(metaData.getColumnName(columnIndex)));
                        else
                            columnMap.put(metaData.getColumnLabel(columnIndex), "");
                    }
                    list.add(columnMap);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return JSONValue.toJSONString(list);
    }
}
