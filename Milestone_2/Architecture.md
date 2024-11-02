# Software Architecture (example template)

## Overview
This document specifies all models, controllers, and views in the application ([Model–view–controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)). Each component is detailed in terms of its responsibilities, location, and communication with other components. 

---

## Component Descriptions

### Models

#### UserModel
- **Responsibility**: Stores user info such as name, UBC email, password, (other personal info?)
- **Location**: Server
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### ListingModel
- **Responsibility**: Stores listing information such as title, description, price, seller, date posted, tags, and pictures.
- **Location**: Server
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### AuthorizationModel
- **Responsibility**: Manages signup/OTPs for new users and login for existing ones.
- **Location**: Server
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### SearchModel
- **Responsibility**: Manages search algorithms for synonym recognition and spell correction (Ex. displays all listings that match search query, displays no listings found if no listings match). *(Too hard? idk)*
- **Location**: Client + Server
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

---

### Views

#### **SignUpView**
- **Responsibility**: Displays sign up fields such as first/last name, student email, password, and re-type password field. Also provides relevant errors (Ex. invalid email, passwords don't match).
- **Location**: Client
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **LoginView**
- **Responsibility**: Displays email/password field, login button, and relevant errors (Ex. invalid username/password).
- **Location**: Client
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **HomePageView**
- **Responsibility**: Displays all listings in grid format, header bar with logo, along with search bar and account button.
- **Location**: Client
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **ListingView**
- **Responsibility**: Shows detailed information for the selected listing, including image, price, seller info, and an email option.
- **Location**: Client
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **ListingCreationView**
- **Responsibility**: Shows fields for insertion of a picture, title, price, and description.
- **Location**: Client
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **?? OTPView ??**
- **Responsibility**: idk bout dis one
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

---

### Controllers

#### **AuthorizationController**
- **Responsibility**: Handles signup, login, OTPs, and password updates.
- **Location**: Server
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **ListingController**
- **Responsibility**: Manages listing creation, editing, retrieval, and deletion.
- **Location**: Server
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **SearchController**
- **Responsibility**: Operates search queries and displaying results.
- **Location**: Client + Server
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

## Component Stubs

Each component has been stubbed in source files within the repository. These stubs define the basic structure, with function headers, arguments, return values, preconditions, and postconditions for all inter-component requests.

### Example Stub: [Component Name]

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
