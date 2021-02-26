package Utilities;

public enum TestCaseType
{
    Web(1),
    Desktop(2);

	private final int value;

	TestCaseType(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}

