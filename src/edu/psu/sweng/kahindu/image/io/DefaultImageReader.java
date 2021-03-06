package edu.psu.sweng.kahindu.image.io;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.psu.sweng.kahindu.image.AWTImageAdapter;
import edu.psu.sweng.kahindu.image.KahinduImage;

public class DefaultImageReader implements ImageReader
{
	public DefaultImageReader()
	{
	}
	
	@Override
	public KahinduImage read(File file) throws IOException
	{
		BufferedImage image = ImageIO.read(file);
		return new AWTImageAdapter(image);
    }

}
