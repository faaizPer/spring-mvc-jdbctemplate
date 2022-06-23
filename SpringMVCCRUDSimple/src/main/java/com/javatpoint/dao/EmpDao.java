package com.javatpoint.dao;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.jdbc.core.RowMapper;  
import com.javatpoint.beans.Emp;
 

public class EmpDao {  
  
private JdbcTemplate jdbcTemplate;


JdbcTemplate template;  
  
public void setTemplate(JdbcTemplate template) {  
    this.template = template;  
}  
public int save(Emp p){  
    String sql="insert into emp99(name,lastName,destination,password ,admin) values('"+p.getName()+"','"+p.getLastName()+"','"+p.getDestination()+"','"+p.getPassword()+"','"+p.getAdmin()+"')";  
    return template.update(sql);  
}  
public int update(Emp p){  
    String sql="update emp99 set name='"+p.getName()+"',lastname='"+p.getLastName()+"',destination='"+p.getDestination()+"' where id="+p.getId()+"";  
    return template.update(sql);  
}  
public int delete(int id){  
    String sql="delete from emp99 where id="+id+"";  
    return template.update(sql);  
    
} 


public String loginUser(Emp emp ) {
	
	String sql = "select name from emp99 where name=? and password=? ";
	
	try {

		String userId = this.template.queryForObject(sql, new Object[] {
				emp.getId(), emp.getPassword() }, String.class);

		return userId;
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
		return null;
	}
}

  
public Emp getEmpById(int id){  
    String sql="select * from emp99 where id=?";  
    return template.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Emp>(Emp.class));  
}  
public List<Emp> getEmployees(){  
    return template.query("select * from emp99",new RowMapper<Emp>(){  
        public Emp mapRow(ResultSet rs, int row) throws SQLException {  
            Emp e=new Emp();  
            e.setId(rs.getInt(1));  
            e.setName(rs.getString(2));  
            e.setLastName(rs.getString(3));  
            e.setDestination(rs.getString(4)); 
            e.setPassword(rs.getString(5));
            e.setAdmin(rs.getString(6));
            return e;  
        }    
    }); 
}
	public List<Emp> searchEmployees(String string) {
		System.out.println(string);
		return template.query("select * from emp99 where name like '" + string + "%'", new RowMapper<Emp>() {
			public Emp mapRow(ResultSet rs, int row) throws SQLException {
				Emp e=new Emp();  
	            e.setId(rs.getInt(1));  
	            e.setName(rs.getString(2));  
	            e.setLastName(rs.getString(3));  
	            e.setDestination(rs.getString(4)); 
	            e.setPassword(rs.getString(5));
	            e.setAdmin(rs.getString(6));
				return e;
			}
		});
}
public int admin(Emp emp) {
String sql = "select admin from emp99 where name=? and password=? ";
	
	try {

	
		if(sql.equals("yes")) {
			return 1;
		}
		else {
			return 0;
		}
		
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
		return 2;
	}
	
}    
}   
  