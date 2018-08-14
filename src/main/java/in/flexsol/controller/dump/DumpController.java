package in.flexsol.controller.dump;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import in.flexsol.delegate.dump.DumpService;
import in.flexsol.modal.dump.Dump;
import in.flexsol.modal.site.Site;

@RestController
public class DumpController {

		@Qualifier("dumpServiceImpl")
		@Autowired
		DumpService dumpService;
	
		
	    @RequestMapping(value="/dump",method=org.springframework.web.bind.annotation.RequestMethod.POST)
	    public ResponseEntity<String> saveDump(@RequestBody String requestBody ,HttpServletRequest request) {
				try {
	    			 Dump dump = new Dump();
	    			 if(requestBody != null && requestBody.trim().toString().isEmpty() == false) {
	    				 System.out.println("--------------------------incoming data-----------------------");
	    				 System.out.println(requestBody);
	    				 System.out.println("--------------------------incoming data-----------------------");
	    				 requestBody = requestBody.replaceAll("\\n", "");
	    				 requestBody = requestBody.replaceAll("\\s", "");
	    				 requestBody = requestBody.replace("e:", "e\":");
	    				 requestBody = requestBody.replace("d=", "d\":");
	    				 //requestBody = requestBody.replaceAll("\\\\", "");
	    			 }
	    			 int randomSiteId = (int)getRandomIntegerBetweenRange(1.0,5.0);
			 	 	 JSONObject snapshot = new JSONObject(requestBody);
			 	 	 String currentSiteId = randomSiteId == 1 ? snapshot.getString("did") : snapshot.getString("did")+"-"+randomSiteId;
			 	 	 snapshot.put("did",  currentSiteId);
			 	 	 if(snapshot.get("tid").equals("cmb")) {
			 	 		 	 Site baseSiteData = dumpService.getBaseSiteData(randomSiteId);
			 	 		 	 Dump previousSnapshot = dumpService.getPreviousSnapshot(currentSiteId);
			 	 		 	 if(previousSnapshot != null) {
			 	 		 		 JSONObject previousSnapshotJson = new JSONObject(previousSnapshot.getDump());
			 	 		 		 if(previousSnapshotJson.has("dg_status")) {
			 	 		 			 		 int currentDgStatus = (int)getRandomIntegerBetweenRange(1.0,2.0);
						 	 		 		 if(currentDgStatus == 1) {
						 	 		 			double dgConsumed =  new BigDecimal(previousSnapshotJson.getDouble("dg_consumed")).add(new BigDecimal(0.2)).doubleValue();
						 	 		 			snapshot.put("dg_status", "on");
						 	 		 			snapshot.put("dg_consumed", dgConsumed);
						 	 		 		 } else if(currentDgStatus == 2) {
						 	 		 			 snapshot.put("dg_status", "off");
						 	 		 			 snapshot.put("dg_consumed", previousSnapshotJson.getDouble("dg_consumed"));
						 	 		 		 }
			 	 		 		 } else {
			 	 		 			 snapshot.put("dg_status","off");
				 	 		 		 snapshot.put("dg_consumed",0);
			 	 		 		 }
			 	 		 	 } else {
			 	 		 		 snapshot.put("dg_status","off");
			 	 		 		 snapshot.put("dg_consumed",0);
			 	 		 	 }
			 	 		 	 snapshot.put("panel_count", baseSiteData.getPanelCount());
			    			 snapshot.put("battery_count", baseSiteData.getBatteryCount());
			    			 snapshot.put("panel_peak_capacity", baseSiteData.getPanelPeakCapacity());
			    			 snapshot.put("battery_capacity", baseSiteData.getBatteryCapacity());
			    			 snapshot.put("dg_capacity", baseSiteData.getDgCapacity());
			    			 snapshot.put("plant_capacity", baseSiteData.getPlantCapacity());
			    			 if(snapshot.getInt("p1") > 0) {
			    				    int totalPanelPower = snapshot.getInt("p1");
			    				    if(totalPanelPower > 0) {
			    				    		totalPanelPower = totalPanelPower * baseSiteData.getMultiplicationFactor();
			    				    }
			    				    snapshot.put("p1", totalPanelPower+"");
			    			 }
			    			 if(snapshot.getInt("p14") > 0) {
			    				 	int generatedEnergy = snapshot.getInt("p14");
			    				 	generatedEnergy = generatedEnergy * baseSiteData.getMultiplicationFactor();
			    				 	snapshot.put("p14", generatedEnergy+""); //generated energy in Wh
			    			 }
			    			 if(snapshot.getInt("p17") > 0) {
		    				 		int storedEnergy = snapshot.getInt("p17");
		    				 		storedEnergy = storedEnergy * baseSiteData.getMultiplicationFactor();
		    				 		snapshot.put("p17", storedEnergy+""); //stored energy in Wh
		    			     }
			    			 if(snapshot.getInt("p14") > 0) {
					    			 	int energyConsumed = snapshot.getInt("p14")  - snapshot.getInt("p17");
					    			 	if(energyConsumed < 0) {
					    			 				int generatedEnergy = snapshot.getInt("p14");  
					    			 				int storedEnergy = snapshot.getInt("p17");
					    			 				snapshot.put("p14", storedEnergy+"");
					    			 				snapshot.put("p17", generatedEnergy+"");
					    			 				energyConsumed = storedEnergy - generatedEnergy;
					    			 	} 
					    			 	double doubleEnergyConsumed = energyConsumed * getRandomDouble(1.0);
				    			 		energyConsumed = (int) doubleEnergyConsumed; // energy consumed in Wh
					    			 	snapshot.put("energy_consumed", energyConsumed);
					    	 } else {
					    		 		snapshot.put("energy_consumed",0);
			    			 }
			    			
			    	 } 
	    			 requestBody = snapshot.toString();
	    			 dump.setDump(requestBody);
			         dump.setOrigin(request.getRemoteAddr());
			         int returnStatus = dumpService.saveDump(dump);
			         return new ResponseEntity<String>(returnStatus+"", HttpStatus.OK);
	    		} catch(Exception e) {
	    			e.printStackTrace();
	    			return new ResponseEntity<String>("-1", HttpStatus.INTERNAL_SERVER_ERROR);
	    		}
	    }
	    
	    
	    @RequestMapping(value="/dump",method=org.springframework.web.bind.annotation.RequestMethod.GET)
	    public ResponseEntity<List<Dump>> getDump(@RequestParam(required=false,defaultValue = "0") int limit,@RequestParam(required=false,defaultValue = "0") int offset,@RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date createdgte) {
	    	List<Dump> dumpList = null;
	    	HttpStatus status =  HttpStatus.OK;
	    	try {
				  	 if(limit == 0 && offset == 0 && createdgte == null) {
				  		dumpList = dumpService.fetchAll();
				  	 } else {
				  		dumpList = dumpService.fetchAll(limit,offset,createdgte);
				  	 }
		  	} catch(Exception e) {
	    			e.printStackTrace();
	    			status =  HttpStatus.INTERNAL_SERVER_ERROR;
	    	}
	    	return new ResponseEntity<List<Dump>>(dumpList,status);
		}
	    
	    public static int getRandomInteger(double max){
	        int x = (int)(Math.random() * max);
	        return x;
	    }
	    
	    public static double getRandomDouble(double max){
	        double x = Math.random();
	    	x = (x < 0.5 ? 0.7 : x) * max;
	        return x;
	    }
	    
	    public static double getRandomIntegerBetweenRange(double min, double max){
	        double x = (int)(Math.random()*((max-min)+1))+min;
	        return x;
	    }

}
