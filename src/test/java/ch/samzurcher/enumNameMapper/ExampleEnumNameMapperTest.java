package ch.samzurcher.enumNameMapper;

import ch.samzurcher.enumNameMapper.ExampleEnumNameMapperTest.EnumA;
import ch.samzurcher.enumNameMapper.ExampleEnumNameMapperTest.EnumB;

public class ExampleEnumNameMapperTest extends AbstractEnumNameMapperTest<EnumA, EnumB> {

	@Override
	protected AbstractEnumNameMapper<EnumA, EnumB> getMapper() {
		return new ExampleEnumNameMapper();
	}

	protected class ExampleEnumNameMapper extends AbstractEnumNameMapper<EnumA, EnumB> {
	}

	protected enum EnumA {
		A, B, C;
	}

	protected enum EnumB {
		A, B, C;
	}
}
