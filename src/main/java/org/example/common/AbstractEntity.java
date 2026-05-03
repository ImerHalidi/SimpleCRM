package org.example.common;
import com.google.gson.annotations.SerializedName;

public abstract class AbstractEntity {
    @SerializedName("id")
    private Long id;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(String createdAt){
        this.createdAt=createdAt;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt){
        this.updatedAt=updatedAt;
    }

    public abstract String validate();


}
