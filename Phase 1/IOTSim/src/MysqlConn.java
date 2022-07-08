import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

//Check Querry SL and ML

public class MysqlConn {
    Connection c_mySQLConect = null;
    String c_ip = null;
    String c_user = null;
    String c_password = null;

    public MysqlConn(String i_ip, String i_user, String i_pass){
        this.c_ip = i_ip;
        this.c_user = i_user;
        this.c_password = i_pass; 
        System.out.println("[Create] Created a database object at: " + this.c_ip + " with the username " + this.c_user);
    }

    public Boolean entryExists(String i_Table, String i_Id){
        String i_querry = "SELECT * FROM iotsec." + i_Table + " WHERE(ID = '" + i_Id + "');";
        Boolean out = null;
        ResultSet res = null;
        _connect();
        try{
            Statement mysqlStatement  = this.c_mySQLConect.createStatement();
            res = mysqlStatement.executeQuery(i_querry);
            // System.out.println("[Info] Querried the database with : " + i_querry);
            out = res.next();
        } catch (SQLException e) {
            System.out.println("[Error] There was an issue querrying the database: " + i_querry + " | " + e.getMessage());
        } finally {
            try {
                if (res != null) res.close();
              } catch (SQLException e) {
                System.out.println("[Error] There was an issue querying the database: " + i_querry + " | " + e.getMessage());  
              }
        }
        _closeConnect();
        return out;
    }

    public Boolean tableExists(String i_Table){
        String i_querry = "SHOW TABLES LIKE '" + i_Table + "';";
        Boolean out = null;
        ResultSet res = null;
        _connect();
        try{
            Statement mysqlStatement  = this.c_mySQLConect.createStatement();
            res = mysqlStatement.executeQuery(i_querry);
            // System.out.println("[Info] Querried the database with : " + i_querry);
            out = res.next();
        } catch (SQLException e) {
            System.out.println("[Error] There was an issue querrying the database: " + i_querry + " | " + e.getMessage());
        } finally {
            try {
                if (res != null) res.close();
              } catch (SQLException e) {
                System.out.println("[Error] There was an issue closing the database response: " + i_querry + " | " + e.getMessage());  
              }
        }
        _closeConnect();
        return out;
    }

    public HashMap<Integer,HashMap<Integer,Object>> querryML(String i_querry){
        HashMap<Integer,HashMap<Integer,Object>> out = new HashMap<>();
        HashMap<Integer,Object> temp = new HashMap<>();
        ResultSet res = null;
        ResultSetMetaData resMeta = null;
        _connect();
        try{
            Statement mysqlStatement  = this.c_mySQLConect.createStatement();
            res = mysqlStatement.executeQuery(i_querry);
            // System.out.println("[Info] Querried the database with : " + i_querry);
            for (int j = 0 ; res.next() ; j++){
                resMeta = res.getMetaData();
                for (int i = 1; i <= resMeta.getColumnCount(); i++){
                    temp.put(i-1,res.getObject(i));
                }  
                out.put(j,temp);
            }
        } catch (SQLException e) {
            System.out.println("[Error] There was an issue querrying the database: " + i_querry + " | " + e.getMessage());
        } finally {
            try {
                if (res != null) res.close();
              } catch (SQLException e) {
                System.out.println("[Error] There was an issue querying the database: " + i_querry + " | " + e.getMessage());  
              }
        }
        _closeConnect();
        return out;
    }

    public HashMap<Integer,Object> querrySL(String i_querry){
        HashMap<Integer,Object> out = new HashMap<>();
        ResultSet res = null;
        ResultSetMetaData resMeta = null;
        _connect();
        try{
            Statement mysqlStatement  = this.c_mySQLConect.createStatement();
            res = mysqlStatement.executeQuery(i_querry);
            // System.out.println("[Info] Querried the database with : " + i_querry);
            res.next();
            resMeta = res.getMetaData();
            for (int i = 1; i <= resMeta.getColumnCount(); i++){
                out.put(i-1,res.getObject(i));
            }     
        } catch (SQLException e) {
            System.out.println("[Error] There was an issue querrying the database: " + i_querry + " | " + e.getMessage());
        } finally {
            try {
                if (res != null) res.close();
              } catch (SQLException e) {
                System.out.println("[Error] There was an issue querying the database: " + i_querry + " | " + e.getMessage());  
              }
        }
        _closeConnect();
        return out;
    }

    public void execute(String i_comm){
        _connect();
        try{
            Statement mysqlStatement  = this.c_mySQLConect.createStatement();
            mysqlStatement.execute(i_comm);
            // System.out.println("[Info] Sent the command to the database: " + i_comm);
        } catch (SQLException e) {
            System.out.println("[Error] There was an issue querrying the database: " + i_comm + " | " + e.getMessage());
        }
        _closeConnect();
    }

    private void _closeConnect(){
        try{
               if(this.c_mySQLConect != null)
               this.c_mySQLConect.close();
            //    System.out.println("[Info] Closed the database connection");
        }catch(SQLException ex){
               System.out.println(ex.getMessage());
        }
    }

    private void _connect(){
        try{
            this.c_mySQLConect = DriverManager.getConnection(this.c_ip, this.c_user, this.c_password);
            // System.out.println("[Info] Connected to the database at: " + this.c_ip + " with the username " + this.c_user);
        } catch (SQLException e) {
            System.out.println("[Error] There was an issue connecting to the database: " + e.getMessage());
        }
    }
}
