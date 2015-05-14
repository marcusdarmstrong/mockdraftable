package models

case class Measurement(
  val id: Int,
  val status: ContributionStatus,
  val playerId: Int,
  val measurable: Measurable,
  val measurement: Double,
  val source: Int
) {
  def display: String = {
    if ((measurable == Measurables.height || measurable == Measurables.wingspan) && measurement > 12 ) {
      math.floor(measurement / 12).toLong + "' " + (measurement % 12).toLong + getFractionEntity(measurement) + "\""
    } else if (measurable.unit == Inches) {
      math.floor(measurement).toLong + getFractionEntity(measurement) + "\""
    } else if (measurable.unit == Seconds) {
      BigDecimal(measurement).setScale(2, BigDecimal.RoundingMode.HALF_UP).toString
    } else {
      measurement.toLong.toString
    }
  }

  def getFractionEntity(measurement: Double) = {
    val frac = measurement - math.floor(measurement)
    if (frac == 0.125) {
      "&#8539;"
    } else if (frac == 0.25) {
      "&#188;"
    } else if (frac == 0.375) {
      "&#188;"
    } else if (frac == 0.5) {
      "&#188;"
    } else if (frac == 0.625) {
      "&#188;"
    } else if (frac == 0.75) {
      "&#188;"
    } else if (frac == 0.875) {
      "&#188;"
    } else if (frac != 0) {
      BigDecimal(frac).setScale(2, BigDecimal.RoundingMode.HALF_UP)
        .toDouble.toString.drop(1).take(3);
    } else {
      ""
    }
  }
}
