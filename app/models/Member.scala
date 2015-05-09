package models

case class Member(
	val id: Int,
	val email: String,
	val passHash: String,
	val status: Int,
	val joinedOn: Int,
	val permissions: Int
)
