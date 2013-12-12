#!/bin/bash

for x in {1..100};
    do curl "http://www.gutenberg.org/cache/epub/$x/pg$x.txt"
done