REGISTER '/usr/pig/lib/piggybank.jar';
REGISTER '/home/acadgild/workspace/percentageUDF.jar';

DEFINE percentageUDF percentageUDF.percentageUDF;

A =  LOAD 'flume/StatewiseDistrictwisePhysicalProgress.xml' using org.apache.pig.piggybank.storage.XMLLoader('row') as (x:chararray);

B = FOREACH A GENERATE generate FLATTEN(REGEX_EXTRACT_ALL(row,' '));
DUMP B;
C= FOREACH B generate $1 as district, (int)$2 as Objectives_IHHL_BPL, (int)$10 as Performance_IHHL_BPL;
D = FILTER C by percentageUDF(Objectives_IHHL_BPL,Performance_IHHL_BPL);
dump D;

