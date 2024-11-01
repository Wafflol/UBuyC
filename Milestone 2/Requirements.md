# REQUIREMENTS

make a requirement section for each page I guess.

#### Login Page
- [ ] Needs an email and password field
- [ ] Needs a login button
- [ ] Redirects to home page on successful login
- [ ] Prints invalid username/password in red on unsuccessful login
- [ ] Calls our database to verify login info by matching username and password fields

#### Signup Page
- [ ] Needs a first name, last name, student email, password, and re-type password field
- [ ] Needs a signup button
- [ ] Redirects to OTP page on successful signup
- [ ] Prints account already exists in red on unsuccessful signup

#### OTP Page
- [ ] Needs 6, 1-digit fields for each digit of the OTP
- [ ] Sends a 6 digit OTP to the given student email in the signup page
- [ ] Either automatically submits when 6 digits are inputted or include a submit button
- [ ] Prints OTP is wrong if OTP does not match
- [ ] Creates new account object in DB on successful OTP entry
- [ ] Redirects to home page on successfull OTP entry

#### Home Page
- [ ] Displays current listings in grid format (maybe all listings in the past week?)
- [ ] Listings should include a picture, a title, and a price
- [ ] Allows user to click into individual listings and redirects them to that listing
- [ ] Includes a header with logo, search bar, and account button
- [ ] Allows user to scroll up and down

#### Listing View Page
- [ ] Includes a picture that spans a large portion of the page
- [ ] Includes various details about the listing such as title, price, seller, description, and email button
- [ ] On email button click, open to email page?? (or maybe copies email address to clipboard to make it easier)
- [ ] Also includes header bar

#### Listing Creation Page
- [ ] Allows insertion of a picture
- [ ] Includes title, price, and description button
- [ ] Throws error if not all fields are populated
- [ ] Throws error is price is negative
- [ ] Adds a listing object to the database
- [ ] Also includes a header bar

#### Account Details Page
- [ ] Displays current email and password
- [ ] Allows user to change password
- [ ] Updates password field in database on successful password change

#### Account button
- [ ] Redirects to account details page

#### Search Bar
- [ ] Implements a search algorithm (for now, do perfect match for the title maybe?)
- [ ] Displays all listings that match search query
- [ ] Displays no listings found if no listings match