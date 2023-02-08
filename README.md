# README #

This is a template Android project with Clean Architecture and MVVM

### Domain module ###

* Model: Contains all business models.
* Repositories: Contains interface for all repositories, these interfaces will be used inside the use case
* Usecases: ONLY containts business logic, MUST NOT contain any logic related to 3rd libraries, Android SDK...

### Common module ###

* Contains common classes that can be reuse across projects

### App module ###

* Data: implement all repositories in Domain module.
* Presentation: Activities, Fragment, ViewModel and other view components
* DI: Implement dependency injection here

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact