package spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SQLContext, SaveMode, SparkSession}

object cours3 {

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)


    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local[*]")
      .getOrCreate()


    val sc = spark.sparkContext

    val sqlContext = new SQLContext(sc)

    val personnes1load =sqlContext.read.json("/Users/aubinlauraire/Desktop/personnes1.json")
    personnes1load.show(false)

    // append pour dupliquer ou overwrite pour ecraser
    personnes1load.write
      .mode(SaveMode.Append)
      .parquet("/Users/aubinlauraire/Desktop/personnes1.parquet")

    // ne peut pas enregistrer en ocal sur orc du coup marche pas
     personnes1load.write
      .mode(SaveMode.Append)
      .orc("/Users/aubinlauraire/Desktop/personnes1.orc")

  }

}
//-----------------------------------------------------------