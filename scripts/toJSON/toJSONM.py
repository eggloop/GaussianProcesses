#!/usr/bin/env python3
import json
import sys

import numpy as np

filename = sys.argv[1]
with open(filename, "r") as file:
    names = file.readline()
    a = file.read()
    trajectories = a.split("\n\n")
trajectories=trajectories[:-2]
tras = [np.array([a.split() for a in tra.split("\n")],dtype=np.float64) for tra in trajectories]

traces = [
    {"time": trasi[:, 0].tolist(), "values": trasi[:, 1:].transpose().tolist(), "variables": names.strip("\n").split()}
    for trasi in tras]
with open(filename + ".json", "w") as file:
    file.write(json.dumps({"traces": traces}))
