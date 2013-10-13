package ch.samzurcher.generics;

import java.lang.reflect.Method;

public class Enums {
	private static final String ENUM_VALUES_METHOD_NAME = "values";

	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> T[] values(final Class<T> clazz) {
		try {
			final Method method = clazz.getMethod(ENUM_VALUES_METHOD_NAME);
			return (T[]) method.invoke(null);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
}
