package edu.psu.sweng.kahindu.transform;

import edu.psu.sweng.kahindu.CharacterizationTest;
import edu.psu.sweng.kahindu.image.KahinduImage;
import gui.TopFrame;

public class LowPassAverageTest extends CharacterizationTest {

	@Override
	public TopFrame constructLegacyImage(TopFrame topFrame) {
		topFrame.average();
		return topFrame;
	}

	@Override
	public KahinduImage constructRefactoredImage(KahinduImage img) {
		LowPassAverage t = new LowPassAverage();
		return t.transform(img);
	}

}
