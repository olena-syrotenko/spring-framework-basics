package asd.syrotenko.autowiring.example;

public class Student {
	private Scores studentScores;

	public Scores getScores() {
		return studentScores;
	}

	public void setStudentScores(Scores scores) {
		this.studentScores = scores;
	}
}
