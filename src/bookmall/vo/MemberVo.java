package bookmall.vo;

public class MemberVo {

	private int member_no;
	private String name;
	private String phone_no;
	private String email;
	private String password;
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "MemberVo [member_no=" + member_no + ", name=" + name + ", phone_no=" + phone_no + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	
}
