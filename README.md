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

First of all, copy all JAR dependencies on the same folder the `fcm-test.jar` is.

Then, create a `config.json` file similar to this:

```json
{
  "keyFile": "/path/to/your/service-account.json",
  "qty": 3,
  "doEnumerateMessages": true,
  "projectId": "your-project-id",
  "message": "This is a TEST MESSAGE from the grave",
  "title": "TESTING, TESTING...",
  "tokens": [
    "list",
    "all-the",
    "push-notifications",
    "tokens you want to send the notification to"
  ],
  "additionalData": null
}
```

Where:

1. `keyFile` is the `service-account.json` file. You get this from the FCM console at Google's site.
2. `qty` is how many messages  you want to send.
3. `doEnumerateMessages` indicates if software should append message number to `title` and `message`.
4. `projectId` is the configured project id. You set this in the FCM console at Google's site.
5. `message` is the message you want to send
6. `title` is the title you want to send
7. `tokens` is a JSON array of the push notifications tokens you want the messages sent to.
8. additionalData is a JSON object that will be sent along with the notification

Finally, you just run:

```$ java -jar fcm-test.jar```

If everything is configured as expected, you should get all the notifications on your devices.
