package Day10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Output implements Data {

	private final int outputID;
	private List<Microchip> elementsList = new ArrayList<>();
	private static Set<Integer> idSet = new HashSet<>();
	
	public Output(int outputID) {
		if(outputID>=0 && !idSet.contains(outputID)) {
			this.outputID = outputID;
			idSet.add(outputID);
		}
		else
			throw new IllegalArgumentException("Can not create output with ID=" + outputID);
	}
	
	public int getID() {
		return outputID;
	}
	
	protected void add(Microchip value) {
		if(elementsList.contains(value))
			throw new IllegalArgumentException("Output" + this.outputID + " already has Microchip=" + value);
		elementsList.add(value);
	}

	@Override
	public int hashCode() {
		return Objects.hash(outputID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Output other = (Output) obj;
		if (outputID != other.outputID)
			return false;
		return true;
	}
	
	protected List<Microchip> getElements() {
		return elementsList;
	}
	
	public static Set<Integer> getIdSet() {
		return idSet;
	}
	
	@Override
	public String toString() {
		return "Output" +  Integer.toString(outputID);
	}
}