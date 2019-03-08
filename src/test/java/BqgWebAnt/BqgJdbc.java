package BqgWebAnt;

import java.sql.*;

public class BqgJdbc {
    public String username = "root";
    public String password = "root";
    public String connectionUrl = "jdbc:mysql://127.0.0.1:3306/bqg?useUnicode=true&characterEncoding=UTF-8?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    Connection conn;

    public void insertInfo(BqgBlog bqgBlog) throws Exception{
        this.conn = DriverManager.getConnection(this.connectionUrl , this.username , this.password);
        String sql = "INSERT INTO booook (book , author , info , image , bookname , content ,url ) VALUE(? , ? , ? , ? , ? , ? , ?)";
        PreparedStatement ptmt = this.conn.prepareStatement(sql);
        String book , author , info , image , bookname , content , url;
        book = bqgBlog.getBook();
        author = bqgBlog.getAuthor();
        info = bqgBlog.getInfo();
        image = bqgBlog.getImage();
        bookname = bqgBlog.getBookname();
        content = bqgBlog.getContent();
        url = bqgBlog.getUrl();
        ptmt.setString(1 ,book);
        ptmt.setString(2 ,author);
        ptmt.setString(3 ,info);
        ptmt.setString(4 , image);
        ptmt.setString(5 , bookname);
        ptmt.setString(6 , content);
        ptmt.setString(7 , url);
        ptmt.execute();
    }

    public boolean checkExist(String connectionUrl) throws SQLException {
        this.conn = this.conn = DriverManager.getConnection(this.connectionUrl , this.username , this.password);
        String sql = "SELECT bookname FROM booook WHERE url = " +"'" + connectionUrl + "'";
        Statement stmt =  conn.createStatement();
        ResultSet resultSet =stmt.executeQuery(sql);
        return resultSet.next();
    }
}
