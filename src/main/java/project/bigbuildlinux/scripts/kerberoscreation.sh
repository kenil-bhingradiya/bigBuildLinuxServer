#!/bin/bash

# Check if the required arguments are provided
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <username> <password>"
    exit 1
fi

# Assign the provided username and password to variables
username="$1"
password="$2"

# Define the realm
realm="ADK-EE.com"

# Check if kadmin.local is available
if ! command -v kadmin.local &> /dev/null; then
    echo "kadmin.local command not found. Please make sure you have the Kerberos utilities installed."
    exit 1
fi

# Use kadmin.local to create a new principal with the provided username
if (echo "addprinc $username" && echo "$password" && echo "$password") | sudo kadmin.local -r $realm; then
    echo "User $username successfully created in the Kerberos."
else
    echo "Failed to create user $username in the Kerberos realm $realm."
    exit 1
fi