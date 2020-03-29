package Day10;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class CommandRegExpTest {
	
	private final String s = "value 5 goes to bot 2"; 
	private final String s2 = "bot 1 gives low to output 1 and high to bot 0";
	private static final Pattern ASSIGN_VALUE_COMMAND_PATTERN = Pattern.compile("value (?<value>\\d+) goes to bot (?<bot>\\d+)");
	private static final Pattern GIVE_VALUE_COMMAND_PATTERN = Pattern.compile("bot (?<givingBot>\\d+) gives low to (?<first>(bot|output)) (?<num1>\\d+) and high to (?<second>(bot|output)) (?<num2>\\d+)");
	
	@Test
	public void shouldReadAssignValueCommand() {
		Matcher matcher = ASSIGN_VALUE_COMMAND_PATTERN.matcher(s);
		assertTrue(matcher.matches());
		assertEquals("5", matcher.group("value"));
		assertEquals("2", matcher.group("bot"));
	}
	
	@Test
	public void shouldReadGiveValueCommand() {
		Matcher matcher = GIVE_VALUE_COMMAND_PATTERN.matcher(s2);
		assertTrue(matcher.matches());
		assertEquals("1", matcher.group("givingBot"));
		assertEquals("output", matcher.group("first"));
		assertEquals("1", matcher.group("num1"));
		assertEquals("bot", matcher.group("second"));
		assertEquals("0", matcher.group("num2"));
	}
}