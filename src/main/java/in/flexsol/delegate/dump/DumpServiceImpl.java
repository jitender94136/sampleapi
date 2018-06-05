package in.flexsol.delegate.dump;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import in.flexsol.dao.dump.DumpDao;
import in.flexsol.modal.dump.Dump;

@Service("dumpServiceImpl")
public class DumpServiceImpl implements DumpService{

	
	@Qualifier("dumpDaoImpl")
	@Autowired
	DumpDao dumpDao;
	
	
	@Override
	public int saveDump(Dump dump) {
		return dumpDao.saveDump(dump);
	}


	@Override
	public List<Dump> fetchAll(int limit, int offset, Date createdgte) {
		return dumpDao.fetchAll(limit,offset,createdgte);
	}


	@Override
	public List<Dump> fetchAll() {
		
		return dumpDao.fetchAll();
	}

}
