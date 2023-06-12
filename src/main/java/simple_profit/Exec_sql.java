package simple_profit;
import static simple_profit.Main.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
    Database management
    @author https://github.com/bufferum7011
*/
public class Exec_sql {

    private static Statement get_statement() {
        try { return DriverManager.getConnection(main.mysql_server, main.mysql_user, main.mysql_password).createStatement(); }
        catch(SQLException e) { System.out.println("[ERROR get_statement]"); return null; }
    }

    /**
        Sql_callback
        Table data as an array.
    */
    public static ResultSet sql_callback(String sql) {
        System.out.print("[Sql_callback]");
        try { return get_statement().executeQuery(sql); }
        catch(SQLException e) { System.out.println("[ERROR Sql_callback]"); return null; }
    }

    /**
        Sql_update
        To execute sql queries without return.
    */
    public static void sql_update(String sql) {
        System.out.print("[Sql_update]");
        try { get_statement().execute(sql); }
        catch(SQLException e) { System.out.println("[ERROR Sql_update]"); }
    }
}