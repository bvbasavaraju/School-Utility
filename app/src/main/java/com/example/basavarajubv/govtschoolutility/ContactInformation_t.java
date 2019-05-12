package com.example.basavarajubv.govtschoolutility;

public class ContactInformation_t
{
  private String phoneNumber;
  private String emailAddress;
  private String address;

  public ContactInformation_t()
  {
    this.phoneNumber = "";
    this.emailAddress = "";
    this.address = "";
  }

  public ContactInformation_t(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
    this.emailAddress = "";
    this.address = "";
  }

  public ContactInformation_t(String phoneNumber, String emailAddress)
  {
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    this.address = "";
  }

  public ContactInformation_t(String phoneNumber, String emailAddress, String address)
  {
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
    this.address = address;
  }

  public String getAddress()
  {
    return address;
  }

  public void setAddress(String address)
  {
    this.address = address;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress)
  {
    this.emailAddress = emailAddress;
  }
}
