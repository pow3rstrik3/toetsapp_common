package nl.han.asd.toetsapp.common.model;

public enum ExamType {
	MOCKEXAM, EXAM;

	public boolean isMock () {
		return this == MOCKEXAM;
	}
}
