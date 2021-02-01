# MovShow
Android application to search medias (Movies, Series, Mangas) by using TVMazeAPI

Language:
- Kotlin
- XML

Architecture:
- MVVM
- Clean Architecture

Techno:
- Binding
- Retrofit (TVMazeAPI)
- RX (for the Repository)
- LiveData (for UI)
- Dagger

Unit Test:
- JUnit
- Mockk


TODO:
- Make the recycler unlimited/much more media (now: limit = 10)
- Add ViewPager (page Favorite, page most popular by choosing different mediaType)
- Add favorite series (Room to save favorite series, notifications, WorkManager to know when a new episode is available)

Doing:
- Use Motion Tags and motionLayout
- Add sorting possibility