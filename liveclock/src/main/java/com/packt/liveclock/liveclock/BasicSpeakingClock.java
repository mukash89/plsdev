package com.packt.liveclock.liveclock;


public class BasicSpeakingClock implements SpeakingClock {

	private static final String[] UNITS_ARRAY = { "zero", "one", "two", "three", "four", "five", "six", "seven",
			"eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
			"eighteen", "nineteen" };
	private static final String[] TENS_ARRAY = { "", "", "twenty", "thirty", "forty", "fifty" };

	@Override
	public String convert(String time) {
		
		this.validate(time);
		StringBuilder sb = new StringBuilder("It is ");
		if(time.equals("00:00")) {
			sb.append("Midnight");
		} else if(time.equals("12:00"))
		{
			sb.append("Midday");
		} else {
			
			String[] splitTime = time.split(":");
			
			Integer hours = Integer.parseInt(splitTime[0]);
			sb.append(convertInt(hours)+ " ");
			
			Integer minutes = Integer.parseInt(splitTime[1]);
			sb.append(convertInt(minutes));
		}
		

		return sb.toString();
	}

	private String convertInt(final Integer number) {
		if (number < 20)
			return UNITS_ARRAY[number];
		return TENS_ARRAY[number / 10] + ((number % 10 > 0) ? " " + convertInt(number % 10) : "");
	}

	private void validate(final String time) throws InvalidTimeException {
		if (time.isBlank()) {
			throw new InvalidTimeException("Time is Blank");
		}

		if (time.length() != 5 && !time.contains(":")) {
			throw new InvalidTimeException("Invalid time format , should be in , HH:MM");
		}

		String[] timeSplit = time.split(":");

		Integer hours = new Integer(0);
		try {
			hours = Integer.parseInt(timeSplit[0]);
		} catch (NumberFormatException e) {
			throw new InvalidTimeException("Ivalid hours");
		}

		if (hours < 0 || hours > 23) {
			throw new InvalidTimeException("Invalid hours");
		}

		Integer minutes = new Integer(0);
		try {
			minutes = Integer.parseInt(timeSplit[1]);
		} catch (NumberFormatException n) {
			throw new InvalidTimeException("Invalid minutes");
		}

		if (minutes < 0 || minutes > 59) {
			throw new InvalidTimeException("Invalid minutes");
		}

	}

	public static void main(String[] args) {
		
		BasicSpeakingClock bs = new BasicSpeakingClock();
		String res= bs.convert("13:23");
		System.out.println(res);

	}

}
