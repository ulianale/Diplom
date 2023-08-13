package ru.netology.web.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
//import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;


public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    @SneakyThrows
    private static Connection getConn() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static DataHelper.PaymentEntity getPaymentTableInfo() {
        var conn = getConn();
        var codeSQL = "SELECT * FROM payment_entity ORDER BY created DESC";
        return runner.query(conn, codeSQL, new BeanHandler<>(DataHelper.PaymentEntity.class));
    }

    @SneakyThrows
    public static DataHelper.CreditEntity getCreditTableInfo() {
        var conn = getConn();
        var codeSQL = "SELECT * FROM credit_request_entity ORDER BY created DESC";
        return runner.query(conn, codeSQL, new BeanHandler<>(DataHelper.CreditEntity.class));
    }

    @SneakyThrows
    public static DataHelper.OrderEntity getOrderTableInfo() {
        var conn = getConn();
        var codeSQL = "SELECT * FROM order_entity ORDER BY created DESC";
        return runner.query(conn, codeSQL, new BeanHandler<>(DataHelper.OrderEntity.class));
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var conn = getConn();
        runner.execute(conn, "DELETE FROM credit_request_entity");
        runner.execute(conn, "DELETE FROM payment_entity");
        runner.execute(conn, "DELETE FROM order_entity");
    }

}
