package co.develhope.meteoapp.data.dto

import co.develhope.meteoapp.data.domainmodel.Weather

fun Int.toWeather() : Weather {
        return when(this){
            0 -> Weather.SUNNY
            1 -> Weather.MAINLY_CLEAR
            2 -> Weather.PARTLY_CLOUDY
            3 -> Weather.CLOUDY
            45,48 -> Weather.FOG
            51,53,55 -> Weather.SUN_RAINY
            56,57 -> Weather.SUN_RAINY
            61,63,65 -> Weather.RAINY
            66,67 -> Weather.RAINY
            71,73,75 -> Weather.SNOWFALL
            77 -> Weather.SNOWFALL
            80,81,82 -> Weather.RAINY
            85,86 -> Weather.SNOWFALL
            95 -> Weather.THUNDERSTORM
            96,99 -> Weather.RAINY_THUNDERSTORM
            else -> Weather.CLOUDY
        }
    }