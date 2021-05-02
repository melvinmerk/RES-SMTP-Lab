# RES-SMTP-Lab
### Authors: Melvin Merk & Gabrielle Thurnherr

## Description
This repository is a basic implementation of an SMTP (email) pranking program.  


## Usage
To use the program simply edit the 3 configuration files (in config folder): 
- config.properties
    - smtpServerAddress: _Address of the target SMTP server_
    - smtpServerPort: _Port of the target SMTP server (**duh**)_
    - numberOfGroups: _Number of groups to be created_
    - witnessToCC: _list of emails, separated by a comma ",", who will receive a blind copy (Cci)_
    
- messages.utf8: _list of prank messages_
    - Format is:
        - Subject: \<a subject\>
        - empty line
        - body of message
        - empty line
        - 3x "=", each message is separated by "==="
    
    - Example:
      ```
      Subject: You won 100
      
      You won 100 congrats! Get them here <a website>
      
      ===
      Subject: You're the best
      
      Not really lmao, get lost
      
      ===
      ```
- victims.utf8: _list of email adresses to be pranked, one address per line_

## How to install a mock server
If you just want to tryout the program without actually pranking anyone, just follow this easy steps to install a mock server.  


## Implementation details
Most of the classes are inspired by the youtube playlist about this lab.  

**What all the classes do:**
- _Main_: This is the main class
- _ConfigManager_: This class reads the *config.properties* file and exposes two methods to read both the victims and messages files.
- _Person_: This class contains a victims address (and potentially name and last name, but it's not used in this lab)
- _Group_: This class contains group of Persons (people who will be pranked)
- _Mail_: This class defines a mail, basically the sender, the messages (body), all targets, and the "hidden" witness
- _SmtpClient_: This class implements the SMTP protocol. It defines a method to send a Mail object.
- _Prank_: This class defines a Prank, the sender, victims, message and witness
- _PrankGenerator_: This class generates all the groups, generates the Pranks with random messages.

