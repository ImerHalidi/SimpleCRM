package org.example.domain;
import com.google.gson.annotations.SerializedName;
import org.example.common.AbstractEntity;

public class Customer extends AbstractEntity {
    @SerializedName ("name")
        private String name;
    @SerializedName ("industry")
    private  String industry;

    @SerializedName ("email")
    private String email;

    @SerializedName ("phone")
    private String phone;

    @SerializedName ("status")
    private String status;


    @Override
    public String validate(){
        if(name==null || name.trim().isEmpty()){
            return "Name is Empty";
        }
        if(email==null ||email.trim().isEmpty()){
            return "Email is Empty";
        }
        if(!email.contains("@")){
            return "Email need to content @";
        }
        if(status==null ||status.trim().isEmpty()){
            return "Status is empty";
        }
        return null;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getIndustry(){
        return industry;
    }
    public void setIndustry(String industry){
        this.industry=industry;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }

}
