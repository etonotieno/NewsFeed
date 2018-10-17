# News-Feed
This is a Kotlin app that fetches data from
Guardian API using [Retrofit](https://github.com/square/retrofit)
and shows the list in a RecyclerView. For the app to work as expected,
you need an API Key from
[The Guardian API](http://open-platform.theguardian.com/) and then place
it in the  gradle.properties file inside the MyApiKey String.


# News Feed v2.0 
I am currently working on the 2nd version of the app that will use [clean architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) in a modularized structure. 
This will be in five modules:

1. Presentation - This module will focus on the User Interface interactions.It will use the [MVVM pattern](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) to handle data and show 
it to the UI. This will be done using the ViewModel architecture component. The View will use a state model to render it's UI. 
This will be done with the help of a Resource data wrapper. The View will observe changes to data passed to it via the [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) using [LiveData](https://developer.android.com/topic/libraries/architecture/livedata).

2. Data - This module will handle fetching data from multiple sources(remote and cache). It will also contain the domain logic 
through Use Case classes.

3. Remote - This module will handle communication with the remote sources using a Retrofit interface.This will be done using 
[Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) to handle the async operations.

4. Cache - This module handles communication with the local database using [Room](https://developer.android.com/topic/libraries/architecture/room).

The Remote and Cache data models will use a mapper via [Kotlin Extension Functions](https://kotlinlang.org/docs/reference/extensions.html) to model the different models.

There are also plans to add the second source of news hence there will be two APIs intergrated i.e. The Guardian API and the [News API](https://newsapi.org/). This will be done via [dynamic feature modules](https://developer.android.com/studio/projects/dynamic-delivery#dynamic_feature_modules).

The UI will also incorporate [Material Design 2.0](https://material.io/) to make using the app more delightful.

Contributions through Pull Requests and Issues are much appreciated. 


## Reach Out

[Twitter](https://twitter.com/eton_otieno)

[LinkedIn](https://www.linkedin.com/in/eton-otieno-10b7b0150/)
