[![](https://jitpack.io/v/NightGoat/KEXtensions.svg)](https://jitpack.io/#NightGoat/KEXtensions)
# KEXtensions
Kotlin and Android extensions library.

## About:
As Android developer, i use in my work Kotlin as main coding language, and Kotlin has a big killer feature - [extensions](https://kotlinlang.org/docs/extensions.html). Some extensions, such as [orIfNull](https://github.com/NightGoat/KEXtensions/blob/master/KEXtensions/src/main/java/ru/nightgoat/kextensions/OtherExt.kt) or [doOnDefault](https://github.com/NightGoat/KEXtensions/blob/master/KEXtensions/src/main/java/ru/nightgoat/kextensions/HighOrderFunctions.kt) I have been using many many times in alot of projects, and get kinda tired of copying this code in all projects, so this library was born.

## Examples:
Using this library does not require knowledge or skill, this extensions will work as part of Kotlin language. For example:
You have liveData, and you want to get value from it, but your method requires non null values, you can use extension from [IntExt](https://github.com/NightGoat/KEXtensions/blob/master/KEXtensions/src/main/java/ru/nightgoat/kextensions/IntExt.kt): orZero():
```
val number: LiveData<Int?> = liveData {
	val result = getNumber()
	emit(result)
}

fun main {
    foo(number.value.orZero())
}

fun foo(bar: Int) {
   doStuff(bar)
}
```
Or if you have string from server with date like this: 2021-04-20, and you want it to be in Java's Date, you can use [StringExt](https://github.com/NightGoat/KEXtensions/blob/master/KEXtensions/src/main/java/ru/nightgoat/kextensions/StringExt.kt): and [DateFormat constants](https://github.com/NightGoat/KEXtensions/blob/master/KEXtensions/src/main/java/ru/nightgoat/kextensions/utils/constants/DateFormats.kt):
```
val serverString = "2021-04-20"
val newDate = serverString.toDate(DATE_FORMAT_yyyy_mm_dd)
```
if you want it back in another string, you can use this
```
newDate.toStringFormatted(DATE_FORMAT_dd_mm_yyyy)
```
or just use formatDate in first place:
```
serverString.formatDate(DATE_FORMAT_yyyy_mm_dd, DATE_FORMAT_dd_mm_yyyy)
```

## Logging
Some extensions do require logging, as they catching exceptions or catching null values from some places. Default Logger in this library is ordinary Android Log.e tool, but you can change it to any you want. Its already has Timber, so if you want to use it you need set it in your app starting point (Application or Activity) like this (of course you need to have Timber in your graddle dependencies):
```
Kex.setTimber()
```
If you want you can have your custom logger, you need to use ILogger interface from library and set your logger to Kex:
```
object MyCustomLogger: ILogger {
    override fun loggE(message: String, tag: String?, e: Throwable?) {
        //Put your logging code here
    }
}

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Kex.setCustomLogger(MyCustomLogger)
    }
}
```
Or you can just turn it off: 
```
Kex.turnOffLogger()
```

## How to add it:
* Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
```
  allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
* Step 2. Add the dependency
```
dependencies {
	        implementation 'com.github.nightgoat:kextensions:0.1.0'
	}
```
