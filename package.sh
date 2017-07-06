#!/bin/bash

echo "[INFO] Build and Package demo Project."

mvn clean package -Dmaven.test.skip=true
