package com.codiply.scalawithcats.chapter4

object DbReaderExercise {
  import cats.data.Reader

  case class Db(usernames: Map[Int, String], passwords: Map[String, String])

  type DbReader[T] = Reader[Db, T]

  def findUsername(userId: Int): DbReader[Option[String]] =
    Reader(db => db.usernames.get(userId))

  def checkPassword(username: String, password: String): DbReader[Boolean] =
    Reader(db => db.passwords.get(username).contains(password))

  def checkLogin(userId: Int, password: String): DbReader[Boolean] =
    for {
      username <- findUsername(userId)
      passwordCheck <- username.map {
        uname => checkPassword(uname, password)
      }.getOrElse(Reader[Db, Boolean](_ => false))
    } yield passwordCheck
}
