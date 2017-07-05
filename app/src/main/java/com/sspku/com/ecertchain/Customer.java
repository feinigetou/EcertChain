package com.sspku.com.ecertchain;

import java.util.Set;

import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.User;


/**
 * Created by zuosoul on 2017/6/28.
 */

//@Data
public class Customer implements User {
    private String name;

    private Enrollment enrollment;

    public void setName(String name) {
        this.name = name;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setMspID(String mspID) {
        this.mspID = mspID;
    }

    private Set<String> roles;

    private String account;

    private String affiliation;

    private String mspID;

    public Customer() {
    }

    public Customer(String name, Enrollment enrollment, Set<String> roles, String account, String affiliation, String mspID) {
        this.name = name;
        this.enrollment = enrollment;
        this.roles = roles;
        this.account = account;
        this.affiliation = affiliation;
        this.mspID = mspID;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Set<String> getRoles() {
        return null;
    }

    @Override
    public String getAccount() {
        return null;
    }

    @Override
    public String getAffiliation() {
        return null;
    }

    @Override
    public Enrollment getEnrollment() {
        return null;
    }

    @Override
    public String getMSPID() {
        return mspID;
    }


}

