package ch.samzurcher.enumNameMapper;

import ch.samzurcher.generics.Enums;
import ch.samzurcher.generics.ParameterizedTypeClassExtractor;

public abstract class AbstractEnumNameMapper<A extends Enum<A>, B extends Enum<B>> {
	private final A[] aValues;
	private final B[] bValues;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AbstractEnumNameMapper() {
		final ParameterizedTypeClassExtractor parameterizedTypeClassExtractor = new ParameterizedTypeClassExtractor(getClass(), AbstractEnumNameMapper.class);
		this.aValues = (A[]) Enums.values(parameterizedTypeClassExtractor.getClassOfIthParameterizedType(0));
		this.bValues = (B[]) Enums.values(parameterizedTypeClassExtractor.getClassOfIthParameterizedType(1));
	}

	public B map(final A value) {
		for (final B item : bValues) {
			if (item.name().equals(value.name())) {
				return item;
			}
		}
		return null;
	}

	public A inverseMap(final B value) {
		for (final A item : aValues) {
			if (item.name().equals(value.name())) {
				return item;
			}
		}
		return null;
	}

	A[] getAValues() {
		return aValues;
	}

	B[] getBValues() {
		return bValues;
	}
}
