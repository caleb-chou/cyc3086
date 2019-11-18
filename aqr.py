import random

tests = 1000000

results = {}

numspins = 3

for i in range(tests):
    points = 0
    spins = 0 
    while(points >= 0 and spins < numspins):
        spin = random.randint(0,359)
        if(spin <= 5):
            points += 10
        elif(spin <= 115):
            points += 1
        elif(spin <= 175):
            points += 2
        elif(spin <= 190):
            points += 3
        elif(spin <= 240):
            points = -1
        elif(spin <= 320):
            points -= 3
        else:
            points -= 1
        spins += 1
    outcome = -1 if points < 0 else points
    if(outcome in results):
        results[outcome] += 1
    else:
        results[outcome] = 1

for k in sorted(results.keys()):
    print('%s points %s%% of the time.' % (k, 100 * results.get(k)/tests))