package book;
/*
管理员实体
*/
/**
 * 管理员实体
 * @author longbin
 *
 */
public class Admin {
	private String ID;                 //编号
	private String name;           //姓名
	private String password;      //密码
	void setID(String id) {
	    this.ID=ID;
	}
	void setName(String name) {
	    this.name=name;
	}
	void setPassword(String password) {
	    this.password=password;
	}
	
	String getID() {
	    return this.ID;
	}
	String getName() {
	    return this.name;
	}
	String getPassword() {
	    return this.password;
	}

}

