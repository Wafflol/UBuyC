# Software Architecture (example template)

## Overview
This document specifies all models, controllers, and views in the application ([Model–view–controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)). Each component is detailed in terms of its responsibilities, location, and communication with other components. 

---

## Component Descriptions

### Models

#### UserModel
- **Responsibility**: Stores user info such as name, UBC email, password, (other personal info?)
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### ListingModel
- **Responsibility**: Stores listing information such as title, description, price, seller, date posted, tags, and pictures.
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### AuthorizationModel
- **Responsibility**: Manages signup/OTPs for new users and login for existing ones.
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### SearchModel
- **Responsibility**: Manages search algorithms for synonym recognition and spell correction (Ex. displays all listings that match search query, displays no listings found if no listings match). *(Too hard? idk)*
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

---

### Views

#### **SignUpView**
- **Responsibility**: Explain what the view displays or allows the user to do.
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **LoginView**
- **Responsibility**: Explain what the view displays or allows the user to do.
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **HomePageView**
- **Responsibility**: Explain what the view displays or allows the user to do.
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **ListingView**
- **Responsibility**: Explain what the view displays or allows the user to do.
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **ListingCreationView**
- **Responsibility**: Explain what the view displays or allows the user to do.
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **?? OTPView ??**
- **Responsibility**: Explain what the view displays or allows the user to do.
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

---

### Controllers

#### **AuthorizationController**
- **Responsibility**: Define the purpose of this controller and its main operations.
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **ListingController**
- **Responsibility**: Define the purpose of this controller and its main operations.
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

#### **SearchController**
- **Responsibility**: Define the purpose of this controller and its main operations.
- **Location**: (Client/Server/Both)
- **Communication**: What other components the component needs to communicate with and precisely what they will communicate.

## Component Stubs

Each component has been stubbed in source files within the repository. These stubs define the basic structure, with function headers, arguments, return values, preconditions, and postconditions for all inter-component requests.

### Example Stub: [Component Name]

```java
// File: [ComponentFileName.java]

/**
 * Function description
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

wawawaw
