# HqxCli-Java

## What is this fork for?

I did not touch a single line of the algorithm code but I did some modification:

-	The original project is a lib, this is a command line application that use that lib 
-	The packages name is now coherent 
-	This is a maven project, not an Eclipse project 

## Original Introduction
__hqx__ ("hq" stands for "high quality" and "x" stands for magnification) is one of the pixel art scaling algorithms developed by Maxim Stepin, used in emulators such as Nestopia, bsnes, ZSNES, Snes9x, FCE Ultra and many more. There are 3 hqx filters: hq2x, hq3x, and hq4x, which magnify by factor of 2, 3, and 4 respectively.

## Port Introduction
__hqx-java__ is a Java port of the excellent [hqxSharp](http://code.google.com/p/hqx-sharp) C# port, which itself is a port of the original [hqx](http://code.google.com/p/hqx) C project

Like the hqxSharp project, the focus of this code is asset creation and usage in tools, so no optimizations were done, just an almost-direct copy of the code.

## Usage

		Darshan@Darshan-HP:/Codice/Java/hqx-java/target$ java -jar .\hqx-java.jar
		hqx image converter
		Usage -> hqx.jar [options] inputFile
				 the input file can olso be specified with an option
				 If not overridden output file name will be inputfile.hq2x.png of hq2x
				 hq3x.png for hq3x and so on

		Option             Description
		------             -----------
		-?, -h, --help     show help
		--all              Upscale the input file with hq2x,hq3x,hq4x
		--hq2x             Upscale the input file with hq2x
		--hq3x             Upscale the input file with hq3x
		--hq4x             Upscale the input file with hq4x
		--input            Specify input file
		--output           Override the default naming convention for output file


## Examples
Original links are dead ([hq2x](http://www.hiend3d.com/hq2x.html) and [hq3x](http://www.hiend3d.com/hq3x.html))

-	Wikipedia entry for [hq2x](https://en.wikipedia.org/wiki/Hqx)
-	Cached version of the original links 
	-	[www.hiend3d.com/hq2x.html](https://web.archive.org/web/20131205091805/http://www.hiend3d.com/hq2x.html)
	-	[www.hiend3d.com/hq3x.html](https://web.archive.org/web/20070125113756/http://www.hiend3d.com/hq3x.html)
	-	[www.hiend3d.com/hq4x.html](https://web.archive.org/web/20131216092117/http://www.hiend3d.com/hq4x.html)