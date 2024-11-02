# Architecture (Components + Stubs)

## Overview
This document specifies all models, controllers, and views in the application ([Model–view–controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)). Each component is detailed in terms of its responsibilities, location, and communication with other components. 

---

## Component Descriptions

### Models

#### UserModel
- **Responsibility**: Stores user info such as name, UBC email, password, (other personal info?)
- **Location**: Server
- **Communication**:
  - AuthenticationController
    - Verifies that user input (email, password) matches with an existing user
    - Adds new users/removes user on account deletion
    - Handles password change requests
    - Updates user details when changes are made

#### ListingModel
- **Responsibility**: Stores listing information such as title, description, price, seller, date posted, tags, and pictures.
- **Location**: Server
- **Communication**:
  - ListingController
    - Asks ListingModel for listing information (title, description, price, etc.)
    - Can retrieve specific listing details and a specific ID for a listing (HashCode)
    - Will tell ListingModel to add/delete listings based on user input

#### SearchModel
- **Responsibility**: Manages search algorithms for synonym recognition and spell correction (Ex. displays all listings that match search query, displays no listings found if no listings match). *(Too hard? idk)*
- **Location**: Client + Server
- **Communication**:
  - SearchController
    - Communicates specific search queries to the SearchModel (sort by price, date, specific item)
  - ListingModel/ListingController
    - Provides listing details required for a search

---

### Views

#### **SignUpView**
- **Responsibility**: Displays sign up fields such as first/last name, student email, password, and re-type password field. Also provides relevant errors (Ex. invalid email, passwords don't match).
- **Location**: Client
- **Communication**:
  - AuthenticationController
    - Retrieves data from sign up fields as specified above for account creation

#### **LoginView**
- **Responsibility**: Displays email/password field, login button, and relevant errors (Ex. invalid username/password).
- **Location**: Client
- **Communication**:
  - AuthenticationController
    - Sends login credentials from fields as outlined above for verification

#### **HomePageView**
- **Responsibility**: Displays all listings in grid format, header bar with logo, along with search bar and account button.
- **Location**: Client
- **Communication**:
  - ListingController
    - Requests recent listings to display on the homepage

#### **ListingView**
- **Responsibility**: Shows detailed information for the selected listing, including image, price, seller info, and an email option.
- **Location**: Client
- **Communication**:
  - ListingController
    - Asks for listing details to display to user

#### **ListingCreationView**
- **Responsibility**: Shows fields for insertion of a picture, title, price, and description.
- **Location**: Client
- **Communication**:
  - ListingController
    - Sends listing data as outlined above for new listing creation

#### **AccountView**
- **Responsibility**: Displays account details and allows user to edit personal info/request password changes.
- **Location**: Client
- **Communication**:
  - AuthenticationController
    - Sends password update requests
    - Updates user info in UserModel when user makes changes to profile
    
---

### Controllers

#### **AuthenticationController**
- **Responsibility**: Handles signup, login, OTPs, and password updates.
- **Location**: Server
- **Communication**:
  - UserModel
    - Sends user data for account creation and validation/verification
  - SignupView/LoginView
    - Gets user input for signup/login
  - AccountView
    - Sends password update requests

#### **ListingController**
- **Responsibility**: Manages listing creation, editing, retrieval, and deletion.
- **Location**: Server
- **Communication**:
  - ListingModel
    - Sends data for listing creation, retrival, and updates (edits made by seller)
  - HomePageView
    - Sends current listings to be displayed
  - ListingView
    - Provides specific listing details when a listing is selected
  - ListingCreationView
    - Gets user input for creating a new listing
    - Says if listing creation was successful

#### **SearchController**
- **Responsibility**: Operates search queries and displaying results.
- **Location**: Client + Server
- **Communication**:
  - SearchModel
    - Provides search queries to execute searches and retrive results
  - HomePageView
    - Takes search input and outputs filtered results based on search criteria

## Component Stubs

### [UserModel]

```java
// File: [User.java]
private String firstName
private String lastName
private String email
private String passwordHash
/** 
 * Creates a new User object
 * @param String firstName
 * @param String lastName
 * @param String email
 * @param String passwordHash
 * @precondition firstName is not null, lastName is not null, email is not null, password is not null
 * @postcondition creates a user object and instantiates all instance variables
 */
public User(String firstName, String lastName, String email, String password) {
    // TODO: Implement constructor
}
/**
 * Encrypts the a string and stores it as the password
 * @param String password
 * @precondition - password not null
 * @postcondition - sets passwordHash
 */
public encryptPassword(String password) {
    // TODO: Encrypt with SHA256 and store to passwordHash   
}

/**
 * @param paramType paramName - Describe the parameter
 * @return returnType - Describe what is returned
 * @precondition - Preconditions for this function
 * @postcondition - Postconditions for this function
 */
public returnType functionName(paramType paramName) {
    // TODO: Replace with actual implementation
    return defaultValue;
}
```
