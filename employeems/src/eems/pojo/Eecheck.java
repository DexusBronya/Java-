package eems.pojo;

public class Eecheck {
	
	private String enumber;
	
	private String goonwork; // 1/0 1打卡 0未打卡
	
	private String gooffwork; // 1/0 1打卡 0未打卡
	
	private String date; // datetime类型
	
	private String dateSearch;

	// Getters
	public String getEnumber() {
		return enumber;
	}

	public String getGoonwork() {
		return goonwork;
	}

	public String getGooffwork() {
		return gooffwork;
	}

	public String getDate() {
		return date;
	}

	
	
	public String getDateSearch() {
		return dateSearch;
	}


	// Setters
	public void setEnumber(String enumber) {
		this.enumber = enumber;
	}

	public void setGoonwork(String goonwork) {
		if (!goonwork.equals("1") && !goonwork.equals("0")) {
            System.out.println("上班打卡状态设置错误！");
        } else {
            this.goonwork = goonwork;
        }
	}

	public void setGooffwork(String gooffwork) {
		if (!gooffwork.equals("1") && !gooffwork.equals("0")) {
            System.out.println("下班打卡状态设置错误！");
        } else {
            this.gooffwork = gooffwork;
        }
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void setDateSearch(String dateSearch) {
		this.dateSearch = dateSearch;
	}
	
	
	@Override
	public String toString() {
		return "Eecheck{" +
				"enumber='" + enumber + '\'' +
				", goonwork='" + goonwork + '\'' +
				", gooffwork='" + gooffwork + '\'' +
				", date='" + date + '\'' +
				'}';
	}

}
