package com.company;

/**
 * Created by Антон on 08.02.2018.
 */
public interface IConstants {

    final public String SQL_CREATE_TABLE =
            "DROP TABLE IF EXISTS tovar; " +
                    "CREATE TABLE tovar " +
                    "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title VARCHAR(255), " +
                    "cost INTEGER );";
final public String SQL_Find_price ="select COST from tovar WHERE title=?";
    String SQL_SELECT = " SELECT * FROM tovar; ";

}
