# News-Feed
This is a simple app (very simple) that fetches data from the News API in JSON format and parses the response.

I will update the code to make the app better and the code base cleaner. Now I have intergrated https://github.com/square/retrofit in my app and made use of caching.

There are some issues with the app. On android phones running API level 19 and below, the newtork connection is not made and it gives an error about the certificates. On higher API level devices, the app runs perfectly and the data is retrieved from the cache successfully. Another issue is the big APK size. These issues will be resolved in due time.

Again, improvements and suggestions are greatly welcome!
