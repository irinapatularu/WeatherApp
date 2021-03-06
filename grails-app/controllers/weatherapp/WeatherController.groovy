package weatherapp

class WeatherController {

    def weatherFetchService

    def index() {
    }

    def save() {
        def input = params.location
        def response


            if (!input.matches(".*\\d.*")) {
                response = weatherFetchService.getDataByCity(params.location)
            } else {
                response = weatherFetchService.getDataByZip(params.location)
            }

            if (response) {
                render(view: 'index', model: ['location':response.location, 'temp':response.temp, 'humidity':response.humidity,  'country':response.country, 'description':response.description])
            } else {
                render "error"
            }




    }
}
