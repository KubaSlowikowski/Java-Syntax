package Day10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Computer {
	
	private static final String INPUT_PATH = "C:\\Users\\Admin\\IdeaProjects\\Java\\Advent Of Code 2016\\src\\main\\resources\\botCommands.txt";
	private List<String> commandsList = new ArrayList<>();
	private static final Pattern ASSIGN_VALUE_COMMAND_PATTERN = Pattern.compile("value (?<value>\\d+) goes to bot (?<bot>\\d+)");
	private static final Pattern GIVE_VALUE_COMMAND_PATTERN = Pattern.compile("bot (?<givingBot>\\d+) gives low to (?<first>(bot|output)) (?<num1>\\d+) and high to (?<second>(bot|output)) (?<num2>\\d+)");
	private static List<String> assignCommandList = new ArrayList<>();
	private static List<String> giveCommandList = new ArrayList<>();
	private static Set<Bot> botsSet = new HashSet<>(); 
	private static Set<Microchip> microchipsSet = new HashSet<>();
	private static Set<Output> outputsSet = new HashSet<>();
	
	
	Computer() {
		readFile(INPUT_PATH);
		groupCommands();
		//System.out.println(commandsList);
		System.out.println(assignCommandList);
		doAssignCommands();
		for(Bot bot : botsSet) {
			System.out.println(bot + " " + bot.getLower() + " " + bot.getHigher());
		}
		System.out.println(giveCommandList);
		doGiveCommands();
		for(Bot bot : botsSet) {
			System.out.println(bot + " " + bot.getLower() + " " + bot.getHigher());
		}
		for(Output output : outputsSet) {
			System.out.println(output + ": " + output.getElements());
		}
	}
	
	private void groupCommands() {
		for(String command : commandsList) {
			Matcher matcher = ASSIGN_VALUE_COMMAND_PATTERN.matcher(command);
			if(matcher.matches()) {
				assignCommandList.add(command);
				continue;
			}
			matcher = GIVE_VALUE_COMMAND_PATTERN.matcher(command);
			if(matcher.matches()) {
				giveCommandList.add(command);
				continue;
			}
		}
	}
	
	private void doAssignCommands() {
		for(String command : assignCommandList) {
			
			Matcher matcher = ASSIGN_VALUE_COMMAND_PATTERN.matcher(command);
			if(matcher.matches()) {
				
				final int botID = Integer.parseInt(matcher.group("bot"));	
				final int value = Integer.parseInt(matcher.group("value"));
				
				if(Microchip.getIdSet().contains(Integer.valueOf(value)))
					throw new IllegalArgumentException("Problem occurred during proceeding data! Mikrochip.idSet contains same value=" + value + " and throws exception");

				Microchip m = new Microchip(value);
				microchipsSet.add(m);
				if(Bot.getIdSet().contains(Integer.valueOf(botID))) {
					for(Bot bot : botsSet) {
						if(bot.getID() == botID) {
							bot.addValue(m);
							microchipsSet.add(m);
							continue;
						}
					}
				} 
				else {
					Bot bot = new Bot(botID);
					bot.addValue(m);
					botsSet.add(bot);
					continue;
				}
			}
		}
		assignCommandList.clear();
	}
	
	private void doGiveCommands() {
		for(String command : giveCommandList) {
			
			Matcher matcher = GIVE_VALUE_COMMAND_PATTERN.matcher(command);
			if(matcher.matches()) {
				final int givingBotID = Integer.parseInt(matcher.group("givingBot"));
				final String firstTarget = matcher.group("first"); //output or bot
				final String secondTarget = matcher.group("second");
				final int firstID = Integer.parseInt(matcher.group("num1"));
				final int secondID = Integer.parseInt(matcher.group("num2"));
				Microchip lower = null;
				Microchip higher = null;
				
				if(!Bot.getIdSet().contains(givingBotID))
					throw new IllegalArgumentException("Problem occurred during proceeding data! Bot with ID=" + givingBotID + " does not exist!");

				for(Bot bot : botsSet) {
					if(bot.getBotID() == givingBotID) {
						if(!bot.hasTwoMicroChips())
							throw new IllegalArgumentException("Error! Bot" + givingBotID + " does not have 2 microchips!");
						lower = bot.takeMicrochip("lower");
						higher = bot.takeMicrochip("higher");
					}
				}
				
				switch(firstTarget) {
					case "output": {
						if(!Output.getIdSet().contains(firstID)) {
							Output output = new Output(firstID);
							outputsSet.add(output);
							output.add(lower);
						}
						else {
							for(Output output : outputsSet) {
								if(output.getID() == firstID)
									output.add(lower);
							}
						}
						break;
					}
					case "bot": {
						if(!Bot.getIdSet().contains(firstID)) {
							Bot bot = new Bot(firstID);
							botsSet.add(bot);
							bot.addValue(lower);
						}
						else {
							for(Bot bot : botsSet) {
								if(bot.getBotID() == firstID)
									bot.addValue(lower);
							}
						}
							break;
					}
				}
				
				switch(secondTarget) {
					case "output": {
						if(!Output.getIdSet().contains(secondID)) {
							Output output = new Output(secondID);
							outputsSet.add(output);
							output.add(higher);
						}
						else {
							for(Output output : outputsSet) {
								if(output.getID() == secondID)
									output.add(higher);
							}
						}
						break;
					}
					case "bot": {
						if(!Bot.getIdSet().contains(secondID)) {
							Bot bot = new Bot(secondID);
							botsSet.add(bot);
							bot.addValue(higher);
						}
						else {
							for(Bot bot : botsSet) {
								if(bot.getBotID() == secondID)
									bot.addValue(higher);
							}
						}
						break;
					}
				}
			}
		}
	}
	
	private void readFile(String inputPath) {
		try( BufferedReader fileReader = new BufferedReader(new FileReader(inputPath));) {
			while(true) {
				String s = fileReader.readLine();
				if(!s.isEmpty()) {
					commandsList.add(s);
				} 
				else break;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {}
	}
	
	public static void main(String[] args) {
		new Computer();
	}

}

