package day13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.InputLoader;

public class Puzzle1 {
	public Puzzle1() {
		String[] input = InputLoader.asString(13).split("\r\n");
		long timeStamp = Long.parseLong(input[0]);
		String[] IDs = input[1].split(",");
		Schedule s = new Schedule(timeStamp, IDs);
		long[] answer = s.getNextBusAndWaitingTime();
		System.out.println("Answer: " + answer[0]*answer[1]);
	}
}

class Schedule {
	long timeStamp;
	String[] IDs;

	public Schedule(long timeStamp, String[] IDs) {
		this.timeStamp = timeStamp;
		this.IDs = IDs;
	}

	public long[] getNextBusAndWaitingTime() {
		long waitTime = Long.MAX_VALUE;
		long bestID = 0;
		for (String sID : IDs) {
			if (sID.equals("x"))
				continue;
			long nID = Long.parseLong(sID);
			long nIDWaitTime = getWaitTime(nID);
			if (nIDWaitTime < waitTime) {
				bestID = nID;
				waitTime = nIDWaitTime;
			}
		}
		return new long[]{bestID, waitTime};
	}

	public long getWaitTime(long nID) {
		return (long) (Math.ceil((double) timeStamp / nID) * nID - timeStamp);
	}

	public long remainderTheorem() {
		long timeStamp = 1000000;
		long increment = 1;

		for (int i = 0; i < IDs.length; i++) {
			if (IDs[i].equals("x"))
				continue;
			long nID = Long.parseLong(IDs[i]);
			while((timeStamp + i) % nID != 0) {
				timeStamp += increment;
			}
			increment *= nID;
		}
	return timeStamp;
	}


}

class ScheduleTest {
	Schedule s;

	@BeforeEach
	void setup() {
		long timeStamp = 939;
		String[] IDs = "7,13,x,x,59,x,31,19".split(",");
		s = new Schedule(timeStamp, IDs);
	}

	@Test
	void waitTimefor47is1() {
		Assertions.assertEquals(1, s.getWaitTime(47));
	}

	@Test
	void sampleReturns295() {
		long[] answer = s.getNextBusAndWaitingTime();
		Assertions.assertEquals(295, answer[0]*answer[1]);
	}

	@Test
	void sampleReturns1068781() {
		long answer = s.remainderTheorem();
		Assertions.assertEquals(1068781, answer);
	}


}