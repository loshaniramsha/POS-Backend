package org.example.aadassignment01backend.dao;

import org.example.aadassignment01backend.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.IllegalFormatCodePointException;

public class SQLUtil {
    public static <T> T execute(String sql, Object... obj) throws SQLException,ClassNotFoundException {
        Connection connection= DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm=connection.prepareStatement(sql);

        for (int i=0;i< obj.length; i++){
            pstm.setObject(i+1, obj[i]);
        }
        if (sql.startsWith("SELECT")||sql.startsWith("select")){
            return (T) pstm.executeQuery();
        }else {
            return (T) (Boolean)(pstm.executeUpdate() > 0);
        }

    }
}
