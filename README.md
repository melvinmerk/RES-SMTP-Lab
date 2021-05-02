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



