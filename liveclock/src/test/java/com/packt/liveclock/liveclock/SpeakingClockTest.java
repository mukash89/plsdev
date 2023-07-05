package com.packt.liveclock.liveclock;



import org.junit.Before;
import org.junit.Test;

import com.packt.liveclock.liveclock.BasicSpeakingClock;
import com.packt.liveclock.liveclock.InvalidTimeException;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

public class SpeakingClockTest {
	private BasicSpeakingClock basicSpeakingClock;
	
	@Before
	public void setup() {
		this.basicSpeakingClock = new BasicSpeakingClock();
	}
	
	
	@Test
	public void convertReturnsAmTime() {
		Object convert = this.basicSpeakingClock.convert("08:34");
		
		assertThat(convert, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("It's eight thirty four"))));
	}
	
	
	@Test
	public void convertReturnsMidnight() {
		Object convert = this.basicSpeakingClock.convert("00:00");
		
		assertThat(convert, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("It's Midnight"))));
	}
	
	
	@Test
	public void convertReturnsMidday() {
		Object convert = this.basicSpeakingClock.convert("12:00");
		
		assertThat(convert, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("It's Midday"))));
	}
	
	
	@Test
	public void convertReturnsJustBeforeMidnight() {
		Object convert = this.basicSpeakingClock.convert("23:59");
		
		assertThat(convert, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("It's twenty three fifty nine"))));
	}
	
	
	@Test
	public void convertReturnsJustAfterMidnight() {
		Object convert = this.basicSpeakingClock.convert("00:01");
		
		assertThat(convert, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("It's zero one"))));
	}
	
	
	@Test
	public void convertReturnsJustBeforeMidday() {
		Object convert = this.basicSpeakingClock.convert("11:59");
		
		assertThat(convert, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("It's eleven fifty nine"))));
	}
	
	
	@Test
	public void convertReturnsJustAfterMidday() {
		Object convert = this.basicSpeakingClock.convert("12:01");
		
		assertThat(convert, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("It's twelve one"))));
	}
	
	
	@Test
	public void convertReturnsOnePm() {
		Object convert = this.basicSpeakingClock.convert("13:00");
		
		assertThat(convert, is(
				allOf(notNullValue(), instanceOf(String.class), equalTo("It's thirteen zero"))));
	}
	
	@Test(expected = InvalidTimeException.class)
	public void convertReturnsBlankTime() {
		this.basicSpeakingClock.convert("");
	}
	
	@Test(expected = InvalidTimeException.class)
	public void convertReturnsBlankHours() {
		this.basicSpeakingClock.convert(":01");
	}
	
	@Test(expected = InvalidTimeException.class)
	public void convertReturnsBlankMinutes() {
		this.basicSpeakingClock.convert("01:");
	}
	
	@Test(expected = InvalidTimeException.class)
	public void convertReturnsTimeWithoutSeparator() {
		this.basicSpeakingClock.convert("0159");
	}
	
	@Test(expected = InvalidTimeException.class)
	public void convertReturnsTimeWithOneDigitLess() {
		this.basicSpeakingClock.convert("01:5");
	}
	
	@Test(expected = InvalidTimeException.class)
	public void convertReturnsInvalidHours() {
		this.basicSpeakingClock.convert("HH:05");
	}
	
	@Test(expected = InvalidTimeException.class)
	public void convertReturnsInvalidMinutes() {
		this.basicSpeakingClock.convert("01:mm");
	}
	
	@Test(expected = InvalidTimeException.class)
	public void convertReturnsHoursLessRange() {
		this.basicSpeakingClock.convert("-1:05");
	}
	
	@Test(expected = InvalidTimeException.class)
	public void convertReturnsHoursMoreThanRange() {
		this.basicSpeakingClock.convert("24:05");
	}
	
	@Test(expected = InvalidTimeException.class)
	public void convertReturnsMinutesLessRange() {
		this.basicSpeakingClock.convert("01:-1");
	}
	
	@Test(expected = InvalidTimeException.class)
	public void convertReturnsMinutesMoreThanRange() {
		this.basicSpeakingClock.convert("01:60");
	}
}
