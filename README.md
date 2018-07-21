#Popular Movies Stage 2 

This app requires your own API key which can be provided by https://www.themoviedb.org/documentation/api.
The gradle.properties file is ignored to store the api key.
Just add a gradle.properties file with the following and add your own api key there to run the app:

Project-wide Gradle settings.
IDE (e.g. Android Studio) users:
Gradle settings configured through the IDE *will override*
any settings specified in this file.
For more details on how to configure your build environment visit
http://www.gradle.org/docs/current/userguide/build_environment.html
Specifies the JVM arguments used for the daemon process.
he setting is particularly useful for tweaking memory settings.
org.gradle.jvmargs=-Xmx1536m
When configured, Gradle will run in incubating parallel mode.
This option should only be used with decoupled projects. More details, visit
http://www.gradle.org/docs/current/userguide/multi_project_builds.html#sec:decoupled_projects
org.gradle.parallel=true
*api_key="your_api_key_here"*

This project is for the Android Nanodegree with Udacity and
the Grow With Google Scholarship.

Code is written in Java and XML. Libraries used are
Picasso, Retrofit2 and RecyclerView.

For stage 2 functionality was added to play trailers and mark movies as favorites.
