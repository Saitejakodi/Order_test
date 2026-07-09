package com.ust.sdet.repository;

import com.ust.sdet.db.DatabaseConfig;
import com.ust.sdet.model.Order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepository {

    public Order findBySku(String sku) {

        String sql = """
            SELECT sku, qty, price, order_date, shipped
            FROM orders
            WHERE sku = ?
            """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, sku);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Order(
                        rs.getString("sku"),
                        rs.getInt("qty"),
                        rs.getDouble("price"),
                        rs.getDate("order_date").toLocalDate(),
                        rs.getBoolean("shipped")
                );
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long countShipped() {

        String sql = """
            SELECT COUNT(*)
            FROM orders
            WHERE shipped = true
            """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()
        ) {

            rs.next();
            return rs.getLong(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Order order) {

        String sql = """
                INSERT INTO orders
                (sku, qty, price, order_date, shipped)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, order.sku());
            statement.setInt(2, order.qty());
            statement.setDouble(3, order.price());
            statement.setDate(4, Date.valueOf(order.orderDate()));
            statement.setBoolean(5, order.shipped());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long count() {

        String sql = "SELECT COUNT(*) FROM orders";

        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()
        ) {

            rs.next();
            return rs.getLong(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void reset() {

//        String sql = "TRUNCATE TABLE orders RESTART IDENTITY";
        String sql = "TRUNCATE TABLE orders";


        try (
                Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}