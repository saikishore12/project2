package com.acadgild;
 

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

 
public class Bpl_performance {
 
   
    public static void main(String[] args) {
        try {
        	@SuppressWarnings("deprecation")
			Job job=new Job(); 
        	Configuration conf = job.getConfiguration();
        	//to pass the start and end key
        	conf.set("START_TAG_KEY", "﻿<row>");
            conf.set("END_TAG_KEY", "﻿</row>");    
            job.setJarByClass(Bpl_performance.class);
            job.setMapperClass(BplMapper.class);
            job.setReducerClass(BplReducer.class);    
            //to read xmlformat input class
           	job.setInputFormatClass(XmlInputFormat.class);
            job.setOutputValueClass(TextOutputFormat.class);
            job.setMapOutputKeyClass(Text.class);
    		job.setMapOutputValueClass(TextPair.class);
    		job.setOutputKeyClass(Text.class);
    		job.setOutputValueClass(NullWritable.class);
    		FileInputFormat.addInputPath(job, new Path(args[0]));
            FileOutputFormat.setOutputPath(job, new Path(args[1]));
 
            job.waitForCompletion(true);
 
        } catch (Exception e) {
            
            System.out.println(e.getMessage().toString());
        }
        // job.setReducerClass(ClickReducer.class);
 
    }
 
}