package com.lcw.one.codegen.dao;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

@Transactional
@Repository
public class TableMetaInfoDao {


    @Autowired
    private EntityManager entityManager;

    public void getInfo() {

        System.out.println(entityManager.getProperties());
    }

    public void getMeta() {
        Session session = entityManager.unwrap(Session.class);
        session.doWork(new Work() {

            @Override
            public void execute(Connection connection) throws SQLException {

                DatabaseMetaData metadata = null;
                try {
                    metadata = connection.getMetaData();
                    ResultSet res = metadata.getColumns(null, null, "USERS", "USERNAME");
                    System.out.println("List of columns: ");
                    while (res.next()) {
                        System.out.println(
                                "  " + res.getString("TABLE_SCHEM")
                                        + ", " + res.getString("TABLE_NAME")
                                        + ", " + res.getString("COLUMN_NAME")
                                        + ", " + res.getString("TYPE_NAME")
                                        + ", " + res.getInt("COLUMN_SIZE")
                                        + ", " + res.getInt("NULLABLE"));
                    }
                    res.close();
                    System.out.println();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


//        org.hibernate.engine.spi.SessionImplementor sessionImp = (org.hibernate.engine.spi.SessionImplementor) entityManager.getDelegate();
//        DatabaseMetaData metadata = null;
//        try {
//            metadata = sessionImp.connection().getMetaData();
//            ResultSet res = metadata.getColumns(null, null, "USERS", "USERNAME");
//            System.out.println("List of columns: ");
//            while (res.next()) {
//                System.out.println(
//                        "  " + res.getString("TABLE_SCHEM")
//                                + ", " + res.getString("TABLE_NAME")
//                                + ", " + res.getString("COLUMN_NAME")
//                                + ", " + res.getString("TYPE_NAME")
//                                + ", " + res.getInt("COLUMN_SIZE")
//                                + ", " + res.getInt("NULLABLE"));
//            }
//            res.close();
//            System.out.println();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
