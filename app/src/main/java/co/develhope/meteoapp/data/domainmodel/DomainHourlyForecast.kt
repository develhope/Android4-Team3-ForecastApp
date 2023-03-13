package co.develhope.meteoapp.data.domainmodel

import org.threeten.bp.OffsetDateTime

data class DomainHourlyForecast(
    val date: OffsetDateTime,
    val detailedCardForecast: DetailedCardForecast
)


