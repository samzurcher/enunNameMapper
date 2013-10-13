package ch.samzurcher.generics;

import static junit.framework.Assert.assertSame;
import static junit.framework.Assert.fail;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import ch.samzurcher.enumNameMapper.AbstractEnumNameMapper;

public class ParameterizedTypeClassExtractorTest {

	@Test
	public void validRetrieval() {
		final ParameterizedTypeClassExtractor<ExampleEnumNameMapper> extractor = new ParameterizedTypeClassExtractor<>(ExampleEnumNameMapper.class,
				AbstractEnumNameMapper.class);
		assertSame(EnumA.class, extractor.getClassOfIthParameterizedType(0));
		assertSame(EnumB.class, extractor.getClassOfIthParameterizedType(1));
		try {
			extractor.getClassOfIthParameterizedType(2);
			fail();
		} catch (final IllegalArgumentException e) {
		}
	}

	@Test
	public void validRetrievalMultipleLevels() {
		final ParameterizedTypeClassExtractor<SecondLevelExampleEnumNameMapper> extractor = new ParameterizedTypeClassExtractor<>(
				SecondLevelExampleEnumNameMapper.class, AbstractEnumNameMapper.class);
		assertSame(EnumA.class, extractor.getClassOfIthParameterizedType(0));
		assertSame(EnumB.class, extractor.getClassOfIthParameterizedType(1));
		try {
			extractor.getClassOfIthParameterizedType(2);
			fail();
		} catch (final IllegalArgumentException e) {
		}
	}

	@Test(expected = UnsupportedOperationException.class)
	public void validRetrievalMultipleLevelsBoundOnDifferentLevels() {
		final ParameterizedTypeClassExtractor<SecondLevelBoundsOneVariableExampleEnumNameMapper> extractor = new ParameterizedTypeClassExtractor<>(
				SecondLevelBoundsOneVariableExampleEnumNameMapper.class, AbstractEnumNameMapper.class);
		assertSame(EnumA.class, extractor.getClassOfIthParameterizedType(0));
		assertSame(EnumB.class, extractor.getClassOfIthParameterizedType(1));
	}

	@SuppressWarnings("rawtypes")
	@Test(expected = IllegalArgumentException.class)
	public void invalidRetrieval() {
		final ParameterizedTypeClassExtractor<List> extractor = new ParameterizedTypeClassExtractor<>(List.class, Collection.class);
		extractor.getClassOfIthParameterizedType(0);
	}

	private class SecondLevelBoundsOneVariableExampleEnumNameMapper extends FirstLevelBoundsOneVariableExampleEnumNameMapper<EnumB> {
	}

	private class FirstLevelBoundsOneVariableExampleEnumNameMapper<T extends Enum<T>> extends AbstractEnumNameMapper<EnumA, T> {
	}

	private class SecondLevelExampleEnumNameMapper extends FirstLevelExampleEnumNameMapper {
	}

	private class FirstLevelExampleEnumNameMapper extends AbstractEnumNameMapper<EnumA, EnumB> {
	}

	private class ExampleEnumNameMapper extends AbstractEnumNameMapper<EnumA, EnumB> {
	}

	private enum EnumA {
		A, B, C;
	}

	private enum EnumB {
		A, B, C;
	}
}
