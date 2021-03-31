package org.knoldus.model

import org.knoldus.model.Type.Type

import java.util.UUID

case class User(id:UUID,username:String, _type:Type)