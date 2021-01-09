package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * �û�ע�Ṧ��ʵ����
 * @author longbin
 *
 */
public class Register {
    String name;
    String ID;
    String password;
    String confirmpassword;
    
    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/BOOK?serverTimezone=UTC";
    private String user = "root";
    private String sqlpassword = "521489";
    
    void setName(String name) {
        this.name = name;
    }
    void setID(String ID) {
        this.ID = ID;
    }
    void setPassword(String password) {
        this.password = password;
    }
    void setconfirmpasswd(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }
    
    
    //�ж�ע����˺��Ƿ���Ϲ���
    boolean JudgeRegister() throws SQLException, ClassNotFoundException {
        
        if(this.name.equals("")) {
            JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ�", "�û���", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(this.ID.equals("")) {
            JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ�գ�", "�˺�Ϊ��", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(this.password.equals("")) {
            JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "����Ϊ��", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(!this.password.equals(this.confirmpassword)) {
            JOptionPane.showMessageDialog(null, "������������벻һ��!", "���벻һ��", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //���Ϲ��򣬵���ע��ɹ��Ĵ��ڣ������˺�������ݿ�
        JOptionPane.showMessageDialog(null, "ע��ɹ�");
        addAdmin();
        return true;
    }
    
    //�����ݿ����Admin�˻�
    void addAdmin() {
    	String sql="insert into BOOK (name, ID, password) values (?,?,?)";
    	Connection conn = null;
    	PreparedStatement ps = null;
    	try {
			Class.forName(driver);
			try {
		    	conn = DriverManager.getConnection(url, user, sqlpassword);
		    	ps = conn.prepareStatement(sql);
		    	ps.setString(2, this.ID);
		    	ps.setString(1, this.name);
		        ps.setString(3, this.password);
		        ps.executeUpdate();
	    	}catch(SQLException ex) {
	    		System.out.println("����û�ʧ�ܣ�");
	    	}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	        
		}
    }
}