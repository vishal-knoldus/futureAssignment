package org.knoldus.testDatabase

import org.knoldus.Database.UserRepo
import org.knoldus.model.{Type, User}
import org.scalatest.flatspec.AsyncFlatSpec

import java.util.UUID
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

class UserRepoTest extends AsyncFlatSpec{
  val userRepo = new UserRepo
  val user:User = User(java.util.UUID.randomUUID(),"vishal",Type.Admin)
  val updateUser:User = User(java.util.UUID.randomUUID(),"Abhay",Type.Customer)
  val user1:User = User(java.util.UUID.randomUUID(),"Neha",Type.Customer)
  it should "Create a new User" in {
    val result:Boolean = Await.result(userRepo.createUser(user),10 seconds)
    assert(result)
  }

  it should "Update the user" in {
    val result:Boolean = Await.result(userRepo.updateUser(user,updateUser),10 seconds)
    assert(result)
  }

  it should "Delete user" in {
    val result:Boolean = Await.result(userRepo.deleteUser(updateUser),10 seconds)
    assert(result)
  }

  it should "return the listBuffer that contains all the user" in {
    val result:List[User] = Await.result(userRepo.getAllUser,10 seconds)
    assert(result.isEmpty)
  }

  it should "return user by ID" in {
    val userId:UUID = user1.id
    val result = Await.result(userRepo.getUserById(userId),10 seconds)
    assert(result != user1)
  }
}
