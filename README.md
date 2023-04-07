<h2 align="center">A Vegetable Garden</h2>

## 🧐 About

Java project for the Object-Oriented Programming course at the University of Lyon 1.

- MVC architecture, unit tests, UML diagrams, Trello board, ...
- The goal of the project is to create a vegetable garden management application.
- You can have multiple gardens, add vegetables, water them, harvest them, etc.
- Coin system, weather system, save system, ...

## 📓 Subject

The subject of the project is available [here](project_subject.pdf).

## 🏁 Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### How to run the project

How to install the project and run it.

- Clone the repository
- Command line
    - Open a command line in the project directory
    - Enter the command ```mvn clean install``` to compile and create the executable jar
    - The executable jar will be created in the `target/` directory with the
      name `avegetablegarden.avegetablegarden.jar`
- IDE
    - Open the project in your IDE
    - Run the `avegetablegarden.avegetablegarden` class

### Folder structure

<details>
<summary>Folders tree</summary>

```
├── images
├── saves
│
└── src
     ├── main
     │    └── java
     │        ├── avegetablegarden.modele
     │        │   ├── legumes
     │        │   ├── meteo
     │        │   ├── player
     │        │   ├── potagers
     │        │   └── save_load
     │        │
     │        └── avegetablegarden.vuecontroleur
     │            ├── icon
     │            └── vues
     │                ├── components
     │                └── windows
     │
     └──test
         └── java
             ├── avegetablegarden.modele
             │   ├── legumes
             │   ├── meteo
             │   └── potagers
             │         └── cases
             │
             └── avegetablegarden.vuecontroleur
                    └── icon
```

</details>

## ⛏️ Built Using

- [Java 19](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html) : A popular programming
  language. Java 19 is a major version of Java SE that offers new features
  and enhancements.
- [Maven](https://maven.apache.org/) : A project management tool for Java that helps manage dependencies, compilation,
  testing, packaging, and deployment of Java projects.
- [Swing](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html) : A GUI widget toolkit for Java
  that allows developers to create desktop applications with a modern graphical user interface.
- [JUnit](https://junit.org/junit5/) : A testing framework for Java that allows to write and run unit tests
  to ensure code is functioning as expected.
- [FlatLaf](https://github.com/JFormDesigner/FlatLaf) : A modern, open-source look and feel for Swing GUIs that provides
  a flat, minimalist design.

## 📌 Trello Board

You can find our [Trello Board](https://trello.com/b/c6MRIEao/tableau-agile).

## ✍️ Authors

- [Lucas FAUSTMANN](https://forge.univ-lyon1.fr/p2020351) : p2020351
- [Farès SIONI](https://forge.univ-lyon1.fr/p1907037) : p1907037
