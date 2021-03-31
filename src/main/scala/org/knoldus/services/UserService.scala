package org.knoldus.services

import org.knoldus.Database.UserRepo
import org.knoldus.model.User
import java.util.UUID
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import scala.util.{Failure, Success}

class UserService(userRepo: UserRepo){
  def createUser(user:User):Future[Boolean] = {
    userRepo.createUser(user)

  }



  def getUserById(id:UUID):Future[User]={
    userRepo.getUserById(id)
  }
  def updateUser(oldUser:User,updatedUser:User):Future[Boolean]={
    userRepo.updateUser(oldUser,updatedUser)
  }
  def deleteUser(user: User):Future[Boolean]={
    userRepo.deleteUser(user)
  }
  def getAllUser:Future[List[User]] = userRepo.getAllUser
}