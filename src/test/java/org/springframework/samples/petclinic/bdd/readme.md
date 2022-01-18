# BDD

This repo is meant to showcase Behavior Driven Development (BDD), specifically concise declarative gherkin, best practices.

## Current Implementation

The following is a list of best practices showcased in this repo

1. [Cucumber Implementation](src/test/java/org/springframework/samples/petclinic/bdd/CucumberTest.java)
2. [Use of Gherkin Universal Language](src/test/java/org/springframework/petclinic/bdd/resources/manage-owners.feature)
3. [Folder Structure](src/test/java/org/springframework/samples/petclinic/bdd/)
4. [Page Object Abstraction](src/test/java/org/springframework/petclinic/bdd/pageobjects)
5. [Personas](src/test/java/org/springframework/petclinic/bdd/resources/personas)
6. [Feature File Organization](src/test/java/org/springframework/petclinic/bdd/resources/features)
7. [Does Not Use Base URL](src/test/java/org/springframework/petclinic/bdd/CucumberSpringContextConfiguration.java)
8. [Seperate Folder for Step Definitions](src/test/java/org/springframework/petclinic/bdd/stepdef)
9. [HTML Report with Screenshots and Logs](src/target/cucumber-reports/index.html)
10. [Leverages Chrome Network Settings](src/test/java/org/springframework/petclinic/bdd/CucumberSpringContextConfiguration.java)
11. [Test Windows Close on Successful Scenarios](src/test/java/org/springframework/petclinic/bdd/CucumberSpringContextConfiguration)

## Improvements In Progress

The following is a list of improvements that have been identified

1. Expanding personas to include pets and visits
2. logging enhancements - screenshots only on failure

## Additional Resources

Interested in learnibng more about BDD? Check out the following sources for more information:

Testing Basics <https://martinfowler.com/articles/practical-test-pyramid.html>

Introduction to BDD <https://dannorth.net/introducing-bdd/>

Gherkin Syntax <https://cucumber.io/docs/gherkin/>

Writing Cucumber Tests <https://saucelabs.com/blog/write-great-cucumber-tests>

File Organization <https://cucumber.io/blog/bdd/solving-how-to-organise-feature-files/>
