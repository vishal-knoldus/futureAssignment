package org.knoldus.testServices

import org.knoldus.Database.UserRepo
import org.knoldus.model.{Type, User}
import org.knoldus.services.UserService
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.language.postfixOps

class UserServiceTest extends AnyFlatSpec {
  val mockedUserRepo:UserRepo = mock[UserRepo]
  val userService = new UserService(mockedUserRepo)
  val user1:User = User(java.util.UUID.randomUUID(),"Vishal",Type.Admin)
  val user:User = User(java.util.UUID.randomUUID(),"Ayush",Type.Admin)
  val updateUser:User = User(java.util.UUID.randomUUID(),"abhay",Type.Customer)

  "createUser" should "Create a new user in Database" in{
    when(mockedUserRepo.createUser(user)) thenReturn Future(true)
   val result = Await.result(userService.createUser(user),10 seconds)
    assert(result)
  }
  it should "delete user1 from the database" in{
    when(mockedUserRepo.deleteUser(user1)) thenReturn Future(true)
    val result = Await.result(userService.deleteUser(user1),10 seconds)
    assert(result)
  }
  it should " Update user to updateUser" in{
    when(mockedUserRepo.updateUser(user,updateUser)) thenReturn Future(true)
    val result = Await.result(userService.updateUser(user,updateUser),10 seconds)
    assert(result)
  }
  it should "list of all user" in{
    when(mockedUserRepo.getAllUser) thenReturn Future(List(user))
    val result:List[User] = Await.result(userService.getAllUser,10 seconds)
    assert(result.nonEmpty)
  }
}
