package org.example.domain;
import com.google.gson.annotations.SerializedName;
import org.example.common.AbstractEntity;

public class ContactPerson extends AbstractEntity {
    @SerializedName("customerId")
    private Long customerId ;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("position")
    private String position;

    public String validate(){
        if(customerId==null ){
            return "CustomerId Required";
        }
        if(fullName==null||fullName.trim().isEmpty()){
            return "FullName Is Required";
        }
        if(email==null||email.trim().isEmpty()){
            return "Email Is Required";
        }
        if(!email.contains("@")){
            return "Email must have @ ";
        }
        if(position==null||position.trim().isEmpty()){
            return "Position Is Required ";
        }
        return null;

    }

    public Long getCustomerId(){
        return customerId;
    }
    public void setCustomerId(Long customerId){
        this.customerId=customerId;
    }

    public String getFullName(){
        return fullName;
    }
    public void setFullName(String fullName){
        this.fullName=fullName;
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

    public String getPosition(){
        return position;
    }
    public void setPosition(String position){
        this.position=position;
    }

}
