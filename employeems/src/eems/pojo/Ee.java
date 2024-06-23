package eems.pojo;

public class Ee {

    private int id; // 主键

    private String ename; // 员工姓名

    private String enumber; // 工号

    private String esex; // 性别

    private String eborn; // 出生日期

    private String eentry; // 入职日期

    private int esalary; // 薪水

    private String eauthority; // 权限
    
    private String username;
    
    private String password;

    



	// Getters
    public int getId() {
        return id;
    }

    public String getEname() {
        return ename;
    }

    public String getEnumber() {
        return enumber;
    }

    public String getEsex() {
        return esex;
    }

    public String getEborn() {
        return eborn;
    }

    public String getEentry() {
        return eentry;
    }

    public int getEsalary() {
        return esalary;
    }

    public String getEauthority() {
        return eauthority;
    }
    
    
    public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}


    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setEname(String ename) {
        if (ename == null || ename.isEmpty()) {
            throw new IllegalArgumentException("姓名不能为空！");
        }
        this.ename = ename;
    }

    public void setEnumber(String enumber) throws IllegalArgumentException {
        String enumberStr = String.valueOf(enumber);
        if (enumberStr.length() != 5 && enumberStr.length() != 1) {
            throw new IllegalArgumentException("工号必须为5位数！");
        } else {
            this.enumber = enumber;
        }
    }

    public void setEsex(String esex) throws IllegalArgumentException {
        if (!esex.equals("男") && !esex.equals("女")) {
            throw new IllegalArgumentException("性别设置错误！");
        } else {
            this.esex = esex;
        }
    }

    public void setEborn(String eborn) throws IllegalArgumentException {
        if (eborn == null) {
            throw new IllegalArgumentException("出生日期不能为空！");
        }
        
        if (!eborn.matches("\\d{4}-\\d{2}-\\d{2}") && !eborn.matches("\\d{4}-\\d{1}-\\d{2}") && !eborn.matches("\\d{4}-\\d{1}-\\d{1}") && !eborn.matches("\\d{4}-\\d{2}-\\d{1}")) {
            throw new IllegalArgumentException("出生日期格式错误！");
        } else {
            this.eborn = eborn;
        }
    }

    public void setEentry(String eentry) throws IllegalArgumentException {
        if (!eborn.matches("\\d{4}-\\d{2}-\\d{2}") && !eborn.matches("\\d{4}-\\d{1}-\\d{2}") && !eborn.matches("\\d{4}-\\d{1}-\\d{1}") && !eborn.matches("\\d{4}-\\d{2}-\\d{1}")) {
            throw new IllegalArgumentException("入职日期格式错误！");
        } else {
            this.eentry = eentry;
        }
    }

    public void setEsalary(int esalary) {
        if (esalary <= -1) {
            throw new IllegalArgumentException("薪水不能为空或小于等于零，必须为自然数！");
        }
        this.esalary = esalary;
    }

    public void setEauthority(String eauthority) throws IllegalArgumentException {
        if (!eauthority.equals("1") && !eauthority.equals("0") && !eauthority.equals("2")) {
            throw new IllegalArgumentException("权限设置错误！0为超级管理员 1为管理员 2为普通员工");
        } else {
            this.eauthority = eauthority;
        }
    }

    
    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("账号不能为空！");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("密码不能为空！");
        }
        this.password = password;
    }
    
    @Override
    public String toString() {
        return "员工姓名：" + ename + "，员工编号：" + enumber + "，性别：" + esex 
             + "，出生日期：" + eborn + "，入职日期：" + eentry + "，薪水：" + esalary 
             + "，权限：" + eauthority;
    }

}