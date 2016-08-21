package com.hackx.spark;

import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hackx on 8/18/16.
 */
public class HelloSpark {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setMaster("local").setAppName("HelloSpark Application");
        JavaSparkContext sc = new JavaSparkContext(conf);


        List<Integer> lines = Arrays.asList(1, 2, 3, 4, 5);
        JavaRDD<Integer> rdd = sc.parallelize(lines, 1);
        JavaRDD<Integer> result = rdd.map(new Function<Integer, Integer>() {
            public Integer call(Integer integer) throws Exception {
                return integer * integer;
            }
        });
        System.out.println(StringUtils.join(result.collect(), ","));

//        List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
//        JavaRDD<Integer> rdd = sc.parallelize(data, 1);
//        JavaRDD<Integer> result = rdd.map(new Function<Integer, Integer>() {
//            public Integer call(Integer integer) throws Exception {
//                return integer * integer;
//            }
//        });
//        System.out.println(StringUtils.join(result.collect(), ","));
    }
}
