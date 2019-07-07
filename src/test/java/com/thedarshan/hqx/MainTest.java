/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thedarshan.hqx;

import java.io.File;
import java.nio.file.Path;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
import java.nio.file.Paths;

/**
 *
 * @author Vincenzo
 */
public class MainTest {
    
    public static final String INPUT_IMAGES_FOLDER ="C:\\tmp\\";
    public static final String OUTPUT_IMAGES_FOLDER =INPUT_IMAGES_FOLDER;
    public static final String INDEXED_COLOR_IMAGE = "Invaders.png";
    public static final String TRUE_COLOR_IMAGE = "lena_std.png";
    
    
    public MainTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    private void checkOutput(String base, int to, int from){
        File f;
        String o;
        for (int i=from;i<=to;i++) {
            o=String.format("%s_hq%dx.png", base,i);
            f=new File(o);
            System.out.print("->    Checking " + o);
            if(f.exists())
                System.out.println("    OK");
            else
                fail("Unable to open " + o);
        }                 
    }
    
    public void testOne(String option, String inputFilePath) {
        File f= new File(inputFilePath);
        if(!f.exists())
            fail("Unable to open " + inputFilePath);
        String[] args = {"--all" , inputFilePath};        
        Main.main(args);
        checkOutput(inputFilePath, 2, 4);    
    }
    
    /**
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() {
        Path inputFile;
        System.out.println("Mocking some commandline calls");
        //
        System.out.println("Converting true color file");
        inputFile = Paths.get(INPUT_IMAGES_FOLDER, TRUE_COLOR_IMAGE);
        testOne("--all",inputFile.toString());
        //
        System.out.println("Converting indexed file");
        inputFile = Paths.get(INPUT_IMAGES_FOLDER, INDEXED_COLOR_IMAGE);
        testOne("--all",inputFile.toString());
    }
    
}
