
# Weather App with Kotlin Coroutines

## Prerequisites
Install Java

## Direct Link (or)
using homebrew - brew cask install java
Install Android Studio

## Direct Link (or)
using homebrew - brew cask install android-studio
Install Android SDK

## Install it inside android studio ( Tools -> Android -> SDK Manager ) (or)
using homebrew - brew cask install android-sdk
Install gradle

##Direct Link (or)
brew install gradle


## Configuring path variable

```export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$ANDROID_HOME/tools:$PATH
export PATH=$ANDROID_HOME/platform-tools:$PATH
Add these three to ~/.zshrc file and run source ~/.zshrc
```

## Clone the repository

##Build Types - Environment

Debug
Release

## Flavours:
en
Useful Aliases for Dev
Add these lines to your ~/.zshrc file

# gradle alias
```alias test="./gradlew clean testDebugUnitTest"
alias build="./gradlew clean assembleDebug"
```


## Android Requirements

minSdkVersion = 19

targetSdkVersion = 28

compileSdkVersion = 28


## Author
Vrushali Raut, Adit Lal
