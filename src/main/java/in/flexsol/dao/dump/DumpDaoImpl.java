package in.flexsol.dao.dump;

import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.flexsol.modal.dump.Dump;
import in.flexsol.modal.site.Site;


@Repository("dumpDaoImpl")
public class DumpDaoImpl implements DumpDao {

	private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int saveDump(Dump dump) {
		String sql = "insert into dump_master (dump,origin) values (?,?)";
		return jdbcTemplate.update(sql,new Object[] {dump.getDump(),dump.getOrigin()});
	}

	@Override
	public List<Dump> fetchAll(int limit, int offset, Date createdgte) {
		
		
		
		String sql = null;
		if(createdgte == null && limit > 0 && offset > 0) {
					sql = "select * from dump_master where dump like ? order by id desc limit ? offset ?";
					return jdbcTemplate.query(sql, new Object[] {"%\"tid\":\"cmb\"%",limit,offset}, new DumpRowMapper());
		} else if(limit == 0 && offset == 0) {
					sql = "select * from dump_master where dump like ? and created_on >= ? order by id desc";
					return jdbcTemplate.query(sql, new Object[] {"%\"tid\":\"cmb\"%",createdgte}, new DumpRowMapper());
		} else if(createdgte == null && limit == 0 && offset > 0) {
					sql = "select * from dump_master where dump like ? order by id desc offset ?";
					return jdbcTemplate.query(sql, new Object[] {"%\"tid\":\"cmb\"%",offset}, new DumpRowMapper());
		} else if(createdgte != null && limit > 0 && offset == 0) {
					sql = "select * from dump_master where dump like ? created_on >= ? order by id desc limit ? ";
					return jdbcTemplate.query(sql, new Object[] {"%\"tid\":\"cmb\"%",createdgte,limit}, new DumpRowMapper());
		} else if(createdgte != null && limit == 0 && offset > 0) {
			sql = "select * from dump_master where dump like ? and created_on >= ? order by id desc offset ? ";
			return jdbcTemplate.query(sql, new Object[] {"%\"tid\":\"cmb\"%",createdgte,offset}, new DumpRowMapper());
        } else {
					sql = "select * from dump_master where dump like ? order by id desc limit ? ";
					return jdbcTemplate.query(sql, new Object[] {"%\"tid\":\"cmb\"%",limit}, new DumpRowMapper());
		}
	}

	@Override
	public List<Dump> fetchAll() {
		String sql = "select * from dump_master where dump like ? order by id desc ";
		return jdbcTemplate.query(sql, new Object[] {"%\"tid\":\"cmb\"%"}, new DumpRowMapper());  
	}

	@Override
	public List<Site> fetchSiteData() {
		String sql = "select * from site_master";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Site>(Site.class));
	}

	@Override
	public Site getBaseSiteData(int randomSiteId) {
		String sql = "select * from site_master where id = ?";
		return jdbcTemplate.queryForObject(sql,new Object[] {randomSiteId},new BeanPropertyRowMapper<Site>(Site.class));
	}

	@Override
	public Dump getPreviousSnapshot(String currentSiteId) {
		String sql = "SELECT * FROM dump_master WHERE dump LIKE ? and dump LIKE ? ORDER BY id DESC limit 1";
		return jdbcTemplate.queryForObject(sql,new Object[] {"%"+currentSiteId+"%", "%\"tid\":\"cmb\"%"},new BeanPropertyRowMapper<Dump>(Dump.class));
	}

	
	
}
