package co.develhope.meteoapp.data

import co.develhope.meteoapp.ui.searchscreen.GetCitiesList
import org.threeten.bp.OffsetDateTime

object DataSource{
    private var selectedDay : OffsetDateTime? = null

    fun setSelectedDay(offsetDateTime: OffsetDateTime){
        selectedDay = offsetDateTime
    }

    fun getSelectedDay(): OffsetDateTime? = selectedDay

}



