package edu.psu.sweng.kahindu.transform;

import java.util.HashMap;
import java.util.Map;

import edu.psu.sweng.kahindu.image.KahinduImage;
import edu.psu.sweng.kahindu.transform.HistogramTransformer.Histogram;

/**
 * Implements an Exponential Non-Adaptive Histogram Transformation
 * @author John
 *
 */
public class ENAHTransformer extends HistogramTransformer implements ParameterizedTransformer {

	public static final String KEY = "Alpha";
	private static final double DEFAULT_ALPHA = 4.0;

	private double alpha;

	public ENAHTransformer() {
		this.alpha = DEFAULT_ALPHA;
	}

	@Override
	public void setParameter(String key, double value) {
		System.out.println(key + "  " + value);
		if (KEY.equals(key))
			this.alpha = value;
		else
			throw new IllegalArgumentException();
	}

	@Override
	public Map<String, Double> getDefaultParameters() {
		Map<String, Double> map = new HashMap<String, Double>();
		map.put(KEY, 4.0);
		return map;

	}

	@Override
	public short[] getLookupTable(KahinduImage input) {
		Histogram histogram = new Histogram(input);
		short lut[] = new short[256];
		double h[] = histogram.getAverageCMF();
		for (short i = 0; i < lut.length; i++) {
			lut[i] = (short) (255 * (-Math.log(1.0 - h[i]) / alpha));
			lut[i] = (short) Math.min(lut[i], 255);
			lut[i] = (short) Math.max(lut[i], 0);
		}
		return lut;
	}

}
