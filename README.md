# fcm-test

This is a very small and simple to test push notification sending through Google's FCM. It was useful for
me when I needed to test push notifications in a hostile (in terms of networking conditions) remote
environment which I did not have control on.

Feel free to add what you need and send me a pull request. I almost always approve them without
much hesitation (or control). I know this is bad.

It has way more dependencies that I would have liked:

* Google API Client (and all of its dependencies)
* log4j2
* Jackson
* Jackson Databind

If you manage to remove some, I would love to merge your changes since I hate having dependencies I 
could have avoided with a couple of lines of code.

## Usage


```$ java -jar fcm-test.jar```
