package weatherapp

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import groovy.json.JsonSlurper
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject
import groovy.time.*
import java.text.SimpleDateFormat
import java.util.Date

@Transactional
class WeatherFetchService {

    def rest

    def APPID = "fa1222b1360cebd73d2990a3c9491f4d"
    def unit = "&units=metric"

    def getDataByZip(String zipCode){

        def myUrl = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + "&appid="
        myUrl = myUrl + APPID

        def values = getData(myUrl)
        return values
    }

    def getDataByCity(String location) {
        Weather variable = Weather.findByLocation(location)

        if (variable == null) {
            //nu exista in baza de date, se adauga intrare noua

            def myUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + location + unit + "&appid="
            myUrl = myUrl + APPID

            def values = getData(myUrl)
            return values
        }
        else{

            Date date = variable.lastUpdated
            Date currentDate = new Date();
            TimeDuration duration = TimeCategory.minus(currentDate, date)

            if (duration.hours >= 1) {
                //e mai mult de o ora intre ele
                def myUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + location + unit + "&appid="
                myUrl = myUrl + APPID

                def values = [:]
                def resp  = rest.get(myUrl){
                    contentType 'application/json'
                    accept JSONObject
                }
                variable.temp = resp.body.main.temp
                variable.humidity = resp.body.main.humidity
                variable.country = resp.body.sys.country
                variable.location = resp.body.name
                variable.description = resp.body.weather.description
                return values
            }
            else {
                //nu e mai mult de o ora
                def values = [:]
                values.location = variable.location
                values.humidity = variable.humidity
                values.temp = variable.temp
                values.country = variable.country
                values.description = variable.description
                return values
            }

        }
    }
    def getData(String myUrl){

        def values = [:]
        def resp  = rest.get(myUrl){
            contentType 'application/json'
            accept JSONObject
        }

        if (!resp.body.cod.equals("404")) {

            values['temp'] = resp.body.main.temp
            values['humidity'] = resp.body.main.humidity
            values['country'] = resp.body.sys.country
            values['location'] = resp.body.name
            values['description'] = resp.body.weather.description


            Weather weather = new Weather()
            weather.location = values.location
            weather.humidity = values.humidity
            weather.temp = values.temp
            weather.country = values.country
            weather.description = values.description
            weather.lastUpdated = new Date()
            weather.save(flush: true, failOnError: true, validate: true)
        }
        return values
    }
}
