package models

import play.api.Logger

object Positions {
	// This shouldn't change often/ever, so it's all spelled out here in static code for efficiency
	val ATH = Position(1, "ATH", "Athlete", Group)
	val SKILL = Position(2, "SKILL", "Skill Position Player", Group)
    val QB = Position(21, "QB", "Quarterback", Primary)
    val BALL = Position(22, "BALL", "Ball Carrier", Group)
      val RB = Position(221, "RB", "Runningback", Primary)
        val `3DB` = Position(2211, "3DB", "Third Down Back", Role)
        val HB = Position(2212, "HB", "Halfback", Role)
        val FB = Position(2213, "FB", "Fullback", Primary)
      val TE = Position(222, "TE", "Tight End", Primary)
        val JKR = Position(2221, "JKR", "Joker Tight End", Role)
        val HTE = Position(2222, "HTE", "H-Back", Role)
        val FTE = Position(2223, "FTE", "Receiving Tight End", Role)
        val YTE = Position(2224, "YTE", "Blocking Tight End", Role)
      val WR = Position(223, "WR", "Wide Receiver", Primary)
        val SR = Position(2231, "SR", "Slot Receiver", Role)
        val X = Position(2232, "X", "Split End Receiver", Role)
        val Z = Position(2233, "Z", "Flanker Receiver", Role)
      val OW = Position(224, "OW", "Offensive Weapon", Primary)
	val OL = Position(3, "OL", "Offensive Line", Group)
    val OT = Position(31, "OT", "Offensive Tackle", Primary)
      val LT = Position(311, "LT", "Left Tackle", Role)
      val RT = Position(312, "RT", "Right Tackle", Role)
    val IOL = Position(32, "IOL", "Interior Offensive Line", Group)
      val OG = Position(321, "OG", "Offensive Guard", Primary)
        val LG = Position(3211, "LG", "Left Guard", Role)
        val RG = Position(3212, "RG", "Right Guard", Role)
      val OC = Position(322, "OC", "Offensive Center", Primary)
	val DL = Position(4, "DL", "Defensive Line", Group)
    val IDL = Position(41, "IDL", "Interior Defensive Line", Group)
      val DT = Position(411, "DT", "Defensive Tackle", Primary)
        val `3T` = Position(4111, "3T", "3 Technique Defensive Tackle", Role)
        val NT = Position(4112, "NT", "Nose Tackle", Primary)
          val `0T` = Position(41121, "0T", "Two-Gap Nose Tackle", Role)
          val `1T` = Position(41122, "1T", "One-Gap Nose Tackle", Role)
      val `5T` = Position(412, "5T", "Two-Gap Defensive End", Primary)
		val DE = Position(42, "DE", "One-Gap Defensive End", Primary)
      val LDE = Position(421, "LDE", "Left Defensive End", Role)
      val RDE = Position(422, "RDE", "Right Defensive End", Role)
  val EDGE = Position(5, "EDGE", "Edge Defender", Group)
	val LB = Position(6, "LB", "Linebacker", Group)
    val OBLB = Position(61, "OBLB", "Off-Ball Linebacker", Group)
      val ILB = Position(611, "ILB", "Inside Linebacker", Primary)
        val COVER = Position(6111, "COVER", "Coverage Linebacker", Role)
        val HIT = Position(6112, "HIT", "Hit Linebacker", Role)
        val MIKE = Position(6113, "MIKE", "MIKE Linebacker", Role)
      val OLB = Position(612, "OLB", "Outside Linebacker", Primary)
        val SLB = Position(6121, "SLB", "Strongside Linebacker", Role)
        val WLB = Position(6122, "WLB", "Weakside Linebacker", Role)
      val HSL = Position(613, "HSL", "Hybrid Safety/Linebacker", Primary)
    val `34B` = Position(62, "34B", "3-4 Outside Linebacker", Primary)
      val SLB34 = Position(621, "SLB34", "3-4 Strongside Linebacker", Role)
      val WLB34 = Position(622, "WLB34", "3-4 Weakside Linebacker", Role)
	val DB = Position(7, "DB", "Defensive Back", Group)
    val S = Position(71, "S", "Safety", Primary)
      val FS = Position(711, "FS", "Deep/Free Safety", Role)
      val SS = Position(712, "SS", "Box/Strong Safety", Role)
    val CB = Position(72, "CB", "Cornerback", Primary)
      val C2CB = Position(721, "C2CB", "Cover 2 Cornerback", Role)
      val PCB = Position(722, "PCB", "Press Cornerback", Role)
      val SCB = Position(723, "SCB", "Slot Cornerback", Role)
	val ST = Position(8, "ST", "Special Teams", Group)
    val K = Position(801, "K", "Kicker", Primary)
      val KOS = Position(8011, "KOS", "Kickoff Specialist", Role)
    val P = Position(802, "P", "Punter", Primary)
    val LS = Position(803, "LS", "Long Snapper", Primary)
    val GUN = Position(804, "GUN", "Gunner", Role)
    val PP = Position(805, "PP", "Punt Protector", Role)
    val VICE = Position(806, "VICE", "Vice", Role)
    val H = Position(807, "H", "Holder", Role)
    val U = Position(808, "U", "Upback", Role)
    val PR = Position(809, "PR", "Punt Returner", Role)
    val KR = Position(810, "KR", "Kick Returner", Role)

	val children = Map(
		ATH -> Set(ATH, SKILL, QB, BALL, OW, RB, `3DB`, HB, FB, TE, JKR, HTE, FTE, YTE, 
      WR, SR, X, Z, OL, OT, LT, RT, IOL, OG, LG, RG, OC, DL, IDL, DT, `3T`, NT, 
      `0T`, `1T`, `5T`, DE, RDE, LDE, EDGE, LB, OBLB, ILB, COVER, HIT, MIKE, OLB, 
      SLB, WLB, HSL, `34B`, SLB34, WLB34, DB, S, FS, SS, CB, C2CB, PCB, SCB, ST, K, 
      KOS, P, LS, GUN, PP, VICE, H, U, PR, KR),

    SKILL -> Set(SKILL, QB, BALL, OW, RB, `3DB`, HB, FB, TE, JKR, HTE, FTE, YTE, 
      WR, SR, X, Z),
    BALL -> Set(BALL, OW, RB, `3DB`, HB, FB, TE, JKR, HTE, FTE, YTE, WR, SR, X, Z),
    RB -> Set(RB, `3DB`, HB, FB),
    TE -> Set(TE, JKR, HTE, FTE, YTE),
    WR -> Set(WR, SR, X, Z),

    OL -> Set(OL, OT, LT, RT, IOL, OG, LG, RG, OC),
    OT -> Set(OT, LT, RT),
    IOL -> Set(IOL, OG, LG, RG, OC),
    OG -> Set(LG, RG),

    DL -> Set(DL, IDL, DT, `3T`, NT, `0T`, `1T`, `5T`, DE, RDE, LDE),
    IDL -> Set(IDL, DT, `3T`, NT, `0T`, `1T`, `5T`),
    DT -> Set(DT, `3T`, NT, `0T`, `1T`),
    NT -> Set(NT, `0T`, `1T`),
    DE -> Set(DE, RDE, LDE),

		EDGE -> Set(EDGE, `34B`, SLB34, WLB34, DE, RDE, LDE),

    LB -> Set(LB, OBLB, ILB, COVER, HIT, MIKE, OLB, SLB, WLB, HSL, `34B`, SLB34, WLB34),
    OBLB -> Set(OBLB, ILB, COVER, HIT, MIKE, OLB, SLB, WLB, HSL),
    ILB -> Set(ILB, COVER, HIT, MIKE),
    OLB -> Set(OLB, SLB, WLB),
    `34B` -> Set(`34B`, SLB34, WLB34),

    DB -> Set(DB, S, FS, SS, CB, C2CB, PCB, SCB),
    S -> Set(S, FS, SS),
    CB -> Set(CB, C2CB, PCB, SCB),

    ST -> Set(ST, K, KOS, P, LS, GUN, PP, VICE, H, U, PR, KR),
    K -> Set(K, KOS)
	)

  val tree = Map(
    ATH -> Set(ATH),

    SKILL -> Set(SKILL, ATH),
    QB -> Set(QB, SKILL, ATH),
    BALL -> Set(BALL, SKILL, ATH),

    RB -> Set(RB, BALL, SKILL, ATH),
    `3DB` -> Set(`3DB`, RB, BALL, SKILL, ATH),
    HB -> Set(HB, RB, BALL, SKILL, ATH),
    FB -> Set(FB, RB, BALL, SKILL, ATH),

    TE -> Set(TE, BALL, SKILL, ATH),
    JKR -> Set(JKR, TE, BALL, SKILL, ATH),
    HTE -> Set(HTE, TE, BALL, SKILL, ATH),
    FTE -> Set(FTE, TE, BALL, SKILL, ATH),
    YTE -> Set(YTE, TE, BALL, SKILL, ATH),

    WR -> Set(WR, BALL, SKILL, ATH),
    SR -> Set(SR, WR, BALL, SKILL, ATH),
    X -> Set(X, WR, BALL, SKILL, ATH),
    Z -> Set(Z, WR, BALL, SKILL, ATH),

    OL -> Set(OL, ATH),
    OT -> Set(OT, OL, ATH),
    LT -> Set(LT, OT, OL, ATH),
    RT -> Set(RT, OT, OL, ATH),
    IOL -> Set(IOL, OL, ATH),
    OG -> Set(OG, IOL, OL, ATH),
    LG -> Set(LG, OG, IOL, OL, ATH),
    RG -> Set(RG, OG, IOL, OL, ATH),
    OC -> Set(OC, IOL, OL, ATH),

    DL -> Set(DL, ATH),
    IDL -> Set(IDL, DL, ATH),
    `5T` -> Set(`5T`, IDL, DL, ATH),
    DT -> Set(DT, IDL, DL, ATH),
    `3T` -> Set(`3T`, DT, IDL, DL, ATH),
    NT -> Set(NT, DT, IDL, DL, ATH),
    `0T` -> Set(`0T`, NT, DT, IDL, DL, ATH),
    `1T` -> Set(`1T`, NT, DT, IDL, DL, ATH),

    DE -> Set(DE, DL, ATH, EDGE),
    RDE -> Set(RDE, DE, DL, ATH, EDGE),
    LDE -> Set(LDE, DE, DL, ATH, EDGE),

    EDGE -> Set(EDGE, ATH),

    LB -> Set(LB, ATH),
    OBLB -> Set(OBLB, LB, ATH),
    HSL -> Set(HSL, OBLB, LB, ATH),
    ILB -> Set(ILB, OBLB, LB, ATH),
    COVER -> Set(COVER, ILB, OBLB, LB, ATH),
    HIT -> Set(HIT, ILB, OBLB, LB, ATH),
    MIKE -> Set(MIKE, ILB, OBLB, LB, ATH),
    OLB -> Set(OLB, OBLB, LB, ATH),
    SLB -> Set(SLB, OLB, OBLB, LB, ATH),
    WLB -> Set(WLB, OLB, OBLB, LB, ATH),

    `34B` -> Set(`34B`, LB, EDGE, ATH),
    SLB34 -> Set(SLB34, `34B`, LB, EDGE, ATH),
    WLB34 -> Set(WLB34, `34B`, LB, EDGE, ATH),

    DB -> Set(DB, ATH),
    S -> Set(S, DB, ATH),
    FS -> Set(FS, S, DB, ATH),
    SS -> Set(SS, S, DB, ATH),
    CB -> Set(CB, DB, ATH),
    C2CB -> Set(C2CB, CB, DB, ATH),
    PCB -> Set(PCB, CB, DB, ATH),
    SCB -> Set(SCB, CB, CB, ATH),

    ST -> Set(ST, ATH),
    K -> Set(K, ST, ATH),
    KOS -> Set(KOS, K, ST, ATH),
    P -> Set(P, ST, ATH),
    LS -> Set(LS, ST, ATH),
    GUN -> Set(GUN, ST, ATH), 
    PP -> Set(PP, ST, ATH),
    VICE -> Set(VICE, ST, ATH),
    H -> Set(H, ST, ATH),
    U -> Set(U, ST, ATH),
    PR -> Set(PR, ST, ATH),
    KR -> Set(KR, ST, ATH)
  )

	val ids = children.get(ATH).getOrElse(Set()).map(p => p.id -> p).toMap
  val abbrs = children.get(ATH).getOrElse(Set()).map(p => p.abbr -> p).toMap

	def getImpliedPositions(positionId: Int) = {
    val pos = getPosition(positionId)
    tree.get(pos) getOrElse Set(pos)
  }
  def getChildPositions(positionId: Int) = {
    val pos = getPosition(positionId)
    children.get(pos) getOrElse Set(pos)
  }
	def getPosition(positionId: Int) = ids.get(positionId) getOrElse ATH
  def getPositionForAbbr(positionAbbr: String) = abbrs.get(positionAbbr) getOrElse ATH
  def getDisplayPosition(positions: Set[Position]) = {
    // LCA, no roles
    // Ignore all 8* positions, unless they are the only ones availble
    var idx = 0;
    var partial = ""
    Logger.debug("Position set: " + positions)
    var success = true;
    do {
      var current = 'a';
      positions.foreach { p =>
        if (p.positionType != Group) {
          val id = p.id.toString
          if (id.length <= idx) {
            success = false
          } else {
            if (!id.startsWith("8")) {
              if (current == 'a') {
                current = id.charAt(idx)
                Logger.debug("Setting current to " + current)
              } else {
                success &= id.charAt(idx) == current
              }
            }
          }
        }
      }
      if (success) {
        partial += current
        Logger.debug("Setting partial to " + partial)
      }
      idx += 1
    } while (success)
    Logger.debug("Partial: " + partial)
    if (partial == "") {
      if (positions.subsetOf(Set(`34B`, SLB34, WLB34, DE, RDE, LDE))) {
        EDGE
      } else if (positions.subsetOf(Set(K, KOS))) {
        K
      } else if (positions == Set(P)) {
        P
      } else if (positions == Set(LS)) {
        LS
      } else if (positions.subsetOf(Set(K, KOS, P, LS, GUN, PP, VICE, H, U, PR, KR))) {
        ST
      } else {
        Logger.debug("Default ATH returned")
        ATH
      }
    } else {
      var pos = getPosition(partial.toInt)
      while (pos.positionType == Role) {
        pos = getPosition(pos.id / 10)
        Logger.debug("Reducing to: " + pos)
      }
      pos
    }
  }

}