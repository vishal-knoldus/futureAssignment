package org.knoldus.Database

import org.knoldus.model.User

import java.util.UUID
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

class UserRepo extends Dao[User]{
  private var userListBuffer = ListBuffer.empty[User]
  private var indexOfUsers:mutable.Map[UUID, User] = mutable.Map[UUID,User]()
  override def createUser(user:User):Future[Boolean] = Future{
    if(!userListBuffer.contains(user))
      Try{
        userListBuffer += user
        indexOfUsers += (user.id -> user)
      }match {
        case Failure(_)=>false
        case Success(_)=>true
      }
    else
      false
  }
  def updateUser(oldUser: User, newUser:User):Future[Boolean]=Future{
    if(userListBuffer.contains(oldUser)){
      val index = userListBuffer.indexOf(oldUser)
      userListBuffer.update(index,newUser)
      indexOfUsers -= oldUser.id
      indexOfUsers += newUser.id -> newUser
      true
    }
    else
      false
  }

  def deleteUser(user:User):Future[Boolean]=Future {
    Try{
      userListBuffer.remove(userListBuffer.indexOf(user))
      indexOfUsers -= user.id
    }match {
      case Success(_) => true
      case Failure(_) => false
    }
  }

  override def getUserById(id:UUID):Future[User] =Future {
    if (indexOfUsers.contains(id: UUID)) indexOfUsers(id)
    else null
  }
  override def getAllUser : Future[List[User]] =Future {
    userListBuffer.toList
  }
}