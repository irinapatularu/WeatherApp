package weatherapp

class WeatherController {

    def weatherFetchService

    def index() {
    }

    def save() {
        def input = params.location
        int code = (int)input[0]
        if (code <= 9 && code >= 0){
            def response = weatherFetchService.getDataByCity(params.location)
        }
        else{
            def response = weatherFetchService.getDataByZip(params.location)
        }

        render(view: 'show', model: ['location':response.location, 'temp':response.temp, 'humidity':response.humidity, 'country':response.country, 'description':response.description])


    }
}
