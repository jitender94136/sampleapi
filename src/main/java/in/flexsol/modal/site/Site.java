package in.flexsol.modal.site;

public class Site {

	
				private int id;
				private String siteId;
				private int panelCount;
				private int batteryCount;
				private int batteryCapacity;
				private double temparatureCoefficent;
				private double efficiencyPerc;
				private int panelPeakCapacity;
				private String panelBrand;
				private int dgCapacity;
				private int plantCapacity;
				private int multiplicationFactor;
				
				public int getId() {
					return id;
				}
				public int getDgCapacity() {
					return dgCapacity;
				}
				public void setDgCapacity(int dgCapacity) {
					this.dgCapacity = dgCapacity;
				}
				public int getPlantCapacity() {
					return plantCapacity;
				}
				public void setPlantCapacity(int plantCapacity) {
					this.plantCapacity = plantCapacity;
				}
				public void setId(int id) {
					this.id = id;
				}
				public String getSiteId() {
					return siteId;
				}
				public void setSiteId(String siteId) {
					this.siteId = siteId;
				}
				public int getPanelCount() {
					return panelCount;
				}
				public void setPanelCount(int panelCount) {
					this.panelCount = panelCount;
				}
				public int getBatteryCount() {
					return batteryCount;
				}
				public void setBatteryCount(int batteryCount) {
					this.batteryCount = batteryCount;
				}
				
				public int getBatteryCapacity() {
					return batteryCapacity;
				}
				public void setBatteryCapacity(int batteryCapacity) {
					this.batteryCapacity = batteryCapacity;
				}
				public double getTemparatureCoefficent() {
					return temparatureCoefficent;
				}
				public void setTemparatureCoefficent(double temparatureCoefficent) {
					this.temparatureCoefficent = temparatureCoefficent;
				}
				public int getPanelPeakCapacity() {
					return panelPeakCapacity;
				}
				public void setPanelPeakCapacity(int panelPeakCapacity) {
					this.panelPeakCapacity = panelPeakCapacity;
				}
				public String getPanelBrand() {
					return panelBrand;
				}
				public void setPanelBrand(String panelBrand) {
					this.panelBrand = panelBrand;
				}
				public double getEfficiencyPerc() {
					return efficiencyPerc;
				}
				public void setEfficiencyPerc(double efficiencyPerc) {
					this.efficiencyPerc = efficiencyPerc;
				}
				public int getMultiplicationFactor() {
					return multiplicationFactor;
				}
				public void setMultiplicationFactor(int multiplicationFactor) {
					this.multiplicationFactor = multiplicationFactor;
				}
				
	
}
