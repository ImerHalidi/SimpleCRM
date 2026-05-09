package org.example.services;
import org.example.common.DatabaseConnection;
import org.example.domain.Customer;
import org.example.exceptions.ConflictException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.ValidationException;
import org.example.common.abstractService;


import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import static org.example.common.DatabaseConnection.close;
import static org.example.common.DatabaseConnection.getConnection;

public class CustomerServiceImpl extends abstractService implements CustomerService {
    public static class Sql {
        public static final String INSERT_CUSTOMER = "Insert into customer (name,industry,email,phone,status) VALUES(?,?,?,?,?)";

        public static final String FIND_BY_ID = "SELECT * FROM customer WHERE id=?";

        public static final String FIND_ALL = "SELECT * FROM customer";

        public static final String UPDATE_CUSTOMER = "UPDATE customer SET name=?,industry=?,email=?,phone=?,status=?,updated_at=NOW() WHERE id= ?)";

        public static final String DELETE_CUSTOMER = "DELETE FROM customer where id=?";
    }



    @Override
    public Customer create(Customer customer) {
        String validationMessage = customer.validate();
        if (validationMessage != null) {
            throw new ValidationException(validationMessage);
        }
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            ps = con.prepareStatement(Sql.INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getIndustry());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhone());
            ps.setString(5, customer.getStatus());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            if (rs.next()) {
                customer.setId(rs.getLong(1));
            }
            return customer;
        } catch (Exception e) {
            throw handleException(e);
        } finally {
            close(con, ps, rs);
        }
    }

    ;


    @Override
    public Customer findById(Long id) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            con = getConnection();
            ps = con.prepareStatement(Sql.FIND_BY_ID);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return mapCostumer(rs);
            }
            throw new NotFoundException("Customer Not Found");
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw handleException(e);
        } finally {
            close(con, ps, rs);
        }

    }

    @Override
    public List<Customer>findAll(){
        List<Customer>customers=new ArrayList<>();
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps=null;
        try {
            con =getConnection();
            ps=con.prepareStatement(Sql.FIND_ALL);
            rs= ps.executeQuery();

            while (rs.next()){
                customers.add(mapCostumer(rs));
            }
            return customers;

        }
        catch (Exception e){
            throw handleException(e);
        }
        finally {
            close(con,ps,rs);
        }
    }

    @Override
    public Customer update(Long id,Customer customer){
        findById(id); // kontrollimi se a egziston customer
        String validationmessage=customer.validate();

        if(validationmessage!=null){
            throw new ValidationException(validationmessage);
        }

        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps=null;

        try{
            con=getConnection();
            ps=con.prepareStatement(Sql.UPDATE_CUSTOMER);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getIndustry());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhone());
            ps.setString(5, customer.getStatus());
            ps.setLong(6,id);
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
        findById(id);

        Connection con=null;

        PreparedStatement ps=null;
        try {
            con=getConnection();
            ps=con.prepareStatement(Sql.DELETE_CUSTOMER);
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






    private Customer mapCostumer(ResultSet rs) throws Exception {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setName(rs.getString("name"));
        customer.setIndustry(rs.getString("industry"));
        customer.setEmail(rs.getString("email"));
        customer.setPhone(rs.getString("phone"));
        customer.setStatus(rs.getString("status"));
        customer.setCreatedAt(rs.getString("created_at"));
        customer.setUpdatedAt(rs.getString("updated_at"));
        return customer;

    }
}
