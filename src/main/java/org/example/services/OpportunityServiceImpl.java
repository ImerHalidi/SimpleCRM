package org.example.services;

import com.google.gson.annotations.SerializedName;
import org.example.common.abstractService;
import org.example.domain.Opportunity;
import org.example.exceptions.ValidationException;
import org.example.exceptions.NotFoundException;


import java.awt.geom.RectangularShape;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OpportunityServiceImpl extends abstractService implements OpportunityService {
    public static class Sql{
        public static final String INSERT_OPPORTUNITY="INSERT INTO opportunity (customer_id,title,value,status,expected_close_date) VALUES (?,?,?,?,?)";

        public static final String FIND_BY_ID="SELECT * FROM opportunity where id=?";

        public static final String FIND_ALL="SELECT * FROM opportunity";

        public static final String UPDATE_OPPORTUNITY="UPDATE opportunity SET customer_id=?,title=?,value=?,status=?,expected_close_date=?,updated_at=NOW() WHERE id=?";

        public static final String DELETE_OPPORTUNITY="DELETE FROM opportunity where id=?";

        public static final String FIND_BY_CUSTOMER_ID="SELECT * FROM opportunity where customer_id=?";

        public static final String CHANGE_STATUS =
                "UPDATE opportunity SET status = ?, updated_at = NOW() WHERE id = ?";
    }

    @Override
    public Opportunity create(Opportunity opportunity){
        String validationmessage=opportunity.validate();
        if(validationmessage!=null){
            throw new ValidationException(validationmessage);
        }

        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps=null;

        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.INSERT_OPPORTUNITY, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,opportunity.getCustomerId());
            ps.setString(2,opportunity.getTitle());
            ps.setDouble(3,opportunity.getValue());
            ps.setString(4,opportunity.getStatus());
            ps.setString(5,opportunity.getExpectedCloseDate());


            ps.executeUpdate();
            rs=ps.getGeneratedKeys();

            if(rs.next()){
                opportunity.setId(rs.getLong(1));
            }
            return opportunity;

        } catch (Exception e) {
            throw handleException(e);
        }
        finally {
            close(con,ps,rs);
        }
    }

    @Override
    public Opportunity findById(Long id) {
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps = null;

        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.FIND_BY_ID);
            ps.setLong(1,id);

            rs=ps.executeQuery();
            if(rs.next()){
                return mapOpportunity(rs);
            }
            throw new NotFoundException("Opportunity not found");
        }
            catch (NotFoundException e) {
                throw e;
            }
         catch (Exception e) {
            throw handleException(e);

        }
        finally {
            close(con,ps,rs);
        }

    }


    @Override
    public List<Opportunity> findAll(){
        List<Opportunity>opportunities=new ArrayList<>();
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps=null;

        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.FIND_ALL);
           rs= ps.executeQuery();
           while(rs.next()){
               opportunities.add(mapOpportunity(rs));
           }
            return opportunities;
        } catch (Exception e) {
            throw handleException(e);
        }
        finally {
            close(con,ps,rs);
        }

    }

    @Override
    public Opportunity update(Long id,Opportunity opportunity){
        findById(id);
        String validationMessage= opportunity.validate();
        if(validationMessage!=null){
            throw new ValidationException(validationMessage);
        }
        Connection con=null;
        PreparedStatement ps=null;

        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.UPDATE_OPPORTUNITY);
            ps.setLong(1,opportunity.getCustomerId());
            ps.setString(2,opportunity.getTitle());
            ps.setDouble(3,opportunity.getValue());
            ps.setString(4,opportunity.getStatus());
            ps.setString(5,opportunity.getExpectedCloseDate());
            ps.setLong(6,id);

            ps.executeUpdate();
            return findById(id);

        } catch (Exception e) {
            throw handleException(e);
        }
        finally {
            close(con,ps);
        }

    }

    public boolean delete(Long id){
        findById(id);
        Connection con=null;
        PreparedStatement ps=null;


        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.DELETE_OPPORTUNITY);
            ps.setLong(1,id);
            ps.executeUpdate();
            return true;
        }
        catch (Exception e){
            throw handleException(e);
        }
        finally {
            close(con,ps);
        }
    }


    @Override
    public List<Opportunity> findByCostumerId(Long customerId){
        List<Opportunity>opportunities=new ArrayList<>();
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps=null;

        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.FIND_BY_CUSTOMER_ID);
            ps.setLong(1,customerId);
            rs= ps.executeQuery();
            while(rs.next()){
                opportunities.add(mapOpportunity(rs));
            }
            return opportunities;
        } catch (Exception e) {
            throw handleException(e);
        }
        finally {
            close(con,ps,rs);
        }

    }

    @Override
    public List<Opportunity> filter(String status,Long customerId){
        List<Opportunity>opportunities=new ArrayList<>();
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps=null;
        try {
            con=getConnection();
            StringBuilder sql=new StringBuilder("SELECT * FROM opportunity WHERE 1=1");
            if(status!=null && !status.trim().isEmpty()){
                sql.append(" AND status = ?");
            }
            if(customerId!=null){
                sql.append(" AND customer_id = ?");
            }
            ps=con.prepareStatement(sql.toString());

            int index=1;
            if(status!= null&&!status.trim().isEmpty()){
                ps.setString(index++,status);
            }

            if (customerId!=null){
                ps.setLong(index++,customerId);
            }
            rs=ps.executeQuery();
            while (rs.next()){
                opportunities.add(mapOpportunity(rs));
            }
            return opportunities;

        } catch (Exception e) {
            throw handleException(e);
        }
        finally {
            close(con,ps,rs);
        }
    }
    @Override
    public Opportunity changeStatus(Long id, String status) {

        if (status == null || status.trim().isEmpty()) {
            throw new ValidationException("Status is Required");
        }

        if (!(status.equals("NEW")
                || status.equals("IN_PROGRESS")
                || status.equals("WON")
                || status.equals("LOST"))) {
            throw new ValidationException("Invalid status");
        }

        findById(id);

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConnection();

            ps = con.prepareStatement(Sql.CHANGE_STATUS);
            ps.setString(1, status);
            ps.setLong(2, id);

            ps.executeUpdate();

            return findById(id);

        } catch (Exception e) {
            throw handleException(e);
        } finally {
            close(con, ps);
        }
    }
    private Opportunity mapOpportunity(ResultSet rs)throws Exception
    {
        Opportunity opportunity=new Opportunity();
        opportunity.setId(rs.getLong("id"));
        opportunity.setCustomerId(rs.getLong("customer_id"));
        opportunity.setTitle(rs.getString("title"));
        opportunity.setValue(rs.getDouble("value"));
        opportunity.setStatus(rs.getString("status"));
        opportunity.setExpectedCloseDate(rs.getString("expected_close_date"));
        opportunity.setCreatedAt(rs.getString("created_at"));
        opportunity.setUpdatedAt(rs.getString("updated_at"));
        return  opportunity;

    }

}
