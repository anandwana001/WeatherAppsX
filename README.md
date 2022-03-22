# WeatherAppsX

**WeatherAppsX** app is a sample networking app showing weather forecast of the current day and the
average forecast for the next four days.

## Architecture 📐
Project is a single module consist of multiple packages in order to achieve MVVM architecture.
* data
* di
* ui
* utils

## Android/Kotlin Beauty 🫀 🫁 🧠
* For animation, pre-defined AnimationUtils class is used
* Generic RecyclerView supporting list of ViewModel and DataBinding
* Custom DividerItemDecorator
* Retrieving and loading local JSON asset
* MockWebServer is used for mocking the data while doing instrumentation testing
* Separate Hilt module for testing and production

## Tech-stack 🛠
This project use multiple libraries to bring easy way of implementation
- [Hilt](https://dagger.dev/hilt/) - Dependency Injection Framework
- [Retrofit](https://github.com/square/retrofitm) - Used for Networking
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - to perform asynchronous work
- [Moshi](https://github.com/square/moshi) - use to serialize and deserialize Java objects to JSON
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - observable data holder
- [AndroidX](https://developer.android.com/jetpack/androidx) - android library for core functionalities
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - manage data in lifecycle aware fashion
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android
- [Data Binding](https://developer.android.com/topic/libraries/data-binding) - helps to bind UI with data source
- [Espresso](https://developer.android.com/training/testing/espresso) - Instrumentation Testing
- [Mock Web Server](https://github.com/square/okhttp/tree/master/mockwebserver) - Mock server for Testing
- [Robolectric](http://robolectric.org/) - Unit Testing

## Screenshot 📱
<p>
<img src="https://github.com/anandwana001/WeatherAppsX/blob/main/screenshots/app_screenshot.png" height=450 width=230 />
</p>

## Contact 🔗
Let's connect here -> [webprofile](https://anandwana001.github.io)

## License 📝
```
MIT License

Copyright (c) 2022 Akshay Nandwana

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
