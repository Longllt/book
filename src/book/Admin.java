package book;
/*
����Աʵ��
*/
/**
 * ����Աʵ��
 * @author longbin
 *
 */
public class Admin {
	private String ID;                 //���
	private String name;           //����
	private String password;      //����
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

