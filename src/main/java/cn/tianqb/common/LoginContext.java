package cn.tianqb.common;

/**
 * @author tianqingbo3
 * @date 2021/3/4 22:05
 * @version v1.0
 */
public class LoginContext {

    private static ThreadLocal<LoginContext> threadLocal = new ThreadLocal<>();

    private String username;

    private Integer roleId;

    private String phone;

    private String mail;

    public LoginContext() {
        threadLocal.set(this);
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static LoginContext get() {
        return threadLocal.get();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "LoginContext{" +
                "username='" + username + '\'' +
                ", roleId=" + roleId +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
