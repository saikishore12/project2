package com.acadgild;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BplReducer extends Reducer<Text, TextPair, Text, NullWritable>{

	public void reduce(Text key, Iterable<TextPair> values, Context context)
			throws IOException, InterruptedException {
		
		int Bpl_objective = 0;
		int Bpl_performance = 0;
		int objective_percentage=0;
		for (TextPair Bpls : values) {
//reading bpl_objectives and performance from Bpl_data textpair
			Bpl_objective = Integer.parseInt(Bpls.getFirst().toString());
			Bpl_performance = Integer.parseInt(Bpls.getSecond().toString());
			//calculating the percentage
			objective_percentage=(Bpl_objective-Bpl_performance)/100;
		}
		//validating the percentage to 100%
		if(objective_percentage==100)
			//Wrinting the list of states to the output file.
		context.write(key,null);
	}
}
