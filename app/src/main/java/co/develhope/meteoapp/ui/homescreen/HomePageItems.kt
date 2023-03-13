package co.develhope.meteoapp.ui.homescreen


sealed class HomePageItems() {
    data class NextDays(
        val nextFiveDays: String
    ) : HomePageItems()

    data class HomeTitle(
        val title: Title
    ) : HomePageItems()

    data class SpecificDayWeather(
        val homeCardWeather: HomeCardWeather
    ) : HomePageItems()
}