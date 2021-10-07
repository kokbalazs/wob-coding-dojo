package co.uk.wob.remoteload;

import java.util.Random;

public class RandomNumberGenerator {
	
	public int generateRandomNumber() {
		return new Random().nextInt(10) + 1;
	}
	
}
