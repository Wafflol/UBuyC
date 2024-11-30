### Contributions
##### Tommy
Created git developer guide for everyone to follow\
Created basic models for users and listings\
Created initial password hashing alg\
Added regex matching for emails to only allow .ubc.ca\
Created db and host from own server\
Added security for pages
  * Restric page access for users
  * Fixed login/signup flow

Added demo scripts for hosting website locally
  * has support for bash and batch

Added logout support\
Fixed bypassing verification through login and changing link

##### Peter
Created and completed verification tokens (OTP) models\
Established connection for user, listing, and verification tokens tables in database\
Created the account DAO to GET and POST to the user and verification tokens tables in db\
Created the listing DAO to POST listing information\
Created a UBuyC noreply email account to send OTPs to users' emails\
Configured JavaMailSender to successfully send emails after a successful registration event on signup\
Modified listing models to store images and image byte data\
Rendered images from all listings\
Created initial login/signup -> verification flow\
Displayed user information in accounts page\
Created a Dockerfile to use Render to host application remotely\
Added various JavaDoc comments

##### Ian
Created search bar functionality with postgresqsl full text search and regex
  * finds minor typos
  * ignores common words (i.e 'the', 'to', etc.)
  * searches for matches in title and description
  * prioritizes title match over description
  * displays best match first

Created methods for displaying listings
  * display all listings (homepage, empty search)
  * user specific listings (profile)
  * listing by email (click on a listing)

Populated the homepage with very cool listings 😎\
Contributed to minor design choices (visual, functionality)\
Some javadoc comments

##### Ellaina
Created html, css, javascript files for all web pages
  * account, index, login, signup, verification, viewListing
  * design and display decisions on htmls pages
  * listing creation modal on home page
  * populated dynamic listing views on index and account pages

Configured basic nagivation between html pages\
Various JavaDoc comments


##### Ethan

