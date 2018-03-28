package spark

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.{SQLContext, SaveMode, SparkSession}

object cours2 {

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
    import sqlContext.implicits._

    //-----------------------------------------------------------
    val personnes1 = sc.parallelize(
      Seq(
        ("LAU", "Aubin", 28),
        ("MOU", "Aniss", 28),
        ("TIM", "Olga", 32)
      )
    ).toDF("nom", "prenom", "age")
    personnes1.cache
    personnes1.show(false)


    val villes = sc.parallelize(
      Seq(
        ("TIM", "ML", "78600"),
        ("MOU", "ML", "78600"),
        ("LAU", "SAR", "78500")
      )
    ).toDF("nom", "ville", "CP")

    villes.show(false)


    val personnes2 = sc.parallelize(
      Seq(
        ("CAV", "Adrien", 30),
        ("WAG", "Benoit", 28)
      )
    ).toDF("nom", "prenom", "age")

    personnes2.show(false)

    val myCount1 = personnes1.count
    println(myCount1)

    val test1 = personnes1.select("nom", "prenom")
    println("selecttableau")
    test1.show(false)

    val test2 = personnes1.drop("age")
    println("droptableau")
    test2.show(false)

    val test3 = personnes1.filter('age > 30)
    println("filtretableau")
    test3.show(false)

    val test4 = personnes1.union(personnes2)
    println("uniontableau")
    test4.show(false)

    // on met seq pour debuter une sequence de mot cle meme si qu'un seul
    val test5 = personnes1.join(villes, Seq("nom"))
    println("jointableau")
    test5.show(false)

    val personnes1Clean = personnes1
      .withColumnRenamed("nom", "NOM")
      .withColumnRenamed("prenom", "PRENOM")
      .withColumnRenamed("age", "AGE")
    println("CleanTableau")
    personnes1Clean.show(false)

    val upadteAgeUdf = udf(upAge _)

    val personnesAge = personnes1.withColumn("age2",upadteAgeUdf('age))
      .drop("age")
      .withColumnRenamed("age2","age")
    personnesAge.show(false)

    val updateNameUdf =udf(upName _)

    val personnesName =personnes1.withColumn("id",updateNameUdf('nom,'prenom))
      .drop("nom","prenom")

    personnesName.show(false)

    val downNameUdf =udf(downAge _)

    val personnesDown =personnes1.withColumn("Age3",downNameUdf('age))
      .drop("age")
    personnesDown.show(false)

    personnes1.write
      .mode(SaveMode.Append)
      .json("/Users/aubinlauraire/Desktop/personnes1.json")

  }
  //les def permettent de créer des algorythmes Upname est le nom de l'UDF et
  def upName(nom: String, prenom: String) = {
    val res = prenom + " " + nom
    println(res)
    res
  }

  def upAge(age: Int) = {
    val res = age + 1
    println(res)
    res
  }

  def downAge(age: Int) = {
    val res = age - 1
    println(res)
    res
  }
}

