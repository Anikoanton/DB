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


                if (line.equals("create") || line.equals("init") )
                {
                    //    2.  Oчистить таблицу и заполнить 1000 товаров вида: id_товара товар1 10
                    showsql();
                }

// Добавить возможность изменения цены товара. Указываем имя товара и новую цену
                if (line.equals("change") )
                {
                    String tovar="товар46";
                    int price=1112;
                    changeprice(co, tovar, price);
                    sql(tovar);
                }
                //   5. Вывести товары в заданном ценовом диапазоне. Консольная команда: «/товарыпоцене 100         600».

                if (line.contains("diapazon"))
                {
                    pricediapazon(co, 100, 140);
                }

                if (line.contains("cost"))
                {
                    showsql();
                    int leng = line.length();
                    String tov1;
                    if (leng<10)
                    {tov1="товар45";} else
                    {tov1 = line.substring(5);}
                    sql(tov1);
                }



                //  Реализовать возможность узнать цену товара по его имени, либо вывести сообщение "Not found" если товара нет в базе.
                //          Консольная команда: -getprice товар545



                stmt.close();
            }
            sc.close();
            stmt.close();
            co.close();
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void showsql()
    {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection co3 = DriverManager.getConnection("jdbc:sqlite:tovar1.db");


            Statement stmt3 = co3.createStatement();

            stmt3.executeUpdate(SQL_CREATE_TABLE);
            System.out.println("---------------------------");
            System.out.println("Таблица очищена и заполнена");

            for (int tov=1;tov<=50;tov++)
                stmt3.executeUpdate("insert into tovar (title, cost)\n" +"values ('товар" + tov + "', " + tov*10 + ");");

            ResultSet rs = stmt3.executeQuery(SQL_SELECT);

            while (rs.next())
                System.out.println(rs.getString("id") + "\t" +
                        rs.getString("title") + "\t" +
                        rs.getString("cost"));
            rs.close();

            stmt3.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // получить цену товара по названию
    public static void sql (String tov)
    {


        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection co2 = DriverManager.getConnection("jdbc:sqlite:tovar1.db");
            PreparedStatement  stmt2 =  co2.prepareStatement("select cost from tovar where title = ?");
            stmt2.setString(1, tov);
            ResultSet rs2  = stmt2.executeQuery();
            while (rs2.next())
                System.out.println("Цена товара " + tov + " равна = " + rs2.getString("cost"));
            rs2.close();
            //System.out.println("товару: " + tov + ", соотвествует цена - " +  rs2);
            co2.close();

            //  PreparedStatement stmt2 = co.prepareStatement(SQL_Find_price);

        }

        catch (Exception e){System.out.println(e.getMessage());}

    }

    // 4. Добавить возможность изменения цены товара. Указываем имя товара и новую цену.
    //Консольная команда: «/сменитьцену товар10 10000».


    public static void changeprice(Connection co, String tovar, int price)
    {
        try

        {
            PreparedStatement ps = co.prepareStatement("update tovar set cost=? where title =?;");
            ps.setInt(1,price);
            ps.setString(2,tovar);
            ps.executeUpdate();
            ps.close();
        }

        catch (Exception e)
        {System.out.println(e.getMessage());}

    }

    public static void pricediapazon(Connection co, int price1, int price2)
    {
        try

        {
            PreparedStatement ps = co.prepareStatement("select * from tovar where (cost > ? and cost < ?);");
            ps.setInt(1,price1);
            ps.setInt(2,price2);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                System.out.println (rs.getString(1) + "\t" + rs.getString (2)  + "\t" + rs.getString(3));
            }
            rs.close();
            ps.close();
        }

        catch (Exception e)
        {System.out.println(e.getMessage());}

    }



}



