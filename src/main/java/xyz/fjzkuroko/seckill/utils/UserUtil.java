package xyz.fjzkuroko.seckill.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.fjzkuroko.seckill.pojo.User;
import xyz.fjzkuroko.seckill.vo.ResponseBean;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户工具类：主要是用于生成用户
 * @author fjzkuroko
 * @version 1.0
 * @datetime 2021/5/16 23:43
 */
public class UserUtil {

    private static void createUser(int count) throws Exception {
        List<User> users = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            User user = new User();
            user.setId(13000000000L + i);
            user.setNickname("user" + i);
            user.setSalt("1a2b3c4d");
            user.setPassword(MD5Util.inputPassToDBPass("123456", user.getSalt()));
            users.add(user);
            user.setRegisterTime(new Date());
            user.setLoginCount(0L);
        }
//        System.out.println("Start create user...");
//        Connection conn = getConn();
//        String sql = "insert into seckill_user(id, nickname, password, salt, register_time, login_count) values(?,?,?,?,?,?)";
//        PreparedStatement psmt = conn.prepareStatement(sql);
//        for (User user : users) {
//            psmt.setLong(1, user.getId());
//            psmt.setString(2, user.getNickname());
//            psmt.setString(3, user.getPassword());
//            psmt.setString(4, user.getSalt());
//            psmt.setTimestamp(5, new Timestamp(user.getRegisterTime().getTime()));
//            psmt.setLong(6, user.getLoginCount());
//            psmt.addBatch();
//        }
//        psmt.executeBatch();
//        psmt.clearParameters();
//        conn.close();
//        System.out.println("insert to db");
        // 登录，生成userTicket
        String urlString = "http://localhost:8088/login/doLogin";
        File file = new File("C:\\Users\\HP\\Desktop\\config.txt");
        if (file.exists()) {
            file.delete();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        raf.seek(0);
        for (User user : users) {
            URL url = new URL(urlString);
            HttpURLConnection co = (HttpURLConnection) url.openConnection();
            co.setRequestMethod("POST");
            co.setDoOutput(true);
            OutputStream out = co.getOutputStream();
            String params = "mobile=" + user.getId() + "&password="+ MD5Util.inputPassToFormPass("123456");
            out.write(params.getBytes());
            out.flush();

            InputStream in = co.getInputStream();
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff)) >= 0) {
                bout.write(buff, 0, len);
            }
            in.close();
            bout.close();

            String response = new String(bout.toByteArray());
            ObjectMapper mapper = new ObjectMapper();
            ResponseBean responseBean = mapper.readValue(response, ResponseBean.class);
            String userTicket = (String) responseBean.getObject();
            System.out.println("create userTicket :" + user.getId());
            String row = user.getId()+ "," + userTicket;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            System.out.println("write to file :" + user.getId());
        }
        raf.close();
        System.out.println("over");
    }

    private static Connection getConn() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/seckillbilibili?useSSL=false";
        String username = "root";
        String password = "87654321";
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) throws Exception {
        createUser(5000);
    }

}
