package models;

public class Account {
	private String userID;
	private String userPassword;
	private String accType;
	private int accNumber;
	private double balance = 0;
	private boolean accStatus = false;
	private int employeeID;
	
	
	public Account() {
		super();
	}
	
	
	public Account(String userID, String userPassword) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
	}


	public Account(String userID, String userPassword, String accType, int accNumber, double balance, boolean accStatus,
			int employeeID) {
		super();
		this.userID = userID;
		this.userPassword = userPassword;
		this.accType = accType;
		this.accNumber = accNumber;
		this.balance = balance;
		this.accStatus = accStatus;
		this.employeeID = employeeID;
	}
	
	
	public String getUserID() {
		return userID;
	}
	
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	public String getUserPassword() {
		return userPassword;
	}
	
	
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
	public String getAccType() {
		return accType;
	}
	
	
	public void setAccType(String accType) {
		this.accType = accType;
	}
	
	
	public int getAccNumber() {
		return accNumber;
	}
	
	
	public void setAccNumber(int accNumber) {
		this.accNumber = accNumber;
	}
	
	
	public double getBalance() {
		return balance;
	}
	
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	public boolean isAccStatus() {
		return accStatus;
	}
	
	
	public void setAccStatus(boolean accStatus) {
		this.accStatus = accStatus;
	}
	
	
	public int getEmployeeID() {
		return employeeID;
	}
	
	
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accNumber;
		result = prime * result + (accStatus ? 1231 : 1237);
		result = prime * result + ((accType == null) ? 0 : accType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + employeeID;
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accNumber != other.accNumber)
			return false;
		if (accStatus != other.accStatus)
			return false;
		if (accType == null) {
			if (other.accType != null)
				return false;
		} else if (!accType.equals(other.accType))
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (employeeID != other.employeeID)
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [userID=" + userID + ", accType=" + accType + ", accNumber="
				+ accNumber + ", balance=" + balance + ", accStatus=" + accStatus + ", employeeID=" + employeeID + "]";
	}
}
