package in.flexsol.dao.dump;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import in.flexsol.modal.dump.Dump;

public class DumpRowMapper implements RowMapper<Dump> {

	 @Override  
	    public Dump mapRow(ResultSet rs, int rownumber) throws SQLException {  
	    	Dump e = new Dump();  
	        e.setId(rs.getInt("id"));
	        String dump = rs.getString("dump");
	        System.out.println(dump);
	        if(dump != null && dump.length() > 0) {
	        	dump = dump.replaceAll("\"", "'");
	        }
	        e.setDump(dump);
	        e.setOrigin(rs.getString("origin"));
	        e.setCreatedOn(rs.getString("created_on"));
	        return e;  
	    }  
	
	
}
