# Productivity Tracker App

This app tracks user key strokes (enters, spaces, and clicks) and then compiles them into graphs to show the user at what time they were being the most productive. 

It only works on my computer because I didn't code any directory configs for it to work on other windows machines. 

To create this project I utilized kwhat, a jnativehook library I found on github. This library allowed me to listen to user input without having focus on a certain window. I also utilized matplotlib for the graphs, and java swing for the GUI. The rest is mostly standard java and python. By no means is this project pretty or optimal, I just wanted to see if I could make something like this work. 

The projects does have a few issues. First is that the kwhat library seems to have a bug that makes it stop listening as soon as a ctrl keyboard command is done. So no listening is done until you restart the program, which must be done by terminating the program through task master. The command I used to open the GUI is just by pressing ` on your keyboard. This is not optimal for JavaScript devs. However, I didn't have the patience to setup a custom ctrl command that didn't interfere with the kwhat library. The program takes up 30% of my CPU on a good day. I think this is also a result of the kwhat library, and my laptop is mid. Data entry is done with plain .txt files, which probably isn't optimal, I could have made a database with postgreSQL or something, but I have skill issues. The graphs in the GUI are sub optimal, but hey, there were a lot of them okay?

The rest of this READ.ME is screen shots of some of my graphs after some usage. 

<img src="https://github.com/user-attachments/assets/0ce6a523-be6a-4a57-aafe-4765781b49c3">
This is the GUI showing the frequency of my clicks in a month (past 30 days). This graph really just represents when I remembered to turn on the program to do any work.
<br>
<img src="https://github.com/user-attachments/assets/b26cd687-f1cc-408d-b613-3027b2d3a0ab">
Some graphs don't look too good. This is the number of clicks in the past 24 hours. You can see that spike at the end because I turned on the program for five minutes before writing this. 
<br>
<img src="https://github.com/user-attachments/assets/76283839-27a1-46be-b1cb-7e43b60356d2">
I also made some of the graphs cumulative. This shows the increasing amount of times I've pressed enter in the past 30 days. 
<br>
Thanks for reading my READ.ME.
