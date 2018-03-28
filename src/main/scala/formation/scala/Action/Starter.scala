package formation.scala.Action

object Starter {

  def main(args: Array[String]) = {

    val testBool2=MesValeurs.testBool1

    val testBool3=MesValeurs.testBool2

    val testString1=MesValeurs.testString

    val testInt1=MesValeurs.testInt

    val testFloat1=MesValeurs.testFloat

    val testDouble1=MesValeurs.testDouble

    val Megane=new Voiture("Rouge",5,4.44,"Renault",32000.79)

    val Kadjar=new Voiture("Noir",5,4.60,"Renault",34500.67)



    //on fait un lien entre les valeurs de l'objet "mes valeurs" sur cet objet

    println(testBool2)
    println(testBool3)
    println(testString1)
    println(testInt1)
    println(testFloat1)
    println(testDouble1)
    println(Megane.getColor)
    println(Megane.getPrixNew)




  }

}
