#!/bin/bash

# Check for the correct number of arguments
if [ "$#" -ne 3 ]; then
    echo "Usage: $0 <username> <password> <group>"
    exit 1
fi

# Assign the arguments to variables
username="$1"
password="$2"
group="$3"

# Check if the group already exists, and if not, create it
if ! getent group "$group" >/dev/null; then
    sudo groupadd "$group"
fi

# Create the user with the specified group
sudo useradd -m -g "$group" -s /bin/bash "$username"

#Define server and client
SERVER_IP=
CLIENT_IP=

# Set the user's password
echo "$username:$password" | sudo chpasswd

#Change ownership of the of the  home directory
sudo chown "$username:$group" /home/"$username"

#Mount NFS server
mount -t nfs "$SERVER_IP":/exports /home/"$username"

#expire user password
sudo passwd --expire "$username"


#Set user management quota

# Display a message to indicate success
echo "User '$username' created successfully!!"
