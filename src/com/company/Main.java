package com.company;


import java.sql.*;
import java.util.Scanner;

public class Main implements IConstants

{
    public static void main(String[] args)

   {
        try

        {
            Class.forName("org.sqlite.JDBC");
            Connection co = DriverManager.getConnection(
                    "jdbc:sqlite:tovar1.db");
            System.out.println("Connect");
            Statement stmt = co.createStatement();
      // 1. Сформировать таблицу товаров (id, title, cost) запросом из Java приложения.


            Scanner sc = new Scanner(System.in);
            String line = "";
            while (!line.equals("end")) {
                   line = sc.nextLine();


                   if (line.equals("create"))
                   {
                       // 1. Сформировать таблицу товаров (id, title, cost) запросом из Java приложения.
                   stmt.executeUpdate(SQL_CREATE_TABLE);
                   System.out.println("Таблица создана");
                                     }

                if (line.equals("init") || line.equals("end"))
                {
                    //    2.  Oчистить таблицу и заполнить 1000 товаров вида: id_товара товар1 10
                    stmt.executeUpdate(SQL_CREATE_TABLE);
                    System.out.println("Таблица очищена и заполнена");
                    for (int tov=1;tov<=50;tov++)
                    {stmt.executeUpdate("insert into tovar (title, cost)\n" +
                            "values ('товар" + tov + "', " + tov + ");");
                 //   System.out.println("Товар добавлен " + tov);
                    }
                }
            }

        ResultSet rs = stmt.executeQuery(SQL_SELECT);

         // Вывод
            while (rs.next())
             System.out.println(rs.getString("id") + "\t" +
                     rs.getString("title") + "\t" +
                     rs.getString("cost"));
            rs.close();

        stmt.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}



