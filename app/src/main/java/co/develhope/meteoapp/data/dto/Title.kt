package co.develhope.meteoapp.data.dto

import co.develhope.meteoapp.data.domainmodel.Weather
import org.threeten.bp.OffsetDateTime

data class Title(
    val city: String,
    val region: String,
)