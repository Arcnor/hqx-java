
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import hqx.*;

class Hqx2xUpscalerExample {
    public static void main(String[] args) {

		// Load an image
		BufferedImage bi = null;
		try {
			bi = ImageIO.read(new File("sampleImage_original.png"));
		}
		catch (java.io.IOException e) {
			System.err.println("Error loading image file: " + e);
		}
		if (bi != null) {
			// Convert image to ARGB if on another format
			if (bi.getType() != BufferedImage.TYPE_INT_ARGB && bi.getType() != BufferedImage.TYPE_INT_ARGB_PRE) {
				final BufferedImage temp = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_ARGB);
				temp.getGraphics().drawImage(bi, 0, 0, null);
				bi = temp;
			}
			// Obtain pixel data for source image
			final int[] data = ((DataBufferInt) bi.getRaster().getDataBuffer()).getData();
		
			// Initialize lookup tables
			RgbYuv.hqxInit();
		
			// Create the destination image, twice as large for 2x algorithm
			final BufferedImage biDest2 = new BufferedImage(bi.getWidth() * 2, bi.getHeight() * 2, BufferedImage.TYPE_INT_ARGB);
			// Obtain pixel data for destination image
			final int[] dataDest2 = ((DataBufferInt) biDest2.getRaster().getDataBuffer()).getData();
			// Resize it
			Hqx_2x.hq2x_32_rb(data, dataDest2, bi.getWidth(), bi.getHeight());
			// Save our result
			try {
				ImageIO.write(biDest2, "PNG", new File("sampleImage_hqx2.png"));
			}
			catch(java.io.IOException e) {
				System.err.println("Error loading image file: " + e);
			}
		
			// More calls to hq[234]x_32_rb() methods
			// ....
		
			// Release the lookup table
			RgbYuv.hqxDeinit();
		}
	}
}
