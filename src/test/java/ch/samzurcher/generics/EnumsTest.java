package ch.samzurcher.generics;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class EnumsTest {

	@Test
	public void valuesForEnum() {
		assertArrayEquals(EnumA.values(), Enums.values(EnumA.class));
	}

	private enum EnumA {
		A, B, C;
	}
}
