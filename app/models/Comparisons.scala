package models

object Comparisons {
  def getComparisons(from: MeasurementSet, originalPop: List[MeasurementSet]) = {
    val population = originalPop.filter(_.playerId != from.playerId)

    def divideOrNone(first: Double, second: Double) = {
      if (second == 0) {
        None
      } else {
        Some(first/second)
      }
    }

    def divideAndSumOrNone(first: Option[List[Double]], second: Int) = {
      if (first.isDefined && first.size > 0 && second != 0) {
        Some(first.get.sum/second)
      } else {
        None
      }
    }

    def sqrtOrNone(num: Option[Double]) = num.map { n =>
      math.sqrt(n)
    }

    def dev(list: List[Double], mean: Option[Double]): Option[List[Double]] = mean.map { m =>
      list.map( measurement => (measurement - m) * (measurement - m) )
    }

    val heights = population.map(_.height).flatten
    val weights = population.map(_.weight).flatten
    val wingspans = population.map(_.wingspan).flatten
    val armLengths = population.map(_.armLength).flatten
    val handSizes = population.map(_.handSize).flatten
    val time10s = population.map(_.time10).flatten
    val time20s = population.map(_.time20).flatten
    val time40s = population.map(_.time40).flatten
    val benchPresss = population.map(_.benchPress).flatten
    val verticalJumps = population.map(_.verticalJump).flatten
    val broadJumps = population.map(_.broadJump).flatten
    val cone3s = population.map(_.cone3).flatten
    val shuttle20s = population.map(_.shuttle20).flatten
    val shuttle60s = population.map(_.shuttle60).flatten
    val wonderlics = population.map(_.wonderlic).flatten

    val means = MeasurementSet(0,
      divideOrNone(heights.sum, heights.size),
      divideOrNone(weights.sum, weights.size),
      divideOrNone(wingspans.sum, wingspans.size),
      divideOrNone(armLengths.sum, armLengths.size),
      divideOrNone(handSizes.sum, handSizes.size),
      divideOrNone(time10s.sum, time10s.size),
      divideOrNone(time20s.sum, time20s.size),
      divideOrNone(time40s.sum, time40s.size),
      divideOrNone(benchPresss.sum, benchPresss.size),
      divideOrNone(verticalJumps.sum, verticalJumps.size),
      divideOrNone(broadJumps.sum, broadJumps.size),
      divideOrNone(cone3s.sum, cone3s.size),
      divideOrNone(shuttle20s.sum, shuttle20s.size),
      divideOrNone(shuttle60s.sum, shuttle60s.size),
      divideOrNone(wonderlics.sum, wonderlics.size)
    )

    val stddevs = MeasurementSet(0,
      sqrtOrNone(divideAndSumOrNone(dev(heights, means.height), heights.size)),
      sqrtOrNone(divideAndSumOrNone(dev(weights, means.weight), weights.size)),
      sqrtOrNone(divideAndSumOrNone(dev(wingspans, means.wingspan), wingspans.size)),
      sqrtOrNone(divideAndSumOrNone(dev(armLengths, means.armLength), armLengths.size)),
      sqrtOrNone(divideAndSumOrNone(dev(handSizes, means.handSize), handSizes.size)),
      sqrtOrNone(divideAndSumOrNone(dev(time10s, means.time10), time10s.size)),
      sqrtOrNone(divideAndSumOrNone(dev(time20s, means.time20), time20s.size)),
      sqrtOrNone(divideAndSumOrNone(dev(time40s, means.time40), time40s.size)),
      sqrtOrNone(divideAndSumOrNone(dev(benchPresss, means.benchPress), benchPresss.size)),
      sqrtOrNone(divideAndSumOrNone(dev(verticalJumps, means.verticalJump), verticalJumps.size)),
      sqrtOrNone(divideAndSumOrNone(dev(broadJumps, means.broadJump), broadJumps.size)),
      sqrtOrNone(divideAndSumOrNone(dev(cone3s, means.cone3), cone3s.size)),
      sqrtOrNone(divideAndSumOrNone(dev(shuttle20s, means.shuttle20), shuttle20s.size)),
      sqrtOrNone(divideAndSumOrNone(dev(shuttle60s, means.shuttle60), shuttle60s.size)),
      sqrtOrNone(divideAndSumOrNone(dev(wonderlics, means.wonderlic), wonderlics.size))
    )


    def scale(distance: Double) = math.round((math.max(1.0, 100.0*math.min(math.pow(1.0/distance, 1.0/7.0), 1.0)) - 1.0) * 10.0) / 10.0;


    def zscoreify(m: MeasurementSet, means: MeasurementSet, stddevs: MeasurementSet) = {
      def z(p: Option[Double], m: Option[Double], s: Option[Double]) = {
        if (p.isDefined && m.isDefined && s.isDefined && s.get != 0) {
          Some((p.get - m.get) / s.get)
        } else if (m.isDefined) {
          Some(0.0)
        } else {
          None
        }
      }
      MeasurementSet(m.playerId,
        z(m.height, means.height, stddevs.height),
        z(m.weight, means.weight, stddevs.weight),
        z(m.wingspan, means.wingspan, stddevs.wingspan),
        z(m.armLength, means.armLength, stddevs.armLength),
        z(m.handSize, means.handSize, stddevs.handSize),
        z(m.time10, means.time10, stddevs.time10),
        z(m.time20, means.time20, stddevs.time20),
        z(m.time40, means.time40, stddevs.time40),
        z(m.benchPress, means.benchPress, stddevs.benchPress),
        z(m.verticalJump, means.verticalJump, stddevs.verticalJump),
        z(m.broadJump, means.height, stddevs.broadJump),
        z(m.cone3, means.cone3, stddevs.cone3),
        z(m.shuttle20, means.shuttle20, stddevs.shuttle20),
        z(m.shuttle60, means.shuttle60, stddevs.shuttle60),
        z(m.wonderlic, means.wonderlic, stddevs.wonderlic)
      )
    }

    def distance(m1: MeasurementSet, m2: MeasurementSet) = {
      def z(i1: Option[Double], i2: Option[Double]) = {
        if (i1.isDefined && i2.isDefined) {
          val t = i1.get - i2.get
          t * t
        } else {
          0.0
        }
      }

      math.sqrt(List(
        z(m1.height, m2.height),
        z(m1.weight, m2.weight),
        z(m1.wingspan, m2.wingspan),
        z(m1.armLength, m2.armLength),
        z(m1.handSize, m2.handSize),
        z(m1.time10, m2.time10),
        z(m1.time20, m2.time20),
        z(m1.time40, m2.time40),
        z(m1.benchPress, m2.benchPress),
        z(m1.verticalJump, m2.verticalJump),
        z(m1.broadJump, m2.broadJump),
        z(m1.cone3, m2.cone3),
        z(m1.shuttle20, m2.shuttle20),
        z(m1.shuttle60, m2.shuttle60),
        z(m1.wonderlic, m2.wonderlic)
      ).sum)
    }

    val z = zscoreify(from, means, stddevs);

    val distances = population.map( m => {
      (m.playerId, scale(distance(z, zscoreify(m, means, stddevs))))
    })

    distances.sortWith(_._2 >= _._2).take(10)
  }

}