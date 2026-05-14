package org.example.services;

import org.example.common.AbstractEntity;
import org.example.common.abstractService;
import org.example.domain.InteractionLog;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.ValidationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InteractionLogServiceImpl extends abstractService implements InteractionLogService {
    public static class Sql{
        public static final String INSERT_INTERACTION_LOG_SERVICE="INSERT INTO interaction_log (customer_id,contact_person_id,type,notes)VALUES(?,?,?,?)";
        public static final String FIND_BY_ID="SELECT * FROM interaction_log where id=?";

        public static final String FIND_ALL="SELECT * FROM interaction_log";

        public static final String UPDATE_OPPORTUNITY="UPDATE interaction_log SET customer_id=?,contact_person_id=?,type=?,notes=?,updated_at=NOW() WHERE id=?";

        public static final String DELETE_OPPORTUNITY="DELETE FROM interaction_log where id=?";
    }

    @Override
    public InteractionLog create(InteractionLog interactionLog){
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            String validationMessage= interactionLog.validate();
            if(validationMessage!=null){
                throw new ValidationException(validationMessage);
            }

            con=getConnection();
            ps= con.prepareStatement(Sql.INSERT_INTERACTION_LOG_SERVICE, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,interactionLog.getCustomerId());
            if(interactionLog.getContactPersonId()!=null){
            ps.setLong(2,interactionLog.getContactPersonId());}
            else{
                ps.setNull(2,java.sql.Types.BIGINT);
            }
            ps.setString(3,interactionLog.getType());
            ps.setString(4,interactionLog.getNotes());

            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            if(rs.next()){
                interactionLog.setId(rs.getLong(1));
            }
            return interactionLog;
        }
        catch (Exception e) {
            throw handleException(e);
        }
        finally {
            close(con,ps,rs);
        }

    }

    @Override
    public InteractionLog findById(Long id){
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.FIND_BY_ID);
            ps.setLong(1,id);
            rs=ps.executeQuery();

            if(rs.next()){
               return mapInteractionLog(rs);
            }
            throw new NotFoundException("Interaction Log Not Found");

        } catch (NotFoundException e) {
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
    public List<InteractionLog>findAll(){
        List<InteractionLog>interactions=new ArrayList<>();
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.FIND_ALL);
            rs=ps.executeQuery();
            while(rs.next()){
                interactions.add(mapInteractionLog(rs));
            }
            return interactions;

        } catch (Exception e) {
            throw handleException(e);
        }
        finally {
            close(con,ps,rs);
        }
    }

    @Override
    public InteractionLog update(Long id,InteractionLog interactionLog){
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.UPDATE_OPPORTUNITY);
            ps.setLong(1,interactionLog.getCustomerId());
            if(interactionLog.getContactPersonId()!=null){
                ps.setLong(2,interactionLog.getContactPersonId());}
            else{
                ps.setNull(2,java.sql.Types.BIGINT);
            }
            ps.setString(3,interactionLog.getType());
            ps.setString(4,interactionLog.getNotes());
            ps.setLong(5,id);
            ps.executeUpdate();

            return findById(id);
        } catch (Exception e) {
            throw handleException(e);
        }
        finally {
            close(con,ps,rs);
        }

    }

    @Override
   public boolean delete(Long id){
    Connection con=null;
    PreparedStatement ps=null;
    try {
        con=getConnection();
        ps=con.prepareStatement(Sql.DELETE_OPPORTUNITY);
        ps.setLong(1,id);
        ps.executeUpdate();
        return true;

    } catch (Exception e) {
        throw handleException(e);
    }
    finally {
        close(con,ps);
    }
   }

   private InteractionLog mapInteractionLog(ResultSet rs)throws Exception{
        InteractionLog interactionLogs =new InteractionLog();
        interactionLogs.setId(rs.getLong("id"));
        interactionLogs.setCustomerId(rs.getLong("customer_id"));
        long ContactPersonId=rs.getLong("contact_person_id");
        if(!rs.wasNull()){
            interactionLogs.setContactPersonId(ContactPersonId);
        }
        interactionLogs.setType(rs.getString("type"));
        interactionLogs.setNotes(rs.getString("notes"));
        interactionLogs.setCreatedAt(rs.getString("created_at"));
        interactionLogs.setUpdatedAt(rs.getString("updated_at"));
        return interactionLogs;
   }

}
