package Day10;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Bot implements Data {
	
	private final int botID; 
	private Microchip lower;
	private Microchip higher;
	private static Set<Integer> idSet = new HashSet<>();
	
	public Bot(int botID) {
		if(botID>=0 && !idSet.contains(botID)) {
			this.botID = botID;
			idSet.add(botID);
		}
		else
			throw new IllegalArgumentException("Can't create bot with ID=" + botID);
	}
	
	public int getID() {
		return botID;
	}
	
	public boolean hasTwoMicroChips() {
		if(lower!=null && higher!=null && !lower.equals(higher))
			return true;
		else
			return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(botID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bot other = (Bot) obj;
		if (botID != other.botID)
			return false;
		return true;
	}
	
	public Microchip getLower() {
		return lower;
	}

	public Microchip getHigher() {
		return higher;
	}

	public void addValue(Microchip value) {
		if(hasTwoMicroChips()) {
			throw new IllegalArgumentException("Can't add Microchip" + value + "! The bot already has two microchips.");
		}
		if(value.getID()>0) {
			if(higher==null && lower==null) {
				this.lower=value;
				return;
			}
			if(lower!=null && higher!=null) {
				if(value.getID()==lower.getID() || value.getID()==higher.getID())
					throw new IllegalArgumentException("Wrong value=" + value.getID() + "!");
			}
			if(value.getID() > lower.getID()) {
				this.higher=value;
				return;
			}
			else if(value.getID() < lower.getID()) {
				higher = lower;
				lower = value;
			}
		}
		else throw new IllegalArgumentException("Wrong value=" + value + "!");		
	}
	
	public Microchip takeMicrochip(String s) {
		
		switch(s) {
			case "lower": {
				Microchip m = lower;
				lower = null;
				return m;
			}
			case "higher": {
				Microchip m = higher;
				higher = null;
				return m;	
			}
			default: {
				throw new IllegalArgumentException("Wrong argument. Method Bot.takeMicrochip can't consume String=" + s + "!");
			}
		}
	}

	
	public int getBotID() {
		return botID;
	}

	public static Set<Integer> getIdSet() {
		return idSet;
	}
	
	@Override
	public String toString() {
		return "Bot"+botID;
	}
}