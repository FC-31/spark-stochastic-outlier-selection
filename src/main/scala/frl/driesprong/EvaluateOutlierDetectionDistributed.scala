package frl.driesprong

import org.apache.spark.mllib.outlier.StochasticOutlierDetection
import org.apache.spark.{SparkConf, SparkContext}

/**
 * Created by fokko on 10-9-15.
 */
object EvaluateOutlierDetectionDistributed {

  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("Stochastic Outlier Selection")

    val sc = new SparkContext(conf)

    val toyDataset = Array(
      (0L, Array(1.00, 1.00)),
      (1L, Array(3.00, 1.25)),
      (2L, Array(3.00, 3.00)),
      (3L, Array(1.00, 3.00)),
      (4L, Array(2.25, 2.25)),
      (5L, Array(8.00, 2.00))
    )

    StochasticOutlierDetection.performOutlierDetection( sc.parallelize(toyDataset) ).foreach( x =>
      System.out.println(x._1 + " : " + x._2)
    )

    sc.stop()
  }
}
