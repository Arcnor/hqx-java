# Hqx-Java 

## What is this fork for?

I did not touch a single line of the original code but I did some modification:

-	The original project is a lib, this is a command line application that use that lib 
-	The packages name is now coherent 
-	This is a maven project, not an Eclipse project 

## Original Introduction
__hqx__ ("hq" stands for "high quality" and "x" stands for magnification) is one of the pixel art scaling algorithms developed by Maxim Stepin, used in emulators such as Nestopia, bsnes, ZSNES, Snes9x, FCE Ultra and many more. There are 3 hqx filters: hq2x, hq3x, and hq4x, which magnify by factor of 2, 3, and 4 respectively.

## Port Introduction
__hqx-java__ is a Java port of the excellent [hqxSharp](http://code.google.com/p/hqx-sharp) C# port, which itself is a port of the original [hqx](http://code.google.com/p/hqx) C project

Like the hqxSharp project, the focus of this code is asset creation and usage in tools, so no optimizations were done, just an almost-direct copy of the code.

## Usage
Look in the wiki for usage information

## Examples
For examples, go to Maxim Stepin hqx pages ([hq2x](http://www.hiend3d.com/hq2x.html) and [hq3x](http://www.hiend3d.com/hq3x.html))