## Scala with Cats - Exercises

Solutions for the exercises of [Scala with Cats Book](https://underscore.io/books/scala-with-cats/)

### Run a main

For exercises where the code is tested with a main, run

    sbt run

and then select the number of the main. Alternatively, run a specific main like this

    sbt "runMain com.codiply.scalawithcats.chapter1.PrintableMain"


### Run the tests

For exercises where the code is tested with tests, run all the tests with

    sbt run

Run a subset of suites with

    sbt "test:test-only *MonoidSpec"
