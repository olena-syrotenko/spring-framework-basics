package asd.syrotenko.autowiring.example.annotations;

import asd.syrotenko.autowiring.example.Scores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Scholar {

	@Autowired
	@Qualifier("scholarScores")
	private Scores scores;

	public Scores getScores() {
		return scores;
	}

	public void setScores(Scores scholarScores) {
		this.scores = scholarScores;
	}
}
