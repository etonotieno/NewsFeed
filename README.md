This current version does not work as expected. I'm currently debugging the issue and working out a fix.

# News-Feed [![CircleCI](https://circleci.com/gh/etonotieno/NewsFeed.svg?style=svg)](https://circleci.com/gh/etonotieno/NewsFeed)

This is a Kotlin app that fetches data from two news sources, the
Guardian API and News API using [Retrofit](https://github.com/square/retrofit)
and shows the list in a RecyclerView. For the app to work as expected,
you need an API Key from both Guardian and News API.

# Building The Project
You need an API Key from Guardian. In the .gitignore file, I have ignored secrets.properties which contains the keys. Change the secrets.sample.properties file to secrets.properties and fill in the key in it. This is done to keep the keys out of git but still build the project.

## Reach Out

[Twitter](https://twitter.com/etonotieno)

[LinkedIn](https://www.linkedin.com/in/etonotieno)
