package in.flexsol.modal.dump;

import org.json.JSONObject;

public class Dump {
		int id;
		JSONObject dump;
		String createdOn;
		String origin;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCreatedOn() {
			return createdOn;
		}
		public JSONObject getDump() {
			return dump;
		}
		public void setDump(JSONObject dump) {
			this.dump = dump;
		}
		public void setCreatedOn(String createdOn) {
			this.createdOn = createdOn;
		}
		public String getOrigin() {
			return origin;
		}
		public void setOrigin(String origin) {
			this.origin = origin;
		}
		
		
		
		
}
