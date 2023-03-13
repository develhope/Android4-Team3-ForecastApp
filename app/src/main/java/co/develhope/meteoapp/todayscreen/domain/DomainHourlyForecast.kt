package co.develhope.meteoapp.todayscreen.domain

import org.threeten.bp.OffsetDateTime

data class DomainHourlyForecast(
    val date: OffsetDateTime,
    val detailedCardForecast: DetailedCardForecast
)


