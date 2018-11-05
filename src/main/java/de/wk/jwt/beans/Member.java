package de.wk.jwt.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {

    private String memberId;
    private String organizationId;
    private String loginName;
    private String title;
    private String name1;
    private String name2;
    private String email;
    private String country;
    private String culture;
    private String city;
    private String street;
    private String zip;
    private String formattedAddress;
    private String memberType;
    private String password;
    private String isDeactivated;

    @JsonProperty("City")
    public String getCity() {
        return city;
    }

    @JsonProperty("City")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("Street")
    public String getStreet() {
        return street;
    }

    @JsonProperty("Street")
    public void setStreet(String street) {
        this.street = street;
    }

    @JsonProperty("Country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("Country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("Culture")
    public String getCulture() {
        return culture;
    }

    @JsonProperty("Culture")
    public void setCulture(String culture) {
        this.culture = culture;
    }

    @JsonProperty("IsDeactivated")
    public String getIsDeactivated() {
        return isDeactivated;
    }

    @JsonProperty("IsDeactivated")
    public void setIsDeactivated(String isDeactivated) {
        this.isDeactivated = isDeactivated;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("Email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("FormattedAddress")
    public String getFormattedAddress() {
        return formattedAddress;
    }

    @JsonProperty("FormattedAddress")
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("Zip")
    public String getZip() {
        return zip;
    }

    @JsonProperty("Zip")
    public void setZip(String zip) {
        this.zip = zip;
    }

    @JsonProperty("MemberId")
    public String getMemberId() {
        return memberId;
    }

    @JsonProperty("MemberId")
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @JsonProperty("OrganizationId")
    public String getOrganizationId() {
        return organizationId;
    }

    @JsonProperty("OrganizationId")
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @JsonProperty("LoginName")
    public String getLoginName() {
        return loginName;
    }

    @JsonProperty("LoginName")
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @JsonProperty("Name1")
    public String getName1() {
        return name1;
    }

    @JsonProperty("Name1")
    public void setName1(String name1) {
        this.name1 = name1;
    }

    @JsonProperty("Name2")
    public String getName2() {
        return name2;
    }

    @JsonProperty("Name2")
    public void setName2(String name2) {
        this.name2 = name2;
    }

    @JsonProperty("MemberType")
    public String getMemberType() {
        return memberType;
    }

    @JsonProperty("MemberType")
    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    @JsonProperty("Password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("Password")
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Member{" +
            "memberId='" + memberId + '\'' +
            ", organizationId='" + organizationId + '\'' +
            ", loginName='" + loginName + '\'' +
            ", title='" + title + '\'' +
            ", name1='" + name1 + '\'' +
            ", name2='" + name2 + '\'' +
            ", email='" + email + '\'' +
            ", country='" + country + '\'' +
            ", culture='" + culture + '\'' +
            ", city='" + city + '\'' +
            ", street='" + street + '\'' +
            ", zip='" + zip + '\'' +
            ", formattedAddress='" + formattedAddress + '\'' +
            ", memberType='" + memberType + '\'' +
            ", password='" + password + '\'' +
            ", isDeactivated='" + isDeactivated + '\'' +
//            ", properties=" + properties +
            '}';
    }
}
