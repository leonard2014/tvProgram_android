Sample project to use [**EasyMVP**](https://github.com/6thsolution/EasyMVP) frameworkde

**feature:**
1. Load and display channel list from https://s3-ap-southeast-2.amazonaws.com/swm-ftp-s3/ios/channel_list.json. The displayed list is sorted by the "displayOrder" field
2. Load and display program list from https://s3-ap-southeast-2.amazonaws.com/swm-ftp-s3/ios/channel_programs_x.json, x is the channel id. The displayed list is sorted by the "start_time" field

<img src="https://raw.githubusercontent.com/leonard2014/tvProgram_android/screenshot/screenshot/channel_list.png" width="300"> <img src="https://raw.githubusercontent.com/leonard2014/tvProgram_android/screenshot/screenshot/program_list.png" width="300">

<img src="https://raw.githubusercontent.com/leonard2014/tvProgram_android/screenshot/screenshot/tablet.png" width="500">

Please use **Android Studio 3.0** to open this project, because the MVP framework EasyMVP needs Android Studio 3.0. 

The app is based on MVP/Clean Architecture/RxJava/Retrofit/Dagger

Adopted the Master/Detail template from Android Studio, because this app is a typical mater/detail app. Channel List is is Master view, and program list is Detail view. Another reason is Master/Detail template reuses detail view in both phone/table screens. See more on the screenshots. 

**3rd Party libraries**a

RxJava, RxAndroid : For Reactive Programming
Dagger2: Compile time approach for dependency injection
EasyMVP: Powerful MVP framework for Android, seamlessly support RxJava, Dagger2 and Clean Architecture
Retrofit: Type-safe HTTP client
Mockwebserver: Configurable web server for testing HTTP client, perfect buddy for Retrofit
Robolectric: Very useful TDD framework for unit test in android project
ButterKnife: For view injection
Stateful-layout: Easy-to-use layout to show most common state template like loading, error, empty
Fresco: Powerful and convenient library for loading and display image resources from network, local storage 

**Development Process**

1. Create project with Master/Detail template from Android Studio
2. Generate Model java classes from json content with online tool

For **channel list feature**, all files are in feature/channelList folder

3. Create network interface with Retrofit, to get channel list Object from network

4. Create unit tests to verify network layer, testing both successful and failed network requests

5. Create GetChannelListUseCase to get channel list from network layer

6. Create SortChannelListMapper to sort the channel list 

7. Define IChannelListView and ChannelListPresenter

8. Implement ChannelListPresenter to get channel list with GetChannelListUseCase, and sort the list with SortChannelListMapper

9. Implement IChannelListView in ChannelListActivity

10. Implement ChannelListRecyclerViewAdapter to show channel list

11. Attach ChannelListActivity to ChannelListPresenter

For **program list feature**, all files are in feature/program folder
Repeat steps 3-11, but implement IProgramView in ChannelDetailFragment

**Project structure**

model: data model for Json object

network: Retrofit interface IProgramService, and ProgramApiClient on top of IProgramService  

app: for Android Application class

contract: global interface, e.g. IBaseView

di: app level dependency injection, including AppComponent and NetModule

feature: separate sources first by feature, then by module, include channelList and program.

feature/channelList: sources for channel list displaying, including view, presenter, usecase, dependency injection, data mapper

feature/program: sources for program list displaying, structure similar to feature/channelList

In test folder there some unit tests and test resources 


