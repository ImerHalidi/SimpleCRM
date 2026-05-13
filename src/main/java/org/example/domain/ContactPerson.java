package org.example.domain;
import com.google.gson.annotations.SerializedName;
import org.example.common.AbstractEntity;

public class ContactPerson extends AbstractEntity {
    @SerializedName("customer_id")
    private Long customer_id;

    @SerializedName("full_name")
    private String full_name;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("position")
    private String position;

    public String validate(){
        if(customer_id ==null ){
            return "CustomerId Required";
        }
        if(full_name ==null|| full_name.trim().isEmpty()){
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

    public Long getCustomer_id(){
        return customer_id;
    }
    public void setCustomer_id(Long customer_id){
        this.customer_id = customer_id;
    }

    public String getFull_name(){
        return full_name;
    }
    public void setFull_name(String full_name){
        this.full_name = full_name;
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
