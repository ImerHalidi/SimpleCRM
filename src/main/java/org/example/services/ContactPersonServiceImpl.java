package org.example.services;
import org.example.domain.ContactPerson;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.ValidationException;
import org.example.common.abstractService;


import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import static org.example.common.DatabaseConnection.close;

public class ContactPersonServiceImpl extends abstractService implements ContactPersonService {
    public static class Sql{
        public static final String INSERT_CONTACT="INSERT INTO contact_person(customerId,fullName,email,phone,position) VALUES (?,?,?,?,?)";

        public static final String FIND_BY_ID="SELECT * FROM contact_person WHERE id = ?";

        public static final String FIND_ALL="SELECT * FROM contact_person ";

        public static final String UPDATE_CONTACT="UPDATE contact_person SET contact_person=?, fullName=?,email=?,phone=?,position=?,update_at=NOW() WHERE id=?";

        public static final String DELETE_CONTACT="DELETE FROM contact_person WHERE id=?";
    }

    @Override
    public ContactPerson create(ContactPerson contactPerson){
        String validationMessage=contactPerson.validate();
        if(validationMessage !=null){
            throw new ValidationException(validationMessage);
        }
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.INSERT_CONTACT,Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,contactPerson.getCustomerId());
            ps.setString(2,contactPerson.getFullName());
            ps.setString(3,contactPerson.getEmail());
            ps.setString(4,contactPerson.getPhone());
            ps.setString(5,contactPerson.getPosition());

            ps.executeUpdate();
            rs=ps.getGeneratedKeys();

            if(rs.next()){
                contactPerson.setId(rs.getLong(1));
            }
            return contactPerson;

        } catch (SQLException e) {
            throw handleException(e);
        }
        finally {
            close(con,ps,rs);
        }

    };

@Override
    public ContactPerson findById(Long id){
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps=null;

        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.FIND_BY_ID);
            ps.setLong(1,id);
            rs=ps.executeQuery();

            if(rs.next()){
                return mapContactPerson(rs);
            }
            throw new NotFoundException("Not Found Contact Person");
        }
        catch (NotFoundException e){
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
    public List<ContactPerson>findAll() {
        List<ContactPerson> contacts = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(Sql.FIND_ALL);
            rs = ps.executeQuery();
            while (rs.next()) {
                contacts.add(mapContactPerson(rs));
            }
        } catch (Exception e) {
            throw handleException(e);
        } finally {
            close(con, ps, rs);
        }
        return contacts;


    }
    @Override
    public ContactPerson update(Long id,ContactPerson contactPerson){
        findById(id);


            String validationmessage = contactPerson.validate();
            if(validationmessage !=null){
                throw new ValidationException(validationmessage);
            }
            Connection con=null;
            PreparedStatement ps=null;

        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.UPDATE_CONTACT);
            ps.setLong(1,contactPerson.getCustomerId());
            ps.setString(2,contactPerson.getFullName());
            ps.setString(3,contactPerson.getEmail());
            ps.setString(4,contactPerson.getPhone());
            ps.setString(5,contactPerson.getPosition());
            ps.setLong(6,id);
            ps.executeQuery();
            return findById(id);

        }
        catch (Exception e) {
            throw handleException(e);
        }
        finally {
            close(con,ps);
        }

        }

        public boolean delete(Long id){
            findById(id);
            Connection con=null;
            ResultSet rs=null;
            PreparedStatement ps=null;

            try {
                con=getConnection();
                ps=con.prepareStatement(Sql.DELETE_CONTACT);
                ps.setLong(1,id);
                ps.executeQuery();

                return true;
            }

            catch (Exception e) {
                throw handleException(e);
            }
            finally {
                close(con,ps);
            }

        }


        private ContactPerson mapContactPerson (ResultSet rs) throws Exception{
            ContactPerson contactPerson=new ContactPerson();
            contactPerson.setId(rs.getLong("id"));
            contactPerson.setCustomerId(rs.getLong("customer_id"));
            contactPerson.setFullName(rs.getString("fullName"));
            contactPerson.setEmail(rs.getString("email"));
            contactPerson.setPhone(rs.getString("phone"));
            contactPerson.setPosition(rs.getString("position"));
            contactPerson.setCreatedAt(rs.getString("created_at"));
            contactPerson.setUpdatedAt(rs.getString("updated_at"));
            return contactPerson;
        }

    }




