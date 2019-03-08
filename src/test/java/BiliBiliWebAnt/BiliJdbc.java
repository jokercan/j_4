package BiliBiliWebAnt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class BiliJdbc {
    public String username = "root";
    public String password = "root";
    public String connectionUrl = "jdbc:mysql://127.0.0.1:3306/bilibili?useUnicode=true&characterEncoding=UTF-8";
    Connection conn;

    public void insertInfo(BiliPojo biliPojo) throws Exception{
        this.conn = DriverManager.getConnection(this.connectionUrl , this.username , this.password);
        String sql = "INSERT INTO bili (title , title_image , up , info , p_and_address , play_number , bullet_number) VALUE(? , ? , ? , ? , ? , ? , ?)";
        PreparedStatement ptmt = this.conn.prepareStatement(sql);
        String title , title_image , up , info , p_and_address , play_number , bullet_number;
        title = biliPojo.getTitle();
        title_image = biliPojo.getTitleImage();
        up = biliPojo.getUp();
        info = biliPojo.getInfo();
        p_and_address = biliPojo.getpAndAddress();
        play_number = biliPojo.getPlayNumber();
        bullet_number = biliPojo.getBulletNumber();
        ptmt.setString(1 ,title);
        ptmt.setString(2 ,title_image);
        ptmt.setString(3 ,up);
        ptmt.setString(4 , info);
        ptmt.setString(5 , p_and_address);
        ptmt.setString(6 , play_number);
        ptmt.setString(7 , bullet_number);
        ptmt.execute();
    }
}
