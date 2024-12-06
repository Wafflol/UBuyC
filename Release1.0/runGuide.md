## How To Use UBuyC
UBuyC is a web app that gives ubc students and faculty access to a secure second hand market, by requiring verification through their ubc accounts.

There are two options to accessing the website, through our hosted server, or if that's down, you can also host it locally, and access it through localhost:8080.

### Navigating to the Website

#### Accessing the Website
Follow this link: \
https://ubuyc-latest.onrender.com/login \
If the link does not work, follow the steps below to host it yourself

#### Hosting Locally
If you are on linux or mac, or have access to bash on windows, this is the simpler method:

##### Bash
cd into Release1.0, and run
```
./init_bash.sh
```
the file should already be executable, but if not, tr
chmod +x init_bash.sh 
```
and then try again \

Wait for a few minutes at most, and then the website should be accessable at: \
http://localhost:8080/

##### Windows
If you are on windows, and don't have access to bash, you can still run the server locally: \
Either through command prompt or file explorer, go into the Release1.0 directory and run init_batch.bat, and then once that's done, run server_batch.bat, and the website shold be up.

### Using UBuyC
First, you must create an account using your student email. If your accoutn was successfully registered, then you will
be directed to a page where you will be prompted to enter an OTP. This OTP will be sent to the email you entered just before. 
Once you input the correct otp, you will be re-directed back to the login page. This is a feature :o. Enter your credentials
again and you should have access to the main website. 

From here you can see all the current listings available. Using the search bar at the top of the page, you can search for any available listings. You can click on any listing to view it and contact the seller. However, the only way to contact them will be through their UBC email. 

You can also create your own listings by pressing the "+" symbol in the bottom right corner. Simply import and image and fill out the fields to create a listing. To view your own listings, click the profile icon in the top-right corner of the page. In this page, you will also be able to see your name and email. 

To signout, navigate to your profile and hit "logout".
