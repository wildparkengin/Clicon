package ua.willeco.clicon.model

class Weather {
    var city: String? =""
    var humidity: String? =""
    var pressure: String? =""
    var temperature: String? =""
    var wind: String? =""
    var precipitation: String? =""

    var featureWeatherData: ArrayList<FeatureWeather>? = null

    class FeatureWeather{
        var day: String=""
        var dayDate: String =""
        var weatherStatus: Int = 0
        var weatherLowHight = arrayListOf<Int>()
    }
}