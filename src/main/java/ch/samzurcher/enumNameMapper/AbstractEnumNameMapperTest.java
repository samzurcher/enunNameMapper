package ch.samzurcher.enumNameMapper;

import static junit.framework.Assert.assertSame;

import org.junit.Test;

public abstract class AbstractEnumNameMapperTest<A extends Enum<A>, B extends Enum<B>> {

	@Test
	public void mapAll() {
		final AbstractEnumNameMapper<A, B> mapper = getMapper();
		for (final A item : mapper.getAValues()) {
			assertSame(item, mapper.inverseMap(mapper.map(item)));
		}
	}

	@Test
	public void mapInverseAll() {
		final AbstractEnumNameMapper<A, B> mapper = getMapper();
		for (final B item : mapper.getBValues()) {
			assertSame(item, mapper.map(mapper.inverseMap(item)));
		}
	}

	protected abstract AbstractEnumNameMapper<A, B> getMapper();
}
