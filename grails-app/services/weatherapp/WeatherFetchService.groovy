package weatherapp

import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional
import groovy.json.JsonSlurper
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject

@Transactional
class WeatherFetchService {

    def rest

    def getDataByZip(String zipCode){
        def APPID = "fa1222b1360cebd73d2990a3c9491f4d"
        def myUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + zipCode + "&appid="
        myUrl = myUrl + APPID

        def values = getData(myUrl)
        return values
    }
    def getDataByCity(String location) {
        def APPID = "fa1222b1360cebd73d2990a3c9491f4d"
        def myUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid="
        myUrl = myUrl + APPID

        def values = getData(myUrl)
        return values
    }

    def getData(String myUrl){

//        String mock = '{"coord":{"lon":-0.13,"lat":51.51},"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"base":"cmc stations","main":{"temp":292.44,"pressure":1010,"humidity":92,"temp_min":290.37,"temp_max":294.82},"wind":{"speed":0.51,"deg":135,"gust":2.06},"rain":{"1h":0.25},"clouds":{"all":100},"dt":1466592619,"sys":{"type":3,"id":54708,"message":0.004,"country":"GB","sunrise":1466567013,"sunset":1466626909},"id":2643743,"name":"London","cod":200}'
        def resp  = rest.get(myUrl){
            contentType 'application/json'
            accept JSONObject}

        println resp
//        JSONElement userJson =  JSON.parse(resp)

//        String sys = "sys"
//        String weather = "weather"
//        String main = "main"
//        String name = "name"
//
//        String temp = userJson[main].temp;
//        String humidity = userJson[main].humidity;
//        String country = userJson[sys].country;
//        String loc = userJson[name];
//        String description = userJson[weather].description
//
//
//        def values = [:]
//        values['temp'] = temp
//        values['humidity'] = humidity
//        values['country'] = country
//        values['location'] = loc
//        values['description'] = description
//        return values
    }
}
