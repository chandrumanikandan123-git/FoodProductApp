package com.yourcompany.wholesale;

public class BusinessCustomer {
    private int customerId;
    private String businessName;
    private Address address;
    private String telephoneNumber;

    public BusinessCustomer(int customerId, String businessName, Address address, String telephoneNumber) {
        this.customerId = customerId;
        this.businessName = businessName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
    }

    // Other getters and setters...
    

    @Override
    public String toString() {
        return "BusinessCustomer [customerId=" + customerId + ", businessName=" + businessName + ", address=" + address
                + ", telephoneNumber=" + telephoneNumber + "]";
    }

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
}
