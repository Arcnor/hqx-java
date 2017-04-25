package com.thedarshan.hqx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import static java.util.Arrays.asList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import joptsimple.BuiltinHelpFormatter;
import joptsimple.HelpFormatter;
import joptsimple.OptionDescriptor;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

/**
 *
 * @author Darshan
 */
public class Main {
    
    public static final short hq2x=2;
    public static final short hq3x=3;
    public static final short hq4x=4;

    private static OptionParser initParser() {
        OptionParser parser = new OptionParser();
        parser.accepts("hq2x", "Upscale the input file with hq2x");
        parser.accepts("hq3x", "Upscale the input file with hq3x");
        parser.accepts("hq4x", "Upscale the input file with hq4x");
        parser.accepts("all", "Upscale the input file with hq2x,hq3x,hq4x");
        parser.accepts("output", "Override the default naming convention for output file").withRequiredArg();
        parser.accepts("input", "Specify input file").withRequiredArg();
        parser.acceptsAll(asList("h", "?", "help"), "show help").forHelp();
        parser.formatHelpWith(new CustomHelpFormatter());

        try {
            parser.printHelpOn(System.out);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        return parser;
    }

    public static void main(String[] args) {
        System.out.println("hqx image converter");
        System.out.println("Working Directory -> " + System.getProperty("user.dir"));

        OptionParser parser = initParser();
        OptionSet options = parser.parse(args);

        String inputFile="";
        String outputFile="";
        int nformat = 0;

        if(args.length==0){
            System.err.println("Must specify at least the input file");
            return;
        }
            
        
        if (options.has("hq2x")) nformat++;
        if (options.has("hq3x")) nformat++;
        if (options.has("hq4x")) nformat++;
        if (options.has("all" )) nformat += 2;

        if (nformat == 0) {
            System.err.println("No scaling method specified, hq2x will be used.");
        }

        if (options.hasArgument("output")) {
            inputFile = (String) options.valueOf("output");
        }

        if (options.hasArgument("input")) {
            inputFile = (String) options.valueOf("input");
        }else{            
            inputFile=args[args.length-1];
        
        }

        if (options.hasArgument("output") && nformat > 0) {
            System.err.println("Can't specify output for multiple conversion, standard pattern will be used.");
        }
        
        RgbYuv.hqxInit();
        if (options.has("hq2x")||options.has("all" )) convert(inputFile, outputFile, hq2x);
        if (options.has("hq3x")||options.has("all" )) convert(inputFile, outputFile, hq3x);
        if (options.has("hq4x")||options.has("all" )) convert(inputFile, outputFile, hq4x);
        RgbYuv.hqxDeinit();

    }

    private static boolean convert(String inputFile, String outputFile, short algo) {
        BufferedImage bi;
        
        if(algo>hq4x)return false;
        
        if(outputFile==null || outputFile.length()<1)outputFile=MessageFormat.format("{0}_hq{1}x.png",inputFile, algo);
                
        
        
        try {
            bi = ImageIO.read(new FileInputStream(inputFile));
        } catch (IOException ex) {
            System.err.println("Can't load " + inputFile);
            return false;
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

            // Create the destination image, twice as large for 2x algorithm
            final BufferedImage destinationBuffer = new BufferedImage(bi.getWidth() * algo, bi.getHeight() * algo, BufferedImage.TYPE_INT_ARGB);
            // Obtain pixel data for destination image
            final int[] dataDest = ((DataBufferInt) destinationBuffer.getRaster().getDataBuffer()).getData();            
            // Resize it
            switch(algo){
                case hq2x:
                    Hqx_2x.hq2x_32_rb(data, dataDest, bi.getWidth(), bi.getHeight());
                    break;
                case hq3x:
                    Hqx_3x.hq3x_32_rb(data, dataDest, bi.getWidth(), bi.getHeight());
                    break;
                case hq4x:
                    Hqx_4x.hq4x_32_rb(data, dataDest, bi.getWidth(), bi.getHeight());
                    break;
                default:
                    return false;                    
            }
            try {
                // Save our result
                ImageIO.write(destinationBuffer, "PNG", new File(outputFile));
            } catch (IOException ex) {
                System.err.println("Can't write image to " + outputFile);
            }

            return true;
        }
        System.err.println("Can't convert " + inputFile);
        return false;

    }

    private static class CustomHelpFormatter implements HelpFormatter {

        private final joptsimple.BuiltinHelpFormatter baseFormatter;

        public CustomHelpFormatter() {
            baseFormatter = new BuiltinHelpFormatter(70, 5);
        }

        @Override
        public String format(Map<String, ? extends OptionDescriptor> options) {
            StringBuilder sb = new StringBuilder();
            sb.append("Usage -> hqx.jar [options] inputFile\n");
            sb.append("\t the input file can olso be specified with an option \n");
            sb.append("\t If not overriden output file name will be inputfile.hq2x.png of hq2x \n");
            sb.append("\t hq3x.png for hq3x and so on \n \n");
            sb.append(baseFormatter.format(options));
            return sb.toString();
        }
    }
}
