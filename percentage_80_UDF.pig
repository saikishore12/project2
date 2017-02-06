REGISTER '/usr/lib/pig/piggybank.jar';
REGISTER '/home/acadgild/pig/percentageUDF.jar';

DEFINE percentageUDF 'percentageUDF.percentageUDF';
DEFINE XPath org.apache.pig.piggybank.evaluation.xml.XPath();

A =  LOAD 'flume/StatewiseDistrictwisePhysicalProgress.xml' using org.apache.pig.piggybank.storage.XMLLoader('row') as (x:chararray);

B = FOREACH A GENERATE  XPath(x, 'row/Project_Objectives_IHHL_BPL') AS Objectives_IHHL_BPL, XPath(x, 'row/Project_Performance_IHHL_BPL') AS Performance_IHHL_BPL;
DUMP B;

C = FOREACH B GENERATE percentageUDF(Objectives_IHHL_BPL,Performance_IHHL_BPL);
dump C;
