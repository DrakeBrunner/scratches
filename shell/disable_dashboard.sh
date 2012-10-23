#!/bin/bash

# If parameter is off or doesn't exist
if [ $# -eq 0 ]; then
    defaults write com.apple.dashboard mcx-disabled -boolean true
elif [ $1 = "off" ]; then
    defaults write com.apple.dashboard mcx-disabled -boolean true

# If the parameter is on
elif [ $1 = "on" ]; then
    defaults write com.apple.dashboard mcx-disabled -boolean false
fi

killall Dock
