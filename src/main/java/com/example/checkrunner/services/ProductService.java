package com.example.checkrunner.services;

import com.example.checkrunner.dao.Repository;
import com.example.checkrunner.database.DBConnection;

import com.example.checkrunner.entity.Product;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements Repository<Product> {

    /**
     * Это поле класа получения подключения к базе данных
     * @see Connection
     */
    Connection connection = DBConnection.getConnection();
    /**
     * Это поле класса запросов к базе данных
     */
    PreparedStatement preparedStatement = null;
    /**
     * Это поле класса запросов к базе данных
     */
    Statement statement = null;

    /**
     * Метод созданный для добавления данных в таблицу product
     * @param entity объект класса Product
     */
    @Override
    public int add(Product entity) {
        int status = 0;
        String sql = "INSERT INTO product (name, price, status) VALUES (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,entity.getName());
            preparedStatement.setDouble(2,entity.getPrice());
            preparedStatement.setString(3,entity.getStatus());

            status = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public Product find(int id) {
        String sql = "SELECT * FROM product WHERE id = " + id;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet != null){
                return new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("status"),
                        1
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Метод чтения данных из таблицы product
     * @return лист с полученными данными из базы данных
     */
    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM product";

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){

                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("status"),
                        1
                );
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    /**
     * Метод созданный для обновления данных в таблице product
     * @param entity объект класса Product
     */
    @Override
    public int update(Product entity) {
        int status = 0;
        String sql = "UPDATE product SET name=?, price=?, status=? WHERE id = " + entity.getId();

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setDouble(2, entity.getPrice());
            preparedStatement.setString(3, entity.getStatus());

            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    /**
     * Метод созданный для удалённых данных из таблицы product по id
     * @param entity объект класса Product
     */
    @Override
    public int remove(Product entity) {
        int status = 0;
        String sql = "DELETE FROM product WHERE ID=?";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,entity.getId());
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
