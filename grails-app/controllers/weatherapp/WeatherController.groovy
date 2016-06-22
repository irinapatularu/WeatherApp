package weatherapp

class WeatherController {

    def weatherFetchService

    def index() {
    }

    def save() {
        def response = weatherFetchService.getData(params.location)
        render(view: 'show')
    }
}
