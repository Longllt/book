package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 登录功能实现类
 * @author longbin
 *
 */
public class Login {

	Admin admin;
	
	void setAdmin(Admin admin) {
		this.admin=admin;
		//System.out.println(this.admin.getPassword()+"   " + this.admin.getID());
	}
	/*
	 * JudgeAdmin()方法
	 * 判断Admin的ID和密码是否正确，如果正确，显示登录成功
	 * 如果错误，弹出一个窗口，显示账号或密码错误
	 */
	private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/BOOK?serverTimezone=UTC";
    private String user = "root";
    private String password = "521489";
    String pw;
    
	
	 public boolean login(Admin admin) throws SQLException, ClassNotFoundException {
	    	String sql="select id,password from book where id="+"'"+admin.getID()+"'";
	        
	   
	    	Class.forName(driver);
	    	Connection conn = DriverManager.getConnection(url, user, password);
	    	PreparedStatement ps = conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        int ans = 0;
	        if(rs.next()) {
	        	ans = 1;
	        	pw=rs.getString(2);
	        }  
	        rs.close();
	        ps.close();
	        conn.close();
	        if(ans == 1) {
	        	return true;
	        }
	        else return false;
	    }
	int JudgeAdmin() {
		int u=0;
		    try {
		        if(login(this.admin)) {
		        	if(pw.equals(admin.getPassword()))
		        	     System.out.println("登录成功");
		        }else {
		            u=1;
		        }
		    }catch(Exception e) {
		        //e.printStackTrace();
		    	//System.out.println("!!!!!!!!!");
		    }
		return u;
		
	}	
}