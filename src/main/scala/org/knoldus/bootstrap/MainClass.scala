package org.knoldus.bootstrap
import org.knoldus.Database.UserRepo
import org.knoldus.model.{Type, User}
import org.knoldus.services.UserService

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object MainClass{
  def main(args: Array[String]): Unit = {
    val repoInstance = new UserRepo
    val userInstance = new UserService(repoInstance)
    val Admin1 = User(java.util.UUID.randomUUID(),"Vishal", Type.Admin)
    val Customer1 = User(java.util.UUID.randomUUID(),"Abhay",Type.Customer)
    val Admin2 = User(java.util.UUID.randomUUID(),"Nehal", Type.Admin)
    val Customer2 = User(java.util.UUID.randomUUID(),"Abhay",Type.Customer)
    val updatedCustomer2 = User(java.util.UUID.randomUUID(),"Abhay",Type.Admin)
    //Creating User
    userInstance.createUser(Admin1)
    userInstance.createUser(Customer1)
    userInstance.createUser(Admin2)
    userInstance.createUser(Customer2)
    println("==================Initial list of created user data=============")
    println("\n" + userInstance.getAllUser + "\n")

    /*for{
      t<- userInstance.createUser(Admin1)
      y<- userInstance.createUser(Customer1)
      z<- userInstance.createUser(Admin2)
      a<- userInstance.createUser(Customer2)
      b<- userInstance.getAllUser
    }yield{
      println(b+"All user created")
    }*/

    /*userInstance.getAllUser.onComplete{
      case Success(value)=>println(value+"Get all user on complete")
      case Failure(exception)=>println(exception)
    }
*/

    userInstance.deleteUser(Admin1)
    println("===============List of user data after deleting Admin1===========")
    println("\n" + userInstance.getAllUser + "\n")

    userInstance.updateUser(Customer2,updatedCustomer2)
    println("===========List of user data after updating of Customer2=============")
    println("\n" + userInstance.getAllUser + "\n")

   // Thread.sleep(10000)

  }
}