package models

import scala.slick.driver.PostgresDriver.simple._

sealed trait ContributionStatus
case object Valid extends ContributionStatus
case object Pending extends ContributionStatus
case object Deleted extends ContributionStatus

object ContributionStatusMapper {
  implicit val contributionStatusColumnType = MappedColumnType.base[ContributionStatus, Int](
    { 
      c => c match {
        case Pending => 2
        case Deleted => 1
        case _ => 0
      } 
    },
    { 
    	i => i match {
    		case 1 => Deleted
    		case 2 => Pending
    		case _ => Valid	
    	} 
    }
  )
}