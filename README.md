# Wildlife_Track_Java_Project
an application that allows Rangers to track wildlife sightings in the area.

# Description
The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. 
Before this proposal may be approved, they must complete an environmental impact study. 
This application was developed to allow Rangers to track wildlife sightings in the area.

## Technology Used
Intelj 
Java 


# Setup
To create the necessary databases, launch postgres, then psql, and run the following commands:

- CREATE DATABASE wildlife_tracker; 

- \c wildlife_tracker;

- CREATE TABLE animals (id serial PRIMARY KEY, name varchar);

- CREATE TABLE endangered_animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar);

- CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar);

- CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;


## Contributions

Pull requests are encouraged.

## Contact Details

You can contact me at inange2013@gmail.com.

# License
Copyright (c) 2019 AngeIngabire License
