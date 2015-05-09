package models

sealed abstract class PositionType
case object Group extends PositionType
case object Primary extends PositionType
case object Role extends PositionType
