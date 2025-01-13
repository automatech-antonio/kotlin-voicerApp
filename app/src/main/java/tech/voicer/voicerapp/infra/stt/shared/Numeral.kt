package tech.voicer.voicerapp.infra.stt.shared

data class Numeral(
  var type: NumeralTypes,
  var text: String,
  var value: Int?
)