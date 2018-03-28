package formation.scala.Action

case class FicheClient(nom : String, prenom : String, age : Int )

object Cours1Scala {
//on creer des def pour mettre des valeurs

  def main(args: Array[String])={

    val testBool1 = true

    val testBool2 = false

    val testString = "ordinateur"

    val testInt = 4

    val testFloat = 44.4f

    val testDouble = 44.4

    var ttt=testInt+1

        ttt=ttt+1

    val myArray = Array(1,2,3,4,5)

    myArray.foreach(x=>println(x))

    myArray.foreach(x=>println(x+1))

    val myArray2=myArray.map(x=>x+2)
        myArray2.foreach(println)

    //creer 3 fiches clients avec le template case class

    val fc1 = FicheClient("Lauraire","Aubin",27)
    val fc2 = FicheClient("Timofeeva","Olga",32)
    val fc3 = FicheClient("Moussa","Aniss",28)
    val fc4 = FicheClient(age=28,nom="Lauraire",prenom="Aubin")

    //on organise comme on veut le print ln la dans ce cas on affiche que l age et espace apres pourmettre le nom on utilise la commande X.
    val myArray3 = Array(fc1,fc2,fc3)
    myArray3.foreach(x=>println(x.age+" "+x.nom))


    println(testBool1)
    println(testBool2)
    println(testString)
    println(testInt)
    println(testFloat)
    println(testDouble)
    println(ttt)
















  }

}
