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
the file should already be executable, but if not, try:
```
chmod +x init_bash.sh 
```
and then try again \

Wait for a few minutes at most, and then the website should be accessable at: \
http://localhost:8080/

##### Windows
If you are on windows, and don't have access to bash, you can still run the server locally: \
Either through command prompt or file explorer, go into the Release1.0 directory and run init_batch.bat, and then once that's done, run server_batch.bat, and the website shold be up.


### Using UBuyC
