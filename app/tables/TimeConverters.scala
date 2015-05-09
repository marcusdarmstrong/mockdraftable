package tables

import java.sql.{Date, Timestamp}
import java.time.{LocalDate, LocalDateTime, ZoneOffset}
import scala.slick.driver.PostgresDriver.simple._
 
object TimeConverters {
  implicit val dateTimeMapper = MappedColumnType.base[LocalDateTime,Timestamp](
    d => Timestamp.from(d.toInstant(ZoneOffset.ofHours(0))),
    d => d.toLocalDateTime
  )

  implicit val dateMapper = MappedColumnType.base[LocalDate,Date](
    d => Date.valueOf(d),
    d => d.toLocalDate
  )
}
