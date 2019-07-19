#!/usr/bin/env python3
import json
import numpy as np
import sys

filename = sys.argv[1]
with open(filename, "rb") as file:
    names = file.readline()
    M = np.loadtxt(file)
time = M[:, 0].tolist()
space = M[:, 1:].transpose().tolist()
fullNames = [x.decode('UTF-8') for x in names.split()]
variables = fullNames[1:]
content = json.dumps({"time": time, "values": space, "variables": variables})
with open(filename + ".json", "w") as file:
    file.write(content)