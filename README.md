# Kotlin-MVP-RxJava-Dagger2
Android Photo application implementing MVP Architecture, RxJava and Dagger2 with Kotlin programming language

Introduction
------------
### Kotlin
This Android application was written in [Kotlin][11] programming language.

Kotlin is an attractive language because it is not only pretty concise, but also 100% interoperable with Java and Android, bringing many advantages of a modern language such to the Android platform.

For more information using Kotlin for Android development, please refer to [http://kotlinlang.org/docs/reference/android-overview.html](http://kotlinlang.org/docs/reference/android-overview.html).

### Android MVP Architecture
This repository contains a Photo application implementing Android MVP Architecture.

The application illustrates how to implement [Model-View-Presenter][12] (MVP) architecture patteren based on Android.

Please refer to [Arndroid Architecture Blueprints][13] for detailed information about Android Model-View-Presenter Architecture

[Model-View-Presenter][12] pattern consists of : 

* The Model - defines data to be displayed and exposes them to the View through the presenter

* The View - displays data (the model), receives user's actions and routes to the presenter

* The Presenter - acts upon the model and the view, retrieves data from repositories (the model), and formats it for display in the view

Here is MVP architecture of this application.
<p><img src=https://softpian.github.io/images/kotlin_mvp_diagram.png width="700" /></p>

### RxJava
This Kotlin application was implemented based on [Reactive Programming][14] using [RxJava][15] that extends [Observer pattern][16] to support sequences of data/events.

[RxJava][15] was used as follows: 

* Making RESTFul API calls and processing responses - [RxJava2 Adapter][17] of Retrofit which makes Observable object used for receiving a response instead of normal Call object.
 
* Processing UI events, especially from EditText - [RxBinding][18]

* Scheduling on the main thread - [RxAndroid][19] which makes modules observe the task's result or outcome on the main thread
 

Please refer to [https://github.com/ReactiveX/RxJava](https://github.com/ReactiveX/RxJava) for more detailed information about RxJava.

### Dagger 2
The application implements [Dependency Injection design pattern][20] with [Dagger 2][21].

Dagger 2 is dependency injection framework maintained by Google which is one of the most efficient dependency injection frameworks built to date.

It analyzes dependencies and generates code to help wire them together based on annotations such as @Component, @Module, @Provides and so on. 

Please refer to [https://google.github.io/dagger](https://google.github.io/dagger) for more information of Dagger 2.

### flickr RESTful API
This application uses RESTful API of [flickr][0] which is a popular image/video hosting service in order to get photos.

It receives photo data formatted in JSON and converts it to Java Objects including photo url, title, owner's name, date, description, viewer's count, comment's count and so on.

Please visit [https://www.flickr.com/services/api/](https://www.flickr.com/services/api/) for more detailed information.


Android development skills
--------------------------
This repository is able to help understand how to use the following skills.
* How to implement Android Model-View-Presenter architecture pattern with Kotlin
* How to use RxJava, RxBinding and RxAndroid for Reactive programming in Android development with Kotlin
* How to apply Dependency Injection design pattern to Android application with Dagger 2 framework
* How to call RESTful API with Retrofit
* How to integrate Retrofit with RxJava through RxJava Call adapter of Retrofit
* How to use OkHttp Logging Intercepter in order to debug HTTP request/response data  
* How to convert JSON to Java Objects with Gson
* How to load images from a remote server with Glide
* How to reduce boilerplate codes with Kotlin Android Extensions
* How to implement Android material design using CoordinatorLayout, AppBarLayout, CollapsingToolbarLayout and so on

Screenshots
-----------
![KotlinPhotoMVP screenshot](https://softpian.github.io/gifs/KotlinPhotoMVP_2.gif)


Getting Started
--------------
In your local.properties file, put your own key given from [flickr][100] :

```
flickrApiKey="yourOwnAPIKey"
```
For example, you should write it as below. 
(The following API Key is not real one. Never use it in your application.)
```
flickrApiKey="788a5fnd5r134id6a792ff39pp68dcs3"
```

Libraries Used
---------------
* [RxJava][15] - Java VM implementation of Reactive Extensions
* [RxBinding][18] - RxJava binding APIs for Android UI widgets from the platform and support libraries
* [RxAndroid][19] - Module providing a Scheduler that schedules on the main thread
* [Dagger 2][1] - Framework to implement Dependency injection pattern for Android and Java
* [Retrofit][2] - Type-safe HTTP client for Android and Java which makes it easier to consume RESTful API services.
* [Retrofit 2 RxJava 2 Adapter][3] - Helps handle a response from server with RxJava as custom Call adapter
* [OkHttp Logging Intercepter][4] - Logs HTTP request and response data with different logging levels in order to debug HTTP error 
* [Gson][5] - JSON library for Android and Java which makes it easy to parse JSON into Java objects. Used with Retrofit Gson converter
* [Glide][6] - A fast and efficient image loading library for Android focused on smooth scrolling which offers an easy to use


Reference
---------
* [Kotlin][11]
* [Arndroid Architecture Blueprints][13]
* [Model–View–Presenter pattern][12]
* [RxJava][15]
* [Reactive Extensions][22]
* [Reactive Programming][14]
* [Dagger 2][21]
* [Dependency Injection][20]


[0]: https://www.flickr.com/
[1]: https://google.github.io/dagger
[2]: http://square.github.io/retrofit/
[3]: https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2
[4]: https://github.com/square/okhttp/wiki/Interceptors
[5]: https://github.com/google/gson
[6]: https://bumptech.github.io/glide/

[11]: https://kotlinlang.org/
[12]: https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter
[13]: https://github.com/googlesamples/android-architecture
[14]: https://en.wikipedia.org/wiki/Reactive_programming
[15]: https://github.com/ReactiveX/RxJava
[16]: https://en.wikipedia.org/wiki/Observer_pattern
[17]: https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava2
[18]: https://github.com/JakeWharton/RxBinding
[19]: https://github.com/ReactiveX/RxAndroid
[20]: https://en.wikipedia.org/wiki/Dependency_injection
[21]: https://google.github.io/dagger
[22]: http://reactivex.io/

[100]: https://www.flickr.com/services/api/

License
-------

    Copyright Jaemoon Hwang <jaemoon.hwang@gmail.com>

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    