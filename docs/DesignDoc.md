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

Our project is an e-store that sells interstellar animals. Users are able to make and login into an account in order to browse, search, and order the animals we offer in our store. There is one admin account that is responsible for adding, deleting, and updating products within the e-store. This document provides an overview of the purpose of our store, the architecture and design of the system, and code coverage of our unit tests.

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
| MVC | Model View Controller |


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

The application routes to a page with two buttons to either log in or sign up, allowing new users to create an account and returning users to log in, as our page doesn't allow signed out browsing. Upon signing up or logging in Customer accounts route to the default homepage, where they can veiw all products in the current inventory in a grid and filter products using the searchbar at the top right of the page. Products feature an "add to shopping cart" button that changes to "remove from shopping cart" if the product has already been added. Along the top of the page is the navigation bar, where users can (from left to right) go to the homepage, check their shopping cart, sign out, or search the current listing of products. When moving to the shopping cart page, the cart icon changes to a checkmark, allowing users to checkout. The logout button brings the users back to the login page. And at the very top left of the screen, the user can click the page icon to go back to the default two button directory. Admin accounts view a similar experience, however their navigation bar is missing the shopping cart icon, and they have the option to edit or delete each product by clicking either button on each product. They are also able to click the "add new button" icon to create a new blank product and get directed to the edit product page. There they can edit the text fields that represent the different values in a product and chose to save and leave, or save and continue editing their product.

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
The model tier featured two different objects, Users and Products. Each featured relevant attributes like Username, Price, Description that could be accessed in the front end. Shopping carts are an attribute of Users, and store an array of product IDs that can be used to access the appropriate product. Each of the two products have a file DAO that stores a collection of all existing user or product objects in a Map. The DAOs are used to save products or users to JSON files for persistent object information

> _At appropriate places as part of this narrative provide one or more
> static models (UML class diagrams) with some details such as critical attributes and methods._

### Static Code Analysis/Design Improvements


> _With the results from the Static Code Analysis exercise, 
> discuss the resulting issues/metrics measurements along with your analysis
> and recommendations for further improvements. Where relevant, include 
> screenshots from the tool and/or corresponding source code that was flagged._

## Testing
Testing was primarily performed on the back end, as it's the most crucial and testible part of the code.
Each function of each object in the MVC were unit tested and passed. This includes the REST API
The front end was tested manually.

### Acceptance Testing
All 22 User stories passed all of their acceptance tests, as they were written to pass for the MVP

### Unit Testing and Code Coverage
Like mentioned before, we only unit-tested the back-end code, which was rather small, so we intended to test with 100% code coverage. We knew if there were anny errors in the back-end they would compound as we built on top of them. We did achieve this by testing each of the Objects created for the back-end development. All of our unit tests passed, we made sure of that before proceeding to front end development.

