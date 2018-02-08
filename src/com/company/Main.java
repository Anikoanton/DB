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
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}



