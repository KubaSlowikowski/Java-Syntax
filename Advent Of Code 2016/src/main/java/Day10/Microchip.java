package Day10;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Microchip implements Data {

	private final int microchipID;
	private static Set<Integer> idSet = new HashSet<>();
	
	public Microchip(int microchipID) {
		if(microchipID>0 && !idSet.contains(microchipID)) {
			this.microchipID = microchipID;
			idSet.add(microchipID);
		}
		else
			throw new IllegalArgumentException("Can not create microchip with ID=" + microchipID);
	}
	
	public int getID() {
		return microchipID;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(microchipID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Microchip other = (Microchip) obj;
		if (microchipID != other.microchipID)
			return false;
		return true;
	}
	
	public static Set<Integer> getIdSet() {
		return idSet;
	}
	
	@Override
	public String toString() {
		return Integer.toString(microchipID);
	}
}