The application structure utilizes a clean architecture that contains independent layers that can be changed or tested separately with no effects on other layers. Description, starting from the bottom:

Network operations are implemented with the Retrofit2 library with okhtttp client and Moshi JSON parser, so the network response is parsed directly to Kotlin data classes. 

Network calls are called from the data repository with deferred methods. The network response is cached in the data repository for further use.

The data repository is injected into the view models. View models use data repository deferred methods from coroutines, where data is received and assigned to live data variables.

Live data variables are observed by views (Activities, Fragments, data bindings in xml view files), which then display the data.

In Fragment, based on observed data, actual lists are created with the corresponding recyclerview adapters.

Interaction diagram

![image](https://user-images.githubusercontent.com/11958691/114364865-24c2e580-9b7a-11eb-871d-2f240604876f.png)

Patterns used:

Android MVVM - separate the business and presentation logic from the UI.

Dependency Injection - implementation of the dependency inversion principle. It's done manually, which is sufficient in a project this size but can be easily converted to Dagger or Hilt form Android Jetpack.

 

Libraries used:

Retrofit2 with okhttp and Moshi - network communication and JSON conversion.

Glide - display and cache images.

Kotlin Coroutines - asynchronous programming.

DataBinding - enables binding data, e.g. from the view model to the XML view and helps reducing boilerplate code.

 

Architecture Components used:

ViewModel - stores and manages UI-related data in respect of lifecycle

LiveData - lifecycle-aware observable data holder
