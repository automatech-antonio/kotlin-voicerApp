package tech.voicer.voicerapp.infra.stt.shared

fun getUnitNumericValues(): MutableList<Numeral> {
  return mutableListOf(
    Numeral(NumeralTypes.UNIT, "zero", 0),
    Numeral(NumeralTypes.UNIT, "um", 1),
    Numeral(NumeralTypes.UNIT, "dois", 2),
    Numeral(NumeralTypes.UNIT, "três", 3),
    Numeral(NumeralTypes.UNIT, "quatro", 4),
    Numeral(NumeralTypes.UNIT, "cinco", 5),
    Numeral(NumeralTypes.UNIT, "seis", 6),
    Numeral(NumeralTypes.UNIT, "meia", 6),
    Numeral(NumeralTypes.UNIT, "sete", 7),
    Numeral(NumeralTypes.UNIT, "oito", 8),
    Numeral(NumeralTypes.UNIT, "nove", 9),
  )
}
