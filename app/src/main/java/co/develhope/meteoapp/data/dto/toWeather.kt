package co.develhope.meteoapp.data.dto

import co.develhope.meteoapp.data.domainmodel.Weather

fun Int.toWeather() : Weather {
        return when(this){
            0 -> Weather.SUNNY
            1,2,3 -> Weather.CLOUDY
            45,48 -> Weather.CLOUDY
            51,53,55 -> Weather.RAINY
            56,57 -> Weather.RAINY
            71,73,75 -> Weather.RAINY
            80,81,82 -> Weather.RAINY
            95 -> Weather.RAINY
            96,99 -> Weather.RAINY
            else -> Weather.CLOUDY
        }
    }