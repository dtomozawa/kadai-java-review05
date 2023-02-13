import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Review05 {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement ps = null; 
        System.out.print("検索キーワードを入力してください > ");
        int id = keyInNum();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                    "root", "daisuke335579");
            String Sql = "SELECT name, age FROM person WHERE id = ?";
            ps = con.prepareStatement(Sql);
            ps.setInt(1, id); 
            ResultSet rs = ps.executeQuery();
        

            try  {
                if (rs.next()) {
                    String name = rs.getString("Name");
                    int age = rs.getInt("age");
                    System.out.println(name + "\t" + age + "\t" );
                }
            }catch (SQLException e) {
                e.printStackTrace();
                
            }
        } catch (ClassNotFoundException e1) {
            // TODO 自動生成された catch ブロック
            e1.printStackTrace();
        } catch (SQLException e1) {
            // TODO 自動生成された catch ブロック
            e1.printStackTrace();
            
        } finally {
            // 8. 接続を閉じる
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("PreparedStatementを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
        }
    }
            
    /*
     * キーボードから入力された値をStringで返す 引数：なし 戻り値：入力された文字列
     */
    private static String keyIn() {
        String line = null;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            line = key.readLine();
        } catch (IOException e) {

        }
        return line;
    }

    /*
     * キーボードから入力された値をintで返す 引数：なし 戻り値：int
     */
    private static int keyInNum() {
        int result = 0;
        try {
            result = Integer.parseInt(keyIn());
        } catch (NumberFormatException e) {
        }
        return result;
    }

}
