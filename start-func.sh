#!/usr/bin/env bash

azureOutputDir=build/azure-functions

rm -rf $azureOutputDir
./gradlew packageAzureFunction
cd $azureOutputDir
func host start
cd -
