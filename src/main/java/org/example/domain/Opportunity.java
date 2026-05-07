package org.example.domain;
import com.google.gson.annotations.SerializedName;
import org.example.common.AbstractEntity;

public class Opportunity extends AbstractEntity {
        @SerializedName("customerId")
        private Long customerId;

        @SerializedName("title")
        private String title;

        @SerializedName("value")
        private Double value;

        @SerializedName("status")
        private String status;

        @SerializedName("expectedCloseDate")
        private String expectedCloseDate;

        @Override
        public String validate() {
            if (customerId == null) {
                return "Customer Id required";
            }
            if (title == null || title.trim().isEmpty()) {
                return "Title is required";
            }
            if (value == null || value <= 0) {
                return "Value must be greater than 0";
            }
            if (status == null || status.trim().isEmpty()) {
                return "Status is required";

            }

            if (!(status.equals("New") || status.equals("IN_PROGRESS") || status.equals("WON") || status.equals("LOST"))) {
                return "invalid status";
            }

            if (expectedCloseDate==null||expectedCloseDate.trim().isEmpty()){
                return "Expected Date is required";
            }
            return null;
        }

        public Long getCustomerId(){
            return customerId;
        }
        public void setCustomerId(Long customerId){
            this.customerId=customerId;
        }

        public String getTitle(){
            return title;
        }
        public void setTitle(String title){
            this.title=title;
        }

        public double getValue(){
            return value;
        }
        public void setValue(double value){
            this.value=value;
        }

        public String getStatus(){
            return status;
        }
        public void setStatus(String status){
            this.status=status;
        }

        public String getExpectedCloseDate(){
            return expectedCloseDate;
        }
        public void setExpectedCloseDate(String expectedCloseDate){
            this.expectedCloseDate=expectedCloseDate;
        }

}
