package spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SQLContext, SparkSession}

object exemple1  {
  def main (args: Array[String]) : Unit  = {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)


    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local[*]")
      .getOrCreate()


    val sc = spark.sparkContext

    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    //-----------------------------------------------------------
    val test1 = sc.parallelize(
      Seq(
        ("T", "O", 31),
        ("m", "A", 28),
        ("W", "B", 27),
        ("M", "C", 34)
      )
    ).toDF("nom", "prenom", "age")

    test1.show(false)


    val test2 = sc.parallelize(
      Seq(
        ("T", "O", "ml"),
        ("m", "A", "ml"),
        ("W", "B", "s"),
        ("M", "C", "p")
      )
    ).toDF("nom", "prenom", "ville")

    test2.show(false)


    val test3 = sc.parallelize(
      Seq(
        (23, "rue bel", "f"),
        (45, "impasse boyer", "m"),
        (33, "allee cool", "f"),
        (18, "rue lilas", "m")
      )
    ).toDF("age", "adresse", "sexe")

    test3.show(false)

    val myCount1 = test1.count
    println("count test1 "+myCount1)

    val myCount2= test2.count
    println("count test2 "+myCount2)

    val myCount3= test3.count
    println("count test3 "+myCount3)

    val mySelect3=test3.select("adresse","sexe")
    println("mySelect3")
    mySelect3.show(false)

    val myFilter3 = test3.filter('age>20)
    println("myFilter3")
    myFilter3.show(false)

    val myFilter4 = test3.filter('adresse!=="rue bel")
    println("myFilter4")
    myFilter4.show(false)

    val myDrop1 =test3.drop("adresse")
    myDrop1.show(false)

    val myJoin1 =test1.join(test2,Seq("nom","prenom"))
    myJoin1.show(false)

    val myUnion1=test3.union(myFilter3)
    myUnion1.show(false)

    println(myUnion1.count)
    println(myJoin1.count)
    println(myFilter3.count)
    





  }

}
