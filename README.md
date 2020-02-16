# NYNewsFeed
NY Times Most Popular Articles Project

This repository contains a sample app of The New York Times most popular articles that implements the MVVM architecture using Dagger2, Navigation, ViewModel, DataBinding, LiveData, Retrofit, RxJava. 

# Highlights

1. MVVM Architectural pattern.
2. Android Architecture components.
2. RxJava used for asynchronous request.
3. Dagger2 used for Dependancy Injection.
4. Espresso used for UI automation testing.
5. Gradle scripts for running sonarqube static code analysis, code coverage, etc.

The application has been build with using the latest architecture guidline provided by google for android.
It has been designed with the best coding practice, maintained loosly coupled code, repository pattern, asynchronous api call and using all the **Android Architecture components**.

The whole application is built based on the MVVM architectural pattern.

# Application Architecture
<img src="/screenshots/mvvm-architecture.png" width="600" height="200" alt="Home"/> 

The main advatage of using MVVM, there is no two way dependency between ViewModel and Model unlike MVP. Here the view can observe the datachanges in the viewmodel as we are using LiveData which is lifecycle aware. The viewmodel to view communication is achieved through observer pattern (basically observing the state changes of the data in the viewmodel).

# Screenshots
<img src="/screenshots/Splash.png" width="346" height="615" alt="Home"/> 
<img src="/screenshots/News-List.jpg" width="346" height="615" alt="Home"/>
<img src="/screenshots/News-Details.jpg" width="346" height="615" alt="Home"/>
<img src="/screenshots/Search-Results.png" width="346" height="615" alt="Home"/>

# Programming Practices Followed

a) Android Architectural Components <br/>
b) Dagger 2 for Dependency Injection <br/>
c) MVVM <br/>
d) Retrofit with Okhttp <br/>
e) JUnit and Mockito for Unit testing <br/>
f) Repository pattern <br/>
g) Gson library for data serialization/deserialization<br/>
h) Glide library image processing<br/>
i) JUnit and Mockito for Unit testing <br/>
j) Espresso for UI automation testing <br/>

# How to build ?

Open terminal and type the below command to generate debug build <br/>

``` ./gradlew assembleDebug ```

Open terminal and type the below command to generate release build <br/>

``` ./gradlew assembleRelease ```


