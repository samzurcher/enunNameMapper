package ch.samzurcher.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ParameterizedTypeClassExtractor<T> {

	private final Class<T> clazz;
	private final Class<? super T> baseClazz;

	public ParameterizedTypeClassExtractor(final Class<T> clazz, final Class<? super T> baseClazz) {
		this.clazz = clazz;
		this.baseClazz = baseClazz;
	}

	/**
	 * Return the class of the ith type parameter.
	 * 
	 * Limitation: Works only if the subclass of baseClazz defines all types.
	 * 
	 */
	public Class<?> getClassOfIthParameterizedType(final int i) {
		final Type[] actualTypeArguments = getParameterizedType().getActualTypeArguments();
		if (actualTypeArguments.length > i) {
			final Type type = actualTypeArguments[i];
			if (type instanceof Class) {
				return (Class<?>) type;
			} else {
				// FIXME Implement here the correct retrieval. In case the ith
				// element hasn't been defined in the subclass of baseClazz,
				// then go down the inheritance hierarchy and look for the place
				// where the variable has been defined.
				throw new UnsupportedOperationException("The " + i + "th type of " + baseClazz + " hasn't been defined in the direct subclass of " + baseClazz
						+ " and can therefore not be resolved in the current implementation.");
			}
		} else {
			throw new IllegalArgumentException("The class " + baseClazz + " has only " + actualTypeArguments.length + " type arguments. Therefore the " + i
					+ "th cannot be accessed.");
		}
	}

	private ParameterizedType getParameterizedType() {
		Class<?> currentClass = clazz;
		while (currentClass != null && currentClass.getSuperclass() != baseClazz) {
			currentClass = currentClass.getSuperclass();
		}
		if (currentClass != null) {
			return (ParameterizedType) currentClass.getGenericSuperclass();
		} else {
			throw new IllegalArgumentException("Class " + clazz + " is supposed to be a subclass of " + baseClazz + " but is not.");
		}
	}
}
