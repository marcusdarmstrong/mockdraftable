package models

case class DisplayableMeasurement(
  val measurable: Measurable,
  val measurement: Double
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
      "&#8540;"
    } else if (frac == 0.5) {
      "&#189;"
    } else if (frac == 0.625) {
      "&#8541;"
    } else if (frac == 0.75) {
      "&#190;"
    } else if (frac == 0.875) {
      "&#8542;"
    } else if (frac != 0) {
      BigDecimal(frac).setScale(2, BigDecimal.RoundingMode.HALF_UP)
        .toDouble.toString.drop(1).take(3);
    } else {
      ""
    }
  }
}
