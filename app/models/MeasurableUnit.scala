package models

sealed trait MeasurableUnit
case object Inches extends MeasurableUnit
case object Seconds extends MeasurableUnit
case object Reps extends MeasurableUnit
case object Pounds extends MeasurableUnit
case object Score extends MeasurableUnit
