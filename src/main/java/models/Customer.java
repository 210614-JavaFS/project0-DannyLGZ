package models;

public class Customer {
	
	private String userId;
	private String userPassword;
//	private int accountNum;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String phoneNum;
	private int last4SSN;
//	private double balance = 0;
//	private boolean accStatus = false;
	
	public Customer() {
		super();
	}

	
	public Customer(String userId, String userPassword) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
	}


//	public Customer(int accountNum, String firstName, String lastName, String address, String email, String phoneNum,
//			int last4ssn, double balance, boolean accStatus) {
//		super();
//		this.accountNum = accountNum;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.address = address;
//		this.email = email;
//		this.phoneNum = phoneNum;
//		last4SSN = last4ssn;
//		this.balance = balance;
//		this.accStatus = accStatus;
//	}

	
	public Customer(String userId, String userPassword, String firstName, String lastName,
			String address, String email, String phoneNum, int last4ssn) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
//		this.accountNum = accountNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phoneNum = phoneNum;
		last4SSN = last4ssn;
//		this.balance = balance;
//		this.accStatus = accStatus;
	}


//	public Customer(int accountNum, String firstName, String lastName, int last4ssn, double balance) {
//		super();
//		this.accountNum = accountNum;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		last4SSN = last4ssn;
//		this.balance = balance;
//	}


	public Customer(String firstName, String lastName, String address, String email, String phoneNum, int last4ssn) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phoneNum = phoneNum;
		last4SSN = last4ssn;
	}


//	public int getAccountNum() {
//		return accountNum;
//	}

//	public void setAccountNum(int accountNum) {
//		this.accountNum = accountNum;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public int getLast4SSN() {
		return last4SSN;
	}

	public void setLast4SSN(int last4ssn) {
		last4SSN = last4ssn;
	}

//	public double getBalance() {
//		return balance;
//	}
//
//	public void setBalance(double balance) {
//		this.balance = balance;
//	}
//
//	public boolean isAccStatus() {
//		return accStatus;
//	}
//
//	public void setAccStatus(boolean accStatus) {
//		this.accStatus = accStatus;
//	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + last4SSN;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (last4SSN != other.last4SSN)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNum == null) {
			if (other.phoneNum != null)
				return false;
		} else if (!phoneNum.equals(other.phoneNum))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
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
		return "Customer [firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", email=" + email + ", phoneNum=" + phoneNum
				+ ", last4SSN=" + last4SSN + "]";
	}
	
	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (accStatus ? 1231 : 1237);
//		result = prime * result + accountNum;
//		result = prime * result + ((address == null) ? 0 : address.hashCode());
//		long temp;
//		temp = Double.doubleToLongBits(balance);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		result = prime * result + ((email == null) ? 0 : email.hashCode());
//		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
//		result = prime * result + last4SSN;
//		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
//		result = prime * result + ((phoneNum == null) ? 0 : phoneNum.hashCode());
//		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
//		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
//		return result;
//	}


//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Customer other = (Customer) obj;
//		if (accStatus != other.accStatus)
//			return false;
//		if (accountNum != other.accountNum)
//			return false;
//		if (address == null) {
//			if (other.address != null)
//				return false;
//		} else if (!address.equals(other.address))
//			return false;
//		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
//			return false;
//		if (email == null) {
//			if (other.email != null)
//				return false;
//		} else if (!email.equals(other.email))
//			return false;
//		if (firstName == null) {
//			if (other.firstName != null)
//				return false;
//		} else if (!firstName.equals(other.firstName))
//			return false;
//		if (last4SSN != other.last4SSN)
//			return false;
//		if (lastName == null) {
//			if (other.lastName != null)
//				return false;
//		} else if (!lastName.equals(other.lastName))
//			return false;
//		if (phoneNum == null) {
//			if (other.phoneNum != null)
//				return false;
//		} else if (!phoneNum.equals(other.phoneNum))
//			return false;
//		if (userId == null) {
//			if (other.userId != null)
//				return false;
//		} else if (!userId.equals(other.userId))
//			return false;
//		if (userPassword == null) {
//			if (other.userPassword != null)
//				return false;
//		} else if (!userPassword.equals(other.userPassword))
//			return false;
//		return true;
//	}
//
//
//	@Override
//	public String toString() {
//		return "Customer [accountNum=" + accountNum + ", firstName=" + firstName + ", lastName=" + lastName
//				+ ", address=" + address + ", email=" + email + ", phoneNum=" + phoneNum + ", last4SSN=" + last4SSN
//				+ ", balance=" + balance + ", accStatus=" + accStatus + "]";
//	}


}
