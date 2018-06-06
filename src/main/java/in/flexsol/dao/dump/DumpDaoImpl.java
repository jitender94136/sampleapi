package in.flexsol.dao.dump;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
					return jdbcTemplate.query(sql, new Object[] {limit,offset}, new BeanPropertyRowMapper<Dump>(Dump.class));
		} else if(limit == 0 && offset == 0) {
					sql = "select * from dump_master order by id created_on >= ?";
					return jdbcTemplate.query(sql, new Object[] {createdgte}, new BeanPropertyRowMapper<Dump>(Dump.class));
		} else if(limit == 0) {
					sql = "select * from dump_master order by id  offset ? created_on >= ?";
					return jdbcTemplate.query(sql, new Object[] {offset,createdgte}, new BeanPropertyRowMapper<Dump>(Dump.class));
		} else {
					sql = "select * from dump_master order by id  limit ? created_on >= ?";
					return jdbcTemplate.query(sql, new Object[] {limit,createdgte}, new BeanPropertyRowMapper<Dump>(Dump.class));
		}
	}

	@Override
	public List<Dump> fetchAll() {
		String sql = "select * from dump_master";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Dump>(Dump.class));
	}
}
