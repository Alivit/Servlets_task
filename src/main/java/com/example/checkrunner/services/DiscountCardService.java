package com.example.checkrunner.services;

import com.example.checkrunner.dao.Repository;
import com.example.checkrunner.database.DBConnection;
import com.example.checkrunner.entity.DiscountCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс сервис crud операций для работы с дисконтными картами
 */
public class DiscountCardService implements Repository<DiscountCard> {

    /**
     * Это поле класа получения подключения к базе данных
     * @see Connection
     */
    private Connection connection = DBConnection.getConnection();
    /**
     * Это поле класса запросов к базе данных
     */
    private PreparedStatement preparedStatement = null;
    /**
     * Это поле класса запросов к базе данных
     */
    private Statement statement = null;

    /**
     * Метод созданный для добавления данных в таблицу discount_card
     * @param entity объект класса DiscountCard
     */
    @Override
    public DiscountCard add(DiscountCard entity){

        String sql = "INSERT INTO discount_card (code, discount) VALUES (?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,entity.getCode());
            preparedStatement.setInt(2,entity.getDiscount());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Метод чтения данных из таблицы discount_card
     * @return лист с полученными данными из базы данных
     */
    @Override
    public List<DiscountCard> getAll(){
        List<DiscountCard> cardList = new ArrayList<>();

        String sql = "SELECT * FROM discount_card";

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){

                DiscountCard card = new DiscountCard(
                        resultSet.getInt("id"),
                        resultSet.getString("code"),
                        resultSet.getInt("discount")
                );
                cardList.add(card);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cardList;
    }

    /**
     * Метод созданный для обновления данных в таблице discount_card
     * @param entity объект класса DiscountCard
     */
    @Override
    public DiscountCard update(DiscountCard entity) {
        String sql = "UPDATE discount_card SET code=?, discount=?";

        try {
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getCode());
            preparedStatement.setInt(2, entity.getDiscount());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Метод созданный для удаления данных из таблицы discount_card по id
     * @param entity объект класса DiscountCard
     */
    @Override
    public DiscountCard remove(DiscountCard entity){
        String sql = "DELETE FROM discount_card WHERE ID=?";

        try{
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
