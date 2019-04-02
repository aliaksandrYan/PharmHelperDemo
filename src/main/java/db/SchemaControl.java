package db;

import entities.Medicine;
import entities.Pharmacy;
import org.json.simple.JSONValue;

import java.util.HashMap;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class SchemaControl {
    Connection connection;
    PreparedStatement ps = null;
    ResultSet rs = null;
    final static String INSERT_FEEDBACK = "INSERT INTO feedbacks (fname,surname,country,message) VALUES (?,?,?,?)";
    final static String GET_PRICES_BY_LIST = "SELECT pharmacies.pharmacyID AS ID,pharmacies.shortName AS pharmacyName, " +
            "pharmacies.adress ,pharmacies.phone,SUM( price ) AS total FROM store \n" +
            "JOIN medicines ON medicines.medicinID = store.medicinID\n" +
            "JOIN pharmacies ON pharmacies.pharmacyID = store.pharmacyID\n" +
            "WHERE medicines.shortName IN ";
    final static String GROUP_BY = " GROUP BY store.pharmacyID;";
    final static String GET_PRICES_BY_MEDICINE = "SELECT pharmacies.shortName,medicines.shortName,\n" +
            "store.quantity,store.price\n" +
            "FROM store\n" +
            "INNER JOIN pharmacies ON pharmacies.pharmacyID = store.pharmacyID\n" +
            "INNER JOIN medicines ON medicines.medicinID = store.medicinID\n" +
            "WHERE medicines.shortName = ? \n" +
            "ORDER BY price ASC;";
    final static String GET_LIST_PRICES = "SELECT pharmacies.pharmacyID,pharmacies.shortName as \"pharmacyName\",medicines.shortName,medicines.medicinID,\n" +
            "medicines.descript,store.quantity,store.price\n" +
            "FROM store\n" +
            "INNER JOIN pharmacies ON pharmacies.pharmacyID = store.pharmacyID\n" +
            "INNER JOIN medicines ON medicines.medicinID = store.medicinID\n" +
            "WHERE medicines.shortName IN ";

    public static String listToString(List<?> list) {
        String result = "(";
        for (int i = 0; i < list.size(); i++) {
            result += "'" + list.get(i) + "'" + ',';
        }
        result = result.substring(0, result.length() - 1);
        result += ")";
        return result;
    }

    public SchemaControl() {
        super();
        connectToDb();
    }

    void connectToDb() {
        try {
            // DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Class.forName("com.mysql.jdbc.Driver");
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

    public void insertNewFeedback(String name, String surname, String country, String message) {
        try {
            ps = connection.prepareStatement(INSERT_FEEDBACK);
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, country);
            ps.setString(4, message);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String getPricesByMedicine(String medicineName) {
        try {
            ps = connection.prepareStatement(GET_PRICES_BY_MEDICINE);
            ps.setString(1, medicineName);
            rs = ps.executeQuery();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return getJSONFromResultSet(rs);
    }

    public List<Pharmacy> getPricesByList(List<String> medicines) {
        Map<String, Double> resultMap = new HashMap<String, Double>();
        List<Pharmacy> pharmacies = new ArrayList<>();
        try {
            String st = GET_PRICES_BY_LIST + SchemaControl.listToString(medicines) + GROUP_BY;
            ps = connection.prepareStatement(st);
            rs = ps.executeQuery();
            while (rs.next()) {
                pharmacies.add(new Pharmacy(rs.getInt("ID"), rs.getString("pharmacyName"), rs.getString("adress"),
                        rs.getString("phone"), rs.getDouble("total")));
                resultMap.put(rs.getString("pharmacyName"), rs.getDouble("total"));
            }
            ps = connection.prepareStatement(GET_LIST_PRICES + SchemaControl.listToString(medicines));
            rs = ps.executeQuery();
            while (rs.next()) {
                for (Pharmacy ph : pharmacies) {
                    if (ph.getId() == rs.getInt("pharmacyID")) {
                        ph.addMedicine(new Medicine(rs.getInt("medicinID"), rs.getString("shortName"),
                                rs.getString("descript"), rs.getDouble("price")));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pharmacies;
    }

    public String getAllPharmacies() {
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
