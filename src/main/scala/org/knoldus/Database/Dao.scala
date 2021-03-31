package org.knoldus.Database

import org.knoldus.model.User

import java.util.UUID
import scala.concurrent.Future


trait Dao[T]{
  def createUser(entity:T):Future[Boolean]

  def getUserById(id:UUID):Future[T]

  def updateUser(oldUser:User,updatedUser:User):Future[Boolean]

  def deleteUser(user:User):Future[Boolean]

  def getAllUser:Future[List[T]]
}