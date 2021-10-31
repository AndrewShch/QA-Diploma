package data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                System.getProperty("url"),
                System.getProperty("db.login"),
                System.getProperty("db.password")
        );
    }

    @SneakyThrows
    public static void deleteTables() {
        val creditRequest = "DELETE FROM credit_request_entity";
        val order = "DELETE FROM order_entity";
        val payment = "DELETE FROM payment_entity";
        val runner = new QueryRunner();
        try (val conn = getConnection();
        ) {
            runner.update(conn, creditRequest);
            runner.update(conn, order);
            runner.update(conn, payment);
        }
    }

    @SneakyThrows
    public static String getStatusCreditRequestEntity() {
        try (val conn = getConnection();
             val countStmt = conn.createStatement()) {
            val sql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
            val resultSet = countStmt.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        }
        return null;
    }

    @SneakyThrows
    public static String getStatusPaymentEntity() {
        try (val conn = getConnection();
             val countStmt = conn.createStatement()) {
            val sql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
            val resultSet = countStmt.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("status");
            }
        }
        return null;
    }
}
