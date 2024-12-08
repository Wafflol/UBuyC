### Traige
Issues in order of priority (most to least important)

#### Fixes we would make

##### 1. Passing a 4k quality image causes the page to crash
Most important to fix, as we should be able to support any images that come through  
Also since crashes are bad for the website

##### 2. Uploading non-image files my dragging and dropping causes website to crash
Crashes are important to fix  
Not as important as prev issue, as non-image files aren't supposed to be supported anyway; would just need to add an error message and check the file extension

##### 3. Passing an empty price produces whitelabel error page
Not as important since the price field shouldn't be empty anyways, and nothing breaks if you do it, you just have to go back and put a real price in for the listing to go through  
The fix for this is simply adding an error message and rejecting an empty price so it doesn't go to the whitelabel error page

### Already fixed

#### 4. Sign up does not redirect back to login page
Sign up should redirect to OTP page (and it does), the sign up button was disabled for a little bit on release but was fixed shortly

#### 5. No OTP code is sent to the given email
Not sure if this is a real issue, need more info on how to reproduce, as it works currently 


