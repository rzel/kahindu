package edu.psu.sweng.kahindu.matrix;

/**
 * This simple ADT is used to to support floating point matrix operations.
 * @author John
 *
 */
public class Matrix {
	
	private final int width;
	private final int height;
	private final float data [];

	
	public Matrix(int width, int height) {
		this(width, height, new float[width*height]);
	}

	public Matrix(int width, int height, float[] data) {
		assert(data.length == width * height);
		this.width = width;
		this.height = height;
		this.data = data;
	}

	public Matrix(int width, int height, float value) {
	    this(width,height);
	    fill(value);
    }

    public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public float getValue(int x, int y) {
		return data[x*width + y];
	}
	
	public void scale(float scale) {
		for (int i=0; i < data.length; i++) {
			data[i] = data[i] * scale;
		}
	}

	public void fill(float value) {
		for (int i=0; i < data.length; i++) {
			data[i] = value;
		}
		
	}

	public void setValueAt(int x, int y, float center) {
		 data[x*width + y] = center;
	}

	public float sum() {
		float sum = 0;
		for (float x : data)
			sum +=x;
		return sum;
	}

	public Matrix normalize() {
		float sum = this.sum();
		this.scale((float) (1 / sum));
		return this;
	}

}
