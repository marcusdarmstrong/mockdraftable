package tables

import java.sql.{Date, Timestamp}
import java.time.{LocalDate, LocalDateTime, ZoneOffset}
import scala.slick.driver.PostgresDriver.simple._

import models.Measurable
import models.Measurables

object TimeConverters {
  implicit val dateTimeMapper = MappedColumnType.base[LocalDateTime,Timestamp](
    d => Timestamp.from(d.toInstant(ZoneOffset.ofHours(0))),
    d => d.toLocalDateTime
  )

  implicit val dateMapper = MappedColumnType.base[LocalDate,Date](
    d => Date.valueOf(d),
    d => d.toLocalDate
  )


  implicit val measurableMapper = MappedColumnType.base[Measurable, Int](
    { 
      m => m match {
        case Measurables.height => 1
        case Measurables.weight => 2
        case Measurables.wingspan => 3
        case Measurables.armLength => 4
        case Measurables.handSize => 5
        case Measurables.time10 => 6
        case Measurables.time20 => 7
        case Measurables.time40 => 8
        case Measurables.benchPress => 9
        case Measurables.verticalJump => 10
        case Measurables.broadJump => 11
        case Measurables.cone3 => 12
        case Measurables.shuttle20 => 13
        case Measurables.shuttle60 => 14
        case _ => 15
      } 
    },
    { 
      i => i match {
        case 1 => Measurables.height
        case 2 => Measurables.weight
        case 3 => Measurables.wingspan
        case 4 => Measurables.armLength
        case 5 => Measurables.handSize
        case 6 => Measurables.time10
        case 7 => Measurables.time20
        case 8 => Measurables.time40
        case 9 => Measurables.benchPress
        case 10 => Measurables.verticalJump
        case 11 => Measurables.broadJump
        case 12 => Measurables.cone3
        case 13 => Measurables.shuttle20
        case 14 => Measurables.shuttle60
        case _ => Measurables.wonderlic
      } 
    }
  )
}
