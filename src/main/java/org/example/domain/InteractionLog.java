package org.example.domain;
import com.google.gson.annotations.SerializedName;
import org.example.common.AbstractEntity;

public class InteractionLog extends AbstractEntity {
    @SerializedName("customer_id")
    private Long customerId;

    @SerializedName("contact_person_id")
    private Long contactPersonId;

    @SerializedName("type")
    private String type;

    @SerializedName("notes")
    private String notes;

    @Override
    public String validate(){
        if(customerId==null){
            return "Customer Id is required";
        }
        if(type==null||type.trim().isEmpty()){
            return "Type is required";
        }
        if(!(type.equals("CALL") || type.equals("EMAIL") || type.equals("MEETING"))){
            return "Invalid interaction type";
        }
        if(notes==null || notes.trim().isEmpty()){
            return "Notes is required";
        }
        return null;

    }

    public Long getCustomerId(){
        return customerId;
    }
    public void setCustomerId(Long customerId){
        this.customerId=customerId;
    }

    public Long getContactPersonId(){
        return contactPersonId;
    }
    public void setContactPersonId(Long contactPersonId){
        this.contactPersonId=contactPersonId;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type=type;
    }

    public String getNotes(){
        return notes;
    }
    public void setNotes(String notes){
        this.notes=notes;
    }
}
