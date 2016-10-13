package net.thecatcher.design;

public class SectorBean {
	@Override
	public String toString() {
		return "[sectorName=" + sectorName + ", pci=" + pci + ", psn=" + psn + "]";
	}

	private String sectorName;
	private String pci;
	private String psn;
	
	public SectorBean(String name,String pci,String psn){
		this.sectorName = name;
		this.pci = pci;
		this.psn = psn;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}

	public String getPci() {
		return pci;
	}

	public void setPci(String pci) {
		this.pci = pci;
	}

	public String getPsn() {
		return psn;
	}

	public void setPsn(String psn) {
		this.psn = psn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pci == null) ? 0 : pci.hashCode());
		result = prime * result + ((psn == null) ? 0 : psn.hashCode());
		result = prime * result + ((sectorName == null) ? 0 : sectorName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SectorBean other = (SectorBean) obj;
		if (pci == null) {
			if (other.pci != null)
				return false;
		} else if (!pci.equals(other.pci))
			return false;
		if (psn == null) {
			if (other.psn != null)
				return false;
		} else if (!psn.equals(other.psn))
			return false;
		if (sectorName == null) {
			if (other.sectorName != null)
				return false;
		} else if (!sectorName.equals(other.sectorName))
			return false;
		return true;
	}


	
	

}
