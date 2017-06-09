# HqxCli-Java

## What is this fork for?

I did not touch a single line of the algorithm code but I did some modification:

-	The original project is a lib, this is lib and a command line application that uses it
-	The packages names are now coherent 
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
	
## A decription from the original implementation

Verbatim from https://code.google.com/archive/p/hqx/source/default/source

> The first step is an analysis of the 3x3 area of the source pixel. At first, we calculate the color difference between the central pixel and its 8 nearest neighbors. Then that difference is compared to a predefined threshold, and these pixels are sorted into two categories: "close" and "distant" colored. There are 8 neighbors, so we are getting 256 possible combinations.
For the next step, which is filtering, a lookup table with 256 entries is used, one entry per each combination of close/distant colored neighbors. Each entry describes how to mix the colors of the source pixels from 3x3 area to get interpolated pixels of the filtered image.
The present implementation is using YUV color space to calculate color differences, with more tolerance on Y (brightness) component, then on color components U and V. That color space conversion is quite easy to implement if the format of the source image is 16 bit per pixel, using a simple lookup table. It is also possible to calculate the color differences and compare them to a threshold very fast, using MMX instructions.
Creating a lookup table was the most difficult part - for each combination the most probable vector representation of the area has to be determined, with the idea of edges between the different colored areas of the image to be preserved, with the edge direction to be as close to a correct one as possible. That vector representation is then rasterised with higher (3x) resolution using anti-aliasing, and the result is stored in the lookup table.
The filter was not designed for photographs, but for images with clear sharp edges, like line graphics or cartoon sprites. It was also designed to be fast enough to process 256x256 images in real-time.


------------

## License
This software is released under the GNU Lesser General Public License

### GNU Lesser General Public License

_Version 3, 29 June 2007_  
_Copyright © 2007 Free Software Foundation, Inc. &lt;<http://fsf.org/>&gt;_

Everyone is permitted to copy and distribute verbatim copies of this license document, but changing it is not allowed.


This version of the GNU Lesser General Public License incorporates the terms and conditions of version 3 of the GNU General Public License, supplemented by the additional permissions listed below.

#### 0. Additional Definitions

As used herein, “this License” refers to version 3 of the GNU Lesser General Public License, and the “GNU GPL” refers to version 3 of the GNU General Public License.

“The Library” refers to a covered work governed by this License, other than an Application or a Combined Work as defined below.

An “Application” is any work that makes use of an interface provided by the Library, but which is not otherwise based on the Library. Defining a subclass of a class defined by the Library is deemed a mode of using an interface provided by the Library.

A “Combined Work” is a work produced by combining or linking an Application with the Library.  The particular version of the Library with which the Combined Work was made is also called the “Linked Version”.

The “Minimal Corresponding Source” for a Combined Work means the Corresponding Source for the Combined Work, excluding any source code for portions of the Combined Work that, considered in isolation, are based on the Application, and not on the Linked Version.

The “Corresponding Application Code” for a Combined Work means the object code and/or source code for the Application, including any data and utility programs needed for reproducing the Combined Work from the Application, but excluding the System Libraries of the Combined Work.

#### 1. Exception to Section 3 of the GNU GPL

You may convey a covered work under sections 3 and 4 of this License without being bound by section 3 of the GNU GPL.

#### 2. Conveying Modified Versions

If you modify a copy of the Library, and, in your modifications, a facility refers to a function or data to be supplied by an Application that uses the facility (other than as an argument passed when the facility is invoked), then you may convey a copy of the modified version:

* **a)** under this License, provided that you make a good faith effort to ensure that, in the event an Application does not supply the function or data, the facility still operates, and performs whatever part of its purpose remains meaningful, or

* **b)** under the GNU GPL, with none of the additional permissions of this License applicable to that copy.

#### 3. Object Code Incorporating Material from Library Header Files

The object code form of an Application may incorporate material from a header file that is part of the Library.  You may convey such object code under terms of your choice, provided that, if the incorporated material is not limited to numerical parameters, data structure layouts and accessors, or small macros, inline functions and templates
(ten or fewer lines in length), you do both of the following:

* **a)** Give prominent notice with each copy of the object code that the Library is used in it and that the Library and its use are covered by this License.
* **b)** Accompany the object code with a copy of the GNU GPL and this license document.

#### 4. Combined Works

You may convey a Combined Work under terms of your choice that, taken together, effectively do not restrict modification of the portions of the Library contained in the Combined Work and reverse engineering for debugging such modifications, if you also do each of the following:

* **a)** Give prominent notice with each copy of the Combined Work that the Library is used in it and that the Library and its use are covered by this License.

* **b)** Accompany the Combined Work with a copy of the GNU GPL and this license document.

* **c)** For a Combined Work that displays copyright notices during execution, include the copyright notice for the Library among these notices, as well as a reference directing the user to the copies of the GNU GPL and this license document.

* **d)** Do one of the following:
    - **0)** Convey the Minimal Corresponding Source under the terms of this License, and the Corresponding Application Code in a form suitable for, and under terms that permit, the user to recombine or relink the Application with a modified version of the Linked Version to produce a modified Combined Work, in the manner specified by section 6 of the GNU GPL for conveying Corresponding Source.
    - **1)** Use a suitable shared library mechanism for linking with the Library.  A suitable mechanism is one that **(a)** uses at run time a copy of the Library already present on the user's computer system, and **(b)** will operate properly with a modified version of the Library that is interface-compatible with the Linked Version.

* **e)** Provide Installation Information, but only if you would otherwise be required to provide such information under section 6 of the GNU GPL, and only to the extent that such information is necessary to install and execute a modified version of the Combined Work produced by recombining or relinking the Application with a modified version of the Linked Version. (If you use option **4d0**, the Installation Information must accompany the Minimal Corresponding Source and Corresponding Application Code. If you use option **4d1**, you must provide the Installation Information in the manner specified by section 6 of the GNU GPL for conveying Corresponding Source.)

### 5. Combined Libraries

You may place library facilities that are a work based on the Library side by side in a single library together with other library facilities that are not Applications and are not covered by this License, and convey such a combined library under terms of your
choice, if you do both of the following:

* **a)** Accompany the combined library with a copy of the same work based on the Library, uncombined with any other library facilities, conveyed under the terms of this License.
* **b)** Give prominent notice with the combined library that part of it is a work based on the Library, and explaining where to find the accompanying uncombined form of the same work.

### 6. Revised Versions of the GNU Lesser General Public License

The Free Software Foundation may publish revised and/or new versions of the GNU Lesser General Public License from time to time. Such new versions will be similar in spirit to the present version, but may differ in detail to address new problems or concerns.

Each version is given a distinguishing version number. If the Library as you received it specifies that a certain numbered version of the GNU Lesser General Public License “or any later version” applies to it, you have the option of following the terms and conditions either of that published version or of any later version published by the Free Software Foundation. If the Library as you received it does not specify a version number of the GNU Lesser General Public License, you may choose any version of the GNU Lesser General Public License ever published by the Free Software Foundation.

If the Library as you received it specifies that a proxy can decide whether future versions of the GNU Lesser General Public License shall apply, that proxy's public statement of acceptance of any version is permanent authorization for you to choose that version for the Library.