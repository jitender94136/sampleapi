package in.flexsol.dao.dump;

import java.util.Date;
import java.util.List;

import in.flexsol.modal.dump.Dump;
import in.flexsol.modal.site.Site;

public interface DumpDao {

	int saveDump(Dump dump);

	List<Dump> fetchAll(int limit, int offset, Date createdgte);

	List<Dump> fetchAll();

	List<Site> fetchSiteData();

	Site getBaseSiteData(int randomSiteId);

	Dump getPreviousSnapshot(String currentSiteId);

	
}
