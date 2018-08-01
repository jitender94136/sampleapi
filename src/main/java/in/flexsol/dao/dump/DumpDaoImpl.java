package in.flexsol.dao.dump;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import in.flexsol.modal.dump.Dump;


@Repository("dumpDaoImpl")
public class DumpDaoImpl implements DumpDao {

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public int saveDump(Dump dump) {
		String sql = "insert into dump_master (dump,origin) values (?,?)";
		return jdbcTemplate.update(sql,new Object[] {dump.getDump(),dump.getOrigin()});
	}

	@Override
	public List<Dump> fetchAll(int limit, int offset, Date createdgte) {
		String sql = null;
		if(createdgte == null && limit > 0 && offset > 0) {
					sql = "select * from dump_master order by id limit ? offset ?";
					return jdbcTemplate.query(sql, new Object[] {limit,offset}, new DumpRowMapper());
		} else if(limit == 0 && offset == 0) {
					sql = "select * from dump_master where  created_on >= ? order by id";
					return jdbcTemplate.query(sql, new Object[] {createdgte}, new DumpRowMapper());
		} else if(createdgte == null && limit == 0 && offset > 0) {
					sql = "select * from dump_master order by id  offset ?";
					return jdbcTemplate.query(sql, new Object[] {offset}, new DumpRowMapper());
		} else if(createdgte != null && limit > 0 && offset == 0) {
					sql = "select * from dump_master where created_on >= ? order by id  limit ? ";
					return jdbcTemplate.query(sql, new Object[] {createdgte,limit}, new DumpRowMapper());
		} else if(createdgte != null && limit == 0 && offset > 0) {
			sql = "select * from dump_master where created_on >= ? order by id  offset ? ";
			return jdbcTemplate.query(sql, new Object[] {createdgte,offset}, new DumpRowMapper());
        } else {
					sql = "select * from dump_master order by id  limit ? ";
					return jdbcTemplate.query(sql, new Object[] {limit}, new DumpRowMapper());
		}
	}

	@Override
	public List<Dump> fetchAll() {
		String sql = "select * from dump_master";
		return jdbcTemplate.query(sql, new DumpRowMapper());  
	}
}
