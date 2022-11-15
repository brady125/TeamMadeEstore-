---
geometry: margin=1in
---
# PROJECT Design Documentation

> _The following template provides the headings for your Design
> Documentation.  As you edit each section make sure you remove these
> commentary 'blockquotes'; the lines that start with a > character
> and appear in the generated PDF in italics._

## Team Information
* Team name: The Restful Gods
* Team members
  * Brady Morin
  * Aashwin Katiyar
  * Erica Libby
  * Logan Holmes
  * Peyton Wagner

## Executive Summary

Our project is an e-store that sells interstellar animals. Users are able to make and
login into an account in order to browse, search, and order the animals 
we offer in our store. There is one admin account that is responsible 
for adding, deleting, and updating products within the e-store. This 
document provides an overview of the purpose of our store, the architecture
and design of the system, and code coverage of our unit tests.

### Purpose
> _Provide a very brief statement about the project and the most
> important user group and user goals._

### Glossary and Acronyms
> _Provide a table of terms and acronyms._

| Term | Definition |
|------|------------|
| SPA | Single Page |
| IO | In/Out |
| DAO | Data Acess Object |


## Requirements

The application has an inventory of products that can be added to, removed from, modified, and filtered, As well as individual user accounts that
can be logged in to to provide individual shopping carts for each user. The application also has admin accounts that grant access to in browser
tools for editing the virtual inventory and products. Users are also able to create new accounts if they don't have one yet.

> _In this section you do not need to be exhaustive and list every
> story.  Focus on top-level features from the Vision document and
> maybe Epics and critical Stories._

### Definition of MVP
An MVP is the the simplest version of an application that allows for user testing and feedback for further development of said application
> _Provide a simple description of the Minimum Viable Product._

### MVP Features
Users must be able to log in to unique accounts, and display the website differently according to the users unique data.
Customers must be able to view, search, and add products to their shopping cart, as well as be able to check out their shopping cart for purchase.
The store owner, through an admin account, must be able to add to, remove from, and modify the virtual inventory as well as the products inside.
The website should save any changes to the inventory or user accounts to persistent files.
The website should use both a username and password to authenticate each user
> _Provide a list of top-level Epics and/or Stories of the MVP._

### Roadmap of Enhancements
Back-end file IO, model, and control for products
Sign up / Log in page and view / control interaction for user authentication
Admin Pages including product editing page
Customer Pages including Shopping cart page and functionality
> _Provide a list of top-level features in the order you plan to consider them._


## Application Domain

This section describes the application domain.

![Domain Model](domain-model-placeholder.png)

> _Provide a high-level overview of the domain for this application. You
> can discuss the more important domain entities and their relationship
> to each other._


## Architecture and Design

This section describes the application architecture.

### Summary

The following Tiers/Layers model shows a high-level view of the webapp's architecture.

![The Tiers & Layers of the Architecture](architecture-tiers-and-layers.png)

The e-store web application, is built using the Model–View–ViewModel (MVVM) architecture pattern. 

The Model stores the application data objects including any functionality to provide persistance. 

The View is the client-side SPA built with Angular utilizing HTML, CSS and TypeScript. The ViewModel provides RESTful APIs to the client (View) as well as any logic required to manipulate the data objects from the Model.

Both the ViewModel and Model are built using Java and Spring Framework. Details of the components within these tiers are supplied below.


### Overview of User Interface

This section describes the web interface flow; this is how the user views and interacts
with the e-store application.

> _Provide a summary of the application's user interface.  Describe, from
> the user's perspective, the flow of the pages in the web application._


### View Tier
> _Provide a summary of the View Tier UI of your architecture.
> Describe the types of components in the tier and describe their
> responsibilities.  This should be a narrative description, i.e. it has
> a flow or "story line" that the reader can follow._

> _You must also provide sequence diagrams as is relevant to a particular aspects 
> of the design that you are describing.  For example, in e-store you might create a 
> sequence diagram of a customer searching for an item and adding to their cart. 
> Be sure to include an relevant HTTP reuqests from the client-side to the server-side 
> to help illustrate the end-to-end flow._


### ViewModel Tier
> _Provide a summary of this tier of your architecture. This
> section will follow the same instructions that are given for the View
> Tier above._

> _At appropriate places as part of this narrative provide one or more
> static models (UML class diagrams) with some details such as critical attributes and methods._


### Model Tier
The model tier featured two different objects, Users and Products. Each featured relevant attributes like Username, Price, Description that could be accessed in the front end.
Shopping carts are an attribute of Users, and store an array of product IDs that can be used to access the appropriate product. Each of the two products have a file DAO that stores a collection of all existing user or product objects in a Map. The DAOs are used to save products or users to JSON files for persistent object information

> _At appropriate places as part of this narrative provide one or more
> static models (UML class diagrams) with some details such as critical attributes and methods._

### Static Code Analysis/Design Improvements
> _Discuss design improvements that you would make if the project were
> to continue. These improvement should be based on your direct
> analysis of where there are problems in the code base which could be
> addressed with design changes, and describe those suggested design
> improvements._

> _With the results from the Static Code Analysis exercise, 
> discuss the resulting issues/metrics measurements along with your analysis
> and recommendations for further improvements. Where relevant, include 
> screenshots from the tool and/or corresponding source code that was flagged._

## Testing
> _This section will provide information about the testing performed
> and the results of the testing._

### Acceptance Testing
> _Report on the number of user stories that have passed all their
> acceptance criteria tests, the number that have some acceptance
> criteria tests failing, and the number of user stories that
> have not had any testing yet. Highlight the issues found during
> acceptance testing and if there are any concerns._

### Unit Testing and Code Coverage
> _Discuss your unit testing strategy. Report on the code coverage
> achieved from unit testing of the code base. Discuss the team's
> coverage targets, why you selected those values, and how well your
> code coverage met your targets. If there are any anomalies, discuss
> those._
