This current version does not work as expected. I'm currently debugging the issue and working out a fix.

# News-Feed [![CircleCI](https://circleci.com/gh/TheCodingWarlock/News-Feed/tree/master.svg?style=svg)](https://circleci.com/gh/TheCodingWarlock/News-Feed/tree/master)

This is a Kotlin app that fetches data from
Guardian API using [Retrofit](https://github.com/square/retrofit)
and shows the list in a RecyclerView. For the app to work as expected,
you need an API Key from
[The Guardian API](http://open-platform.theguardian.com/) and then place
it in the  gradle.properties file inside the MyApiKey String.

# Building The Project
You need an API Key from Guardian. In the .gitignore file, I have ignored secrets.properties which contains the keys. Change the secrets.sample.properties file to secrets.properties and fill in the key in it. This is done to keep the keys out of git but still build the project.

## Reach Out

[Twitter](https://twitter.com/eton_otieno)

[LinkedIn](https://www.linkedin.com/in/eton-otieno-10b7b0150/)
